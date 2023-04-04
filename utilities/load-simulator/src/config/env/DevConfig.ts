import fs from "fs";
import { BaseConfig } from "./BaseConfig";

export class DevConfig extends BaseConfig {
    setAttributes(): void {
        try {
            // Note that jsonString will be a <Buffer> since we did not specify an
            // encoding type for the file. But it'll still work because JSON.parse() will
            // use <Buffer>.toString().
            const jsonString: string = fs.readFileSync("src/config/dev-files/dev-config.json", { encoding: 'utf-8' });
            const config = JSON.parse(jsonString);


            if (config.numberOfUsers != undefined)
                this.numberOfUsers = config.numberOfUsers
            else
                throw "Undefined file config attribute numberOfUsers, is necessary to define it on src/config/dev-files/dev-config.json"

            if (config.serverUserUrl != undefined)
                this.serverUserUrl = config.serverUserUrl
            else
                throw "Undefined file config attribute serverUserUrl, is necessary to define it on src/config/dev-files/dev-config.json"

            if (config.serverPostUrl != undefined)
                this.serverPostUrl = config.serverPostUrl
            else
                throw "Undefined file config attribute serverPostUrl, is necessary to define it on src/config/dev-files/dev-config.json"



        } catch (err) {
            console.error(err)
            throw "src/config/dev-files/dev-config.json not found please create the file as defined on src/config/dev-files/dev-config-template.json"
        }
    }
}