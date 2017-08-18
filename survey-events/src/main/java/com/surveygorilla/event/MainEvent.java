package com.surveygorilla.event;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by fabio.pires on 18.08.17.
 */
public class MainEvent {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions().setClustered(true), cluster -> {
            if (cluster.succeeded()) {
                final Vertx vertx = cluster.result();
                vertx.deployVerticle(SurveyEventVerticle.class.getName());
            }
        });
    }
}
