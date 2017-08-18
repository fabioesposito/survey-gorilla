package com.surveygorilla.api;

import com.surveygorilla.common.command.CreatePollCommand;
import com.surveygorilla.common.command.SubmitAnswerCommand;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;


/**
 * Created by fabio.pires on 17.08.17.
 */
public class SurveyApiVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void start(Future<Void> start) throws Exception {
        logger.info("SurveyApiVerticle started");

        Router router = Router.router(vertx);
        router.route("/api/polls*").handler(BodyHandler.create());

        router.get("/api/polls").handler(this::getAllPolls);
        router.get("/api/polls/:id").handler(this::getPollById);
        router.post("/api/polls").handler(this::addPoll);
        router.post("/api/polls/:id/answers").handler(this::submitAnswer);

        vertx.createHttpServer().
                requestHandler(router::accept).
                listen(8080, "0.0.0.0");

    }

    private void getAllPolls(RoutingContext ctx) {
        vertx.eventBus().
                send("GET:/api/polls", "",
                        responseHandler -> requestResponse(ctx, responseHandler));
    }

    private void addPoll(RoutingContext ctx) {
        final CreatePollCommand createPollCommand = Json.decodeValue(ctx.getBodyAsString(),
                CreatePollCommand.class);

        vertx.eventBus().
                send("POST:/api/polls", Json.encode(createPollCommand),
                        responseHandler -> {
                            requestResponse(ctx, responseHandler);
                        });
    }

    private void getPollById(RoutingContext ctx) {
        vertx.eventBus().
                send("GET:/api/polls/:id", ctx.get("id"),
                        responseHandler -> requestResponse(ctx, responseHandler));
    }

    private void submitAnswer(RoutingContext ctx) {
        final SubmitAnswerCommand submitAnswerCommand = Json.decodeValue(ctx.getBodyAsString(),
                SubmitAnswerCommand.class);

        vertx.eventBus().
                send("POST:/api/polls/:id/answer", Json.encode(submitAnswerCommand),
                        responseHandler -> requestResponse(ctx, responseHandler));
    }

    private void requestResponse(RoutingContext ctx, AsyncResult<Message<Object>> responseHandler) {
        if (responseHandler.failed()) {
            logger.info("SurveyApiVerticle.requestResponse.failed=" + responseHandler.cause().getMessage());

            ctx.fail(500);
        } else {
            final Message<Object> result = responseHandler.result();
            ctx.response().end((String) result.body());
        }
    }
}
