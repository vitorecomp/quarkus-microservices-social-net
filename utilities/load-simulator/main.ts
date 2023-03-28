import {Config} from "./src/config/Config"
import logger from "./src/config/logger/Logger"

const config : Config = new Config()
logger.debug(config)


const main = async (): Promise<any> => {
    while(1){
        // get a random user
        const user = Math.floor(Math.random() * config.numberOfUsers)

        //look for the user
        //if user not exist 
            //create a new user

        //get a random from 0 to 10
        const numberOfPosts = Math.floor(Math.random() * 10)
        for(let i: number = 0; i < numberOfPosts; i++){
        }
        //create a post

        //get all the friend
        const friends = []
        const numberOfLook = Math.floor(Math.random() * friends.length)
        for(let i: number = 0; i < numberOfLook; i++){
        }

        const posts = []
        //get a random from 1 to 5
        const numberOfLikes = Math.floor(Math.random() * 5) + 1


        for(let i = 0; i < numberOfLikes; i++){
            //get a random friend
            const post = Math.floor(Math.random() * posts.length)
            //like the post
        }

    }
    


}

main()

