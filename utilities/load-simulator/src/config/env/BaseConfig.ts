export abstract class BaseConfig {
    public numberOfUsers: number = 0

    abstract setAttributes(): void

    constructor(){
        this.setAttributes()
    }

    copyAttributes(baseConfig : BaseConfig): void {
        this.numberOfUsers = baseConfig.numberOfUsers
    }
}