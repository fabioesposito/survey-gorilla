#!/bin/bash

./gradlew clean build

docker build -t fesposito/survey-api survey-api/.

docker build -t fesposito/survey-events survey-events/.

docker build -t fesposito/survey-read survey-read/.

docker build -t fesposito/survey-write survey-write/.
