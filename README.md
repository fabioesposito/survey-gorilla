# Survey Gorilla
Survey monkey clone using vertx 

## Todo

    - Statistics
    - Event sourcing
    - Docker
    
## Components

survey-api

survey-common

survey-read

survey-write


## How to run

`docker-compose up`

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
