import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import ch.qos.logback.core.status.OnConsoleStatusListener
import groovy.transform.Field
import static ch.qos.logback.classic.Level.DEBUG

// THIS FILE IS IN WEB-INF/classes/, because IDEA 12 compiles all groovy in src, even in resources, and this problem is solved in IDEA 13.

@Field String systemOutAppender = 'systemOut'
@Field String logFileAppender = 'logfile'

displayStatusOnConsole()
scan('5 minutes')  // Scan for changes every 5 minutes.
setupAppenders()
setupLoggers()

def displayStatusOnConsole() {
    statusListener OnConsoleStatusListener
}

def setupAppenders() {
    def logfileDate = timestamp("yyyyMMdd'T'HHmmss") // Formatted current date.
    // hostname is a binding variable injected by Logback.
    def filePatternFormat = "%d{HH:mm:ss.SSS} %-5level [${hostname}] %logger - %msg%n"

    appender(logFileAppender, FileAppender) {
        file = "main.${logfileDate}.log"
        encoder(PatternLayoutEncoder) {
            pattern = filePatternFormat
        }
    }

    appender(systemOutAppender, ConsoleAppender) {
        encoder(PatternLayoutEncoder) {
            pattern = filePatternFormat
        }
    }
}

def setupLoggers() {
    root DEBUG, [systemOutAppender, logFileAppender]
}

