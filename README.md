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
1. Home page for client has several links that direct to different pages (Eg: authentication, signup, profile)
1. Firebase is already set up for authentication and storing data
1. Home page for server displays `Hello World`

## TODO:





## Vue.JS Log In Credentials:

email = hello@world.com
password = password

Login screen and Sign Up screen redirects the user with authentication to a secured page.

## Project Mockups:

This is the link to our project mockups and ideas.
https://xd.adobe.com/view/a80ad18b-0783-464b-9eae-38fa800c634b-fd59/
