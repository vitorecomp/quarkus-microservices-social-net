FROM node:slim

COPY package.json ./

RUN npm install
RUN npm install -g ts-node

COPY main.ts main.ts
COPY src src

RUN npm run build

CMD npm run dev-test




