# Dogmates (name subject to change) Application

## For development setup: 

1. Clone project: `git clone https://csil-git1.cs.surrey.sfu.ca/cmalla/dogmates.git`

1. Open terminal and navigate into the project directory

1. Run `docker-compose up --build`

1. Test everything went fine by going to `localhost:8080` and you should see `Hello World`

## Dependencies:

1. Have docker installed

1. Keep port `8080` and `5432` available 

1. Project uses `Java 8` (subject to change)

## What state is project at?

1. A dockerized Spring Boot application with a database connection to a PostgreSQL docker container
2. Home page displays `Hello World`

## TODO: 

1. Set-up `Vue` as the front-end for the application
1. Set-up `AWS Cognito` for identity management (sign-up/logging in/out users)