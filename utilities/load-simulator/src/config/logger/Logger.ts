import { createLogger, format, Logger, transports } from 'winston';

const { combine, timestamp, label, printf } = format;
declare global {
    let logger : Logger
}
const logFormat = printf(({ level, message, label, timestamp }) => {
    if(typeof message == "object"){
        message = JSON.stringify(message)
    }
    
    return `${timestamp} [${label}] ${level}: ${message}`;
  });

const logger = createLogger({
    format: combine(
        label({ label: 'SimpleApp' }),
        timestamp(),
        logFormat
      ),
});

if (process.env.NODE_ENV !== 'production') {
    logger.add(new transports.Console({
        level: 'debug'
    }));
}else{
    logger.add(new transports.Console());
}


export default logger