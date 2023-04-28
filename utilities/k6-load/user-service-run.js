import http from 'k6/http';
import { check } from 'k6';
import {getConfig} from './config-factory.js';
import faker from 'https://cdnjs.cloudflare.com/ajax/libs/Faker/3.1.0/faker.min.js'

function generateUser(id) {
    return {
        id: id,
        name: faker.name.firstName(),
    };
}

export function getUser(userId) {
    const config = getConfig();
    const result = http.post(`${config.userUrl}/user`, JSON.stringify(generateUser(userId)), { headers: { 'Content-Type': "application/json" } });
    check(result, {
        'http response status code is 200 or 201': result.status === 200 || result.status === 201,
    });
    return JSON.parse(result.body);
}

export function follow(followerId, toFollow) {
    const config = getConfig();
    const result = http.post(`${config.userUrl}/follow/${toFollow}`, { }, { headers: { 'X-User-Id': followerId } });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;
}