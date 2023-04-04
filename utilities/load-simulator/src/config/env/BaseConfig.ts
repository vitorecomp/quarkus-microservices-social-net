export abstract class BaseConfig {
    public numberOfUsers: number = 0
    public serverUserUrl: string = ''
    public serverPostUrl: string = ''


    abstract setAttributes(): void

    constructor(){
        this.setAttributes()
    }

    copyAttributes(baseConfig : BaseConfig): void {
        this.numberOfUsers = baseConfig.numberOfUsers
        this.serverUserUrl = baseConfig.serverUserUrl
        this.serverPostUrl = baseConfig.serverPostUrl
    }
}