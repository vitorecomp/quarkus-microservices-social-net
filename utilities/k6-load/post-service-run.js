import faker from 'https://cdnjs.cloudflare.com/ajax/libs/Faker/3.1.0/faker.min.js'

export const generatePost = () => ({
    text: faker.lorem.sentence(),
});

export const generateComment = () => ({
    text: faker.lorem.sentence(),
});


export function getPosts(page = 0, lastId = null) {
    const result = http.get(`${config.postUrl}/posts?lastId=${lastId}&limit=50&page=${page}`);
    check(post, {
        'http response status code is 200': result.status === 200,
    });
    return result.body;
}

export function like(postId, userId) {
    const result = http.post(`${config.postUrl}/like/${postId}`, { }, { headers: { 'X-User-Id': userId } });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;

}

export function comment(postId, userId) {
    const result = http.post(`${config.postUrl}/comment/${postId}`, generateComment, { headers: { 'X-User-Id': userId } });
    check(result, {
        'http response status code is 200': result.status === 200,
    });
    return result;
}