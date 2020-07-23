# PawMates Application

## For development setup:

1. Clone project: `git clone https://csil-git1.cs.surrey.sfu.ca/cmalla/dogmates.git`

1. Open terminal and navigate into the project directory

1. Run `docker-compose up --build`

1. Test everything went fine by going to `http://localhost:8080` and you should see a demo Vue application

1. Test the backend by going to `http://localhost:8090` and you should see `Hello World`

## Dependencies:

1. Have docker installed

1. Keep port `8080`, `8090` and `5432` available

1. Project uses `Java 8` (subject to change)

## What state is project at?

1. A dockerized Spring Boot backend application and Vue frontend application with a database connection to a PostgreSQL docker container
1. Home page for client is default starter Vue app
1. Home page for server displays `Hello World`

## TODO:

1. Set-up `AWS Cognito` or `Google Firebase` for identity management (sign-up/logging in/out users)

1. Clean up default files from Vue project and set up file structure



## Vue.JS Log In Credentials:

email = hello@world.com
password = password

Login screen and Sign Up screen redirects the user with authentication to a secured page.