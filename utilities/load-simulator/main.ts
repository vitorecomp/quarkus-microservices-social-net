import axios from "axios";
import {Config} from "./src/config/Config"
import logger from "./src/config/logger/Logger"
import { faker } from '@faker-js/faker';
import { promises } from "dns";

const config : Config = new Config()
logger.debug(config)


const main = async (): Promise<any> => {
    while(1){
        // get a random user
        const user = Math.floor(Math.random() * config.numberOfUsers)

        
        //if user not exist 
        logger.info("create user")
        await axios.post(config.serverUserUrl + "/user", {
            id: user,
            name: faker.name.fullName(),
        })


        //get a random from 0 to 10
        const numberOfPosts = Math.floor(Math.random() * 10)
        let proms = []
        for(let i: number = 0; i < numberOfPosts; i++){
            logger.info("create post")
            async function post(user: number) {
                let post = await axios.post(config.serverPostUrl + "/post", {
                    user: user,
                    text: faker.lorem.sentence(),
                })
    
                console.log(post.data.id)
                await axios.delete(config.serverPostUrl + "/post/" + post.data.id)    
            }
            
            proms.push(post(user));
        }
        await Promise.all(proms)
        //create a post

        // //get all the friend
        // const friends = []
        // const numberOfLook = Math.floor(Math.random() * friends.length)
        // for(let i: number = 0; i < numberOfLook; i++){
        // }

        // const posts = []
        // //get a random from 1 to 5
        // const numberOfLikes = Math.floor(Math.random() * 5) + 1


        // for(let i = 0; i < numberOfLikes; i++){
        //     //get a random friend
        //     const post = Math.floor(Math.random() * posts.length)
        //     //like the post
        // }

    }
    


}

main()

