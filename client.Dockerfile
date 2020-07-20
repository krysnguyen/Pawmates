#
# Build Client app stage
#
# base image
FROM node:lts-alpine

# set working directory
WORKDIR /app

# add `/app/node_modules/.bin` to $PATH
ENV PATH /app/node_modules/.bin:$PATH

# install and cache app dependencies
COPY client/package.json /app/package.json
RUN npm install
RUN npm install @vue/cli@3.7.0 -g

# copy project files and folders to the current working directory (i.e. 'app' folder)
COPY /client .

# build app for production with minification
RUN npm run build

EXPOSE 8080
# start app
CMD ["npm", "run", "serve"]
