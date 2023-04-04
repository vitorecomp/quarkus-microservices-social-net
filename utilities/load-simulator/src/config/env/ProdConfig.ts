import { BaseConfig } from "./BaseConfig";

export class ProdConfig extends BaseConfig {
    setAttributes(): void {
        if (process.env.NUMBER_OF_USERS != undefined)
            this.numberOfUsers = parseInt(process.env.NUMBER_OF_USERS)
        else
            throw "Undefined process.env.NUMBER_OF_USERS, is necessary to set it in production mode"

        if (process.env.SERVER_USER_URL != undefined)
            this.serverUserUrl = process.env.SERVER_USER_URL
        else
            throw "Undefined process.env.SERVER_USER_URL, is necessary to set it in production mode"

        if (process.env.SERVER_POST_URL != undefined)
            this.serverPostUrl = process.env.SERVER_POST_URL
        else
            throw "Undefined process.env.SERVER_POST_URL, is necessary to set it in production mode"

    }
}