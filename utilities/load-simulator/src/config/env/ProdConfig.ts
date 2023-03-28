import { BaseConfig } from "./BaseConfig";

export class ProdConfig extends BaseConfig {
    setAttributes(): void {
        if(process.env.NUMBER_OF_USERS != undefined)
            this.numberOfUsers =  parseInt(process.env.NUMBER_OF_USERS)
        else
            throw "Undefined process.env.NUMBER_OF_USERS, is necessary to set it in production mode"
        
    }
}