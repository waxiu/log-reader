package model;

import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;

public class LogFileAnalytics {
    private final Path logFilePath;  //dziala
    private final Duration timeProcessingLogFile;
    private final Duration timeBetweenFirstLastLog;  //dziala
    private final HashMap<LogLevel, Integer> logLevelsOccurrences; // dziala
    private final HashMap<String, Integer> librariesOccurrences; //chybadziala
    private final int logTotalNumber; //dziala

    public LogFileAnalytics(Path logFilePath,
                            Duration timeBetweenFirstLastLog,
                            Duration timeProcessingLogFile,
                            HashMap<LogLevel, Integer> logLevelsOccurrences,
                            HashMap<String, Integer> librariesOccurrences,
                            int logTotalNumber) {

        this.logFilePath = logFilePath;
        this.timeBetweenFirstLastLog = timeBetweenFirstLastLog;
        this.timeProcessingLogFile = timeProcessingLogFile;
        this.logLevelsOccurrences = logLevelsOccurrences;
        this.librariesOccurrences = librariesOccurrences;
        this.logTotalNumber = logTotalNumber;
    }

    public Path getLogFilePath() {
        return logFilePath;
    }

    public Duration getTimeProcessingLogFile() {
        return timeProcessingLogFile;
    }

    public Duration getTimeBetweenFirstLastLog() {
        return timeBetweenFirstLastLog;
    }

    public HashMap<LogLevel, Integer> getLogLevelsOccurrences() {
        return logLevelsOccurrences;
    }

    public int getLogLevelOccurrence(LogLevel logLevel) {
        return logLevelsOccurrences.get(logLevel);
    }

    public int getLogTotalNumber() {
        return logTotalNumber;
    }

    public HashMap<String, Integer> getLibrariesOccurrences() {
        return librariesOccurrences;
    }
    public int getLibraryOccurrence(String library) {
        return librariesOccurrences.get(library);
    }
}
