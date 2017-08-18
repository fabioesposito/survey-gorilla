package com.surveygorilla.read;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by fabio.pires on 16.08.17.
 */
public class MainRead {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions().setClustered(true), cluster -> {
            if (cluster.succeeded()) {
                final Vertx vertx = cluster.result();
                vertx.deployVerticle(SurveyReadVerticle.class.getName());
            }
        });
    }
}

