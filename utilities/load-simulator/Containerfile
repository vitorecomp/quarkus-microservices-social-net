FROM node:slim

COPY package.json ./

RUN npm install

COPY main.js main.js
COPY src src

RUN npm run build

CMD npm run generate-cert




