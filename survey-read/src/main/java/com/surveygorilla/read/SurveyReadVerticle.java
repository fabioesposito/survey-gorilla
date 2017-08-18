package com.surveygorilla.read;

import com.surveygorilla.common.Poll;
import com.surveygorilla.common.event.AnswerSubmitted;
import com.surveygorilla.common.event.PollCreatedEvent;
import com.surveygorilla.common.exception.PollCreationException;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by fabio.pires on 15.08.17.
 */
public class SurveyReadVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private Map<Integer, Poll> polls = new LinkedHashMap<>();

    @Override
    public void start(Future<Void> start) throws Exception {
        logger.info("SurveyReadVerticle started");

        vertx.eventBus().consumer("GET:/api/polls", getAllPolls());
        vertx.eventBus().consumer("GET:/api/polls/:id", getPollById());
        vertx.eventBus().consumer("pollCreatedEvent", pollCreatedEventListener());
        vertx.eventBus().consumer("answerSubmittedEvent", answerSubmittedEventListener());
        start.complete();
    }

    private Handler<Message<String>> getAllPolls() {
        return handler -> handler.reply(Json.encodePrettily(polls.values()));
    }

    private Handler<Message<String>> getPollById() {
        return handler -> handler.reply(Json.encodePrettily(polls.get(handler.body())));
    }

    private Handler<Message<String>> pollCreatedEventListener() {
        return handler -> {
            final PollCreatedEvent pollCreatedEvent = Json.decodeValue(
                    handler.body(),
                    PollCreatedEvent.class);

            try {
                polls.put(
                        pollCreatedEvent.getPollID(),
                        new Poll(
                                pollCreatedEvent.getPollID(),
                                pollCreatedEvent.getQuestion(),
                                pollCreatedEvent.getOptions(),
                                new HashMap<>(),
                                new HashMap<>()
                        ));
            } catch (PollCreationException e) {
                e.printStackTrace();
            }
        };
    }

    private Handler<Message<String>> answerSubmittedEventListener() {
        return handler -> {
            final AnswerSubmitted answerSubmitted = Json.decodeValue(
                    handler.body(),
                    AnswerSubmitted.class);

            Poll p = polls.get(answerSubmitted.getPollID());

            if (p != null) {
                p.getAnswers().putAll(answerSubmitted.getAnswers());
                p.getStats().putAll(answerSubmitted.getStats());

                polls.put(p.getPollID(), p);
            }
        };
    }
}




