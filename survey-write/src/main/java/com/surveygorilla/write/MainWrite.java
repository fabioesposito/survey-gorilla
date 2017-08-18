package com.surveygorilla.write;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * Created by fabio.pires on 16.08.17.
 */
public class MainWrite {
    public static void main(String[] args) {
        Vertx.clusteredVertx(new VertxOptions().setClustered(true), cluster -> {
            if (cluster.succeeded()) {
                final Vertx vertx = cluster.result();
                vertx.deployVerticle(SurveyWriteVerticle.class.getName());
            }
        });
    }
}
