import faker from 'https://cdnjs.cloudflare.com/ajax/libs/Faker/3.1.0/faker.min.js'

import {getConfig} from './config-factory.js';
import http from 'k6/http';
import { check } from 'k6';

export const generatePost = () => ({
    title: faker.lorem.sentence(),
    content: faker.lorem.sentence(),
});

export const generateComment = () => ({
    content: faker.lorem.sentence(),
});

export function post(userId) {
    const config = getConfig();
    const result = http.post(`${config.postUrl}/post`, JSON.stringify(generatePost()), { headers: { 'X-User-Id': userId , 'Content-Type': "application/json"} });
    check(result, {
        'http response status code is 200 or 201': result.status === 200 || result.status === 201,
    });
    try {
        return JSON.parse(result.body);
    } catch(e) {
        console.error("error on create post")
    }
    
}

export function getPosts(page, lastId) {
    const config = getConfig();
    let request = `${config.postUrl}/post?size=50`
    if(lastId !== undefined && page !== 0)
        request = `${config.postUrl}/post?lastId=${lastId}&size=50&index=${page}`
    const result = http.get(request);

    check(post, {
        'http response status code is 200': result.status === 200,
    });

    if(!result.body)
        return []

    try {
        let body = JSON.parse(result.body);
        if(!body.length)
            return []
    } catch(e) {
        console.error("error on get posts")
        console.error(result)
        return []
    }
}

export function like(postId, userId) {
    const config = getConfig();

    const result = http.post(`${config.postUrl}/like/${postId}`, { }, { headers: { 'X-User-Id': userId, 'Content-Type': "application/json"} });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;
}

export function comment(postId, userId) {
    const config = getConfig();

    const result = http.post(`${config.postUrl}/post/${postId}/comments`, JSON.stringify(generateComment()), { headers: { 'X-User-Id': userId , 'Content-Type': "application/json"} });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;
}