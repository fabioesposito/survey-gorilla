# Survey Gorilla
Survey monkey clone using vertx 
    
## Components

survey-api
API for all frontends

survey-common
Shared VOs used by all services

survey-write
Handle all write-side requests (POST/PUT/DELETE)

survey-read
Answer all read-side requests (GET)

survey-event
Log all events happening on the system


## How to build

`./build.sh`


## How to run

Option 1: fatjar

`java -jar survey-api/build/libs/survey-api-1.0-SNAPSHOT-fat.jar`

`java -jar survey-read/build/libs/survey-read-1.0-SNAPSHOT-fat.jar`

`java -jar survey-write/build/libs/survey-write-1.0-SNAPSHOT-fat.jar`

`java -jar survey-events/build/libs/survey-events-1.0-SNAPSHOT-fat.jar`

Option 2:

`docker-compose up`

## How to use

`curl -X GET \
   http://localhost:8080/api/polls`

`curl -X POST \
  http://localhost:8080/api/polls \
  -H 'content-type: application/json' \
  -d '{
"question" : "When!??????", 
"options" : { 
	"1": "First option",
	"2": "another option"
	}
}'` 

`curl -X POST \
   http://localhost:8080/api/polls/1/answers \
   -H 'cache-control: no-cache' \
   -H 'content-type: application/json' \
   -H 'postman-token: 236ef2c5-c62d-0ff5-2d17-b16e81323076' \
   -d '{
 "pollID" : "1", 
 "answers" : [1,2]
 }'`
