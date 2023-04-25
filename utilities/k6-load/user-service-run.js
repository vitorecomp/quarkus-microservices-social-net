import http from 'k6/http';
import { check } from 'k6';
import ConfigFactory from 'config-factory.js';

function generateUser(id) {
    return {
        id: id,
        firstName: faker.name.firstName(),
        lastName: faker.name.lastName(),
        email: faker.internet.email(),
        password: faker.internet.password(),
    };
}

export function getUser(userId) {
    const config = ConfigFactory.getConfig();
    const result = http.post(`${config.userUrl}/user`, generateUser(userId));
    check(result, {
        'http response status code is 200 or 201': result.status === 200 || result.status === 201,
    });
    return result.body;
}

export function follow(followerId, toFollow) {
    const result = http.post(`${config.userUrl}/follow/${toFollow}`, { }, { headers: { 'X-User-Id': followerId } });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;
}