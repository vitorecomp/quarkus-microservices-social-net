import { BaseConfig } from "./env/BaseConfig";
import { DevConfig } from "./env/DevConfig";
import { ProdConfig } from "./env/ProdConfig";

export class Config extends BaseConfig {
    setAttributes(): void {
        let configBase : BaseConfig;
        
        if(process.env.NODE_ENV === "dev"){
            configBase = new DevConfig()
        }else{
            configBase = new ProdConfig()
        }

        this.copyAttributes(configBase)
    }
}