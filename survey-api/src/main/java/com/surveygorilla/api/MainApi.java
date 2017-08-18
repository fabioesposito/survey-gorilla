package com.surveygorilla.api;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by fabio.pires on 17.08.17.
 */
public class MainApi {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions().setClustered(true), cluster -> {
            if (cluster.succeeded()) {
                final Vertx vertx = cluster.result();
                vertx.deployVerticle(SurveyApiVerticle.class.getName());
            }
        });
    }
}
