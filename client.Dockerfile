#
# Build Client app stage
#
# base image
FROM node:lts-alpine as build-stage

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies

COPY client/package.json /app/package.json

RUN npm install
RUN npm install @vue/cli@3.7.0 -g

#install firebase
RUN npm install --save firebase

# copy project files and folders to the current working directory (i.e. 'app' folder)

COPY /client .



# for PRODUCTION
# build app for production with minification
RUN npm run build
WORKDIR /
FROM nginx:stable-alpine as production-stage
COPY --from=build-stage /app/dist /usr/share/nginx/html
COPY /client/default.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]


# for DEVELOPMENT
#EXPOSE 8080
## start app
#CMD ["npm", "run", "serve"]
