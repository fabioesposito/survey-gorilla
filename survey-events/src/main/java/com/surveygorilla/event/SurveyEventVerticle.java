package com.surveygorilla.event;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Created by fabio.pires on 18.08.17.
 */
public class SurveyEventVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void start(Future<Void> start) throws Exception {
        logger.info("SurveyEventVerticle started");
        vertx.eventBus().addInterceptor(message -> {
            logger.info("NEW EVENT:", message.message().body());
        });

        start.complete();
    }
}
