import faker from 'https://cdnjs.cloudflare.com/ajax/libs/Faker/3.1.0/faker.min.js'

import UserService from 'user-service.js';
import PostService from 'post-service.js';
import ConfigFactory from 'config-factory.js';


export const options = {
  stages: [
    { target: 200, duration: '30s' },
    { target: 0, duration: '30s' },
  ],
};



export default function () {
  //get config
  const config = ConfigFactory.getConfig();
  
  //get a user
  const userId = faker.random.number(config.maxUsers)
  const user = UserService.getUser(userId)

  if(config.shouldPost())
    PostService.post(user.id)
    
  let lastId = null
  //make the reads
  for(let i = 0; i < config.viewRatio; i++) {
    //get a list of post
    const posts = PostService.getPosts(lastId)
    lastId = posts[posts.length - 1].id
    posts.map((post) => {
      //like the post
      if(config.shouldLike())
        PostService.like(post.id, user.id)
      
      //comment on the post
      if(config.shouldComment())
        PostService.comment(post.id, user.id)

      //follow the post user
      if(config.shouldFollow())
        UserService.follow(user.id, post.userId)
    })
    
  }
}