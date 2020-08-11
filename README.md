# PawMates

## Setup

### For production:

1. Clone project: `git clone https://csil-git1.cs.surrey.sfu.ca/cmalla/dogmates.git`

1. Open terminal and navigate into the project directory

1. Run `docker-compose up --build`

1. Go to `http://localhost:8080` for the PawMates homepage

## Dependencies:

1. Have docker installed

1. Keep ports `8080` and `8090` available

1. Project uses `Java 11`

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

This is the link to our project mockups and ideas:\
https://xd.adobe.com/view/a80ad18b-0783-464b-9eae-38fa800c634b-fd59/

## Photo attribution/licensing

All static photos used in PawMates are from Unsplash, who requires no permission or attribution to use photos.\
Link to Unsplash license: https://unsplash.com/license


## Project Description:


Our project has several different features as promised in our project proposal. 

* The "Sniff" page is where the user can look through other user's profiles and like/dislike them. If two users match, then they will both 
  on each other's "My Pawmates" page. 

* The "My Pawmates" page is where users can view their matches. In other words, people that they have liked and are interested in hanging out with.

* The "Walk" page is where users can view walks created by their matches, users can join walks and create walks. We also implemented a maps feature which
  shows the path of the walk. 

* The user can click on their name on the top right hand corner and go to their profile. The "profile" page allows the user to update and edit their profile. 

* The "Message" page is where the user can go to message the users that they have matched with. This enables users to easily contact each other. 
