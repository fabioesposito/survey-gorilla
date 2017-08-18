package com.surveygorilla.write;

import com.surveygorilla.common.Poll;
import com.surveygorilla.common.command.CreatePollCommand;
import com.surveygorilla.common.command.SubmitAnswerCommand;
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


/**
 * Created by fabio.pires on 16.08.17.
 */
public class SurveyWriteVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private AtomicInteger count = new AtomicInteger();

    private Map<Integer, Poll> polls = new LinkedHashMap<>();

    @Override
    public void start(Future<Void> start) throws Exception {
        logger.info("SurveyWriteVerticle started");
        vertx.eventBus().consumer("POST:/api/polls", addPoll());
        vertx.eventBus().consumer("POST:/api/polls/:id/answer", submitAnswer());
    }

    private Handler<Message<Object>> addPoll() {

        return handler -> {
            final CreatePollCommand createPollCommand = Json.decodeValue(
                    handler.body().toString(),
                    CreatePollCommand.class);

            // validations skipped

            try {
                Poll p = new Poll(
                        count.addAndGet(1),
                        createPollCommand.getQuestion(),
                        createPollCommand.getOptions(),
                        new HashMap<>(),
                        new HashMap<>()
                );

                createPollCommand.getOptions().keySet().forEach(o -> {
                    p.getAnswers().merge(o, 0, Integer::sum);
                    p.getStats().merge(o, 0.0, Double::sum);
                });

                polls.put(count.get(), p);

                // create and propagate events
                final PollCreatedEvent pollCreatedEvent = new PollCreatedEvent(p.getPollID(), p.getQuestion(), p.getOptions());
                vertx.eventBus().send("pollCreatedEvent", Json.encode(pollCreatedEvent));

                handler.reply(Json.encodePrettily(p));

            } catch (PollCreationException e) {
                e.printStackTrace();
                handler.fail(500, "Unable to create poll " + e.getMessage());
            }

        };
    }

    private Handler<Message<Object>> submitAnswer() {

        return handler -> {
            final SubmitAnswerCommand submitAnswerCommand = Json.decodeValue(
                    handler.body().toString(),
                    SubmitAnswerCommand.class);

            Poll p = polls.get(submitAnswerCommand.getPollID());
            if (p != null){
                submitAnswerCommand.getAnswers().forEach(a -> {
                    p.getAnswers().merge(a, 1, Integer::sum);
                });

               polls.put(p.getPollID(), p);

                final AnswerSubmitted answerSubmitted = new AnswerSubmitted(p.getPollID(), p.getAnswers());
                vertx.eventBus().send("answerSubmittedEvent", Json.encode(answerSubmitted));

                handler.reply(Json.encodePrettily(p));
            }
            handler.fail(500, "Unable to submit answer");
        };
    }
}
