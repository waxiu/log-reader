package core;

import model.LogEntry;
import model.LogFileAnalytics;
import model.LogLevel;
import model.ParsedLogFile;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class LogProcessor {
    private final ParsedLogFile parsedLogFile;
    private final long logFileParsingTime;
    private final List<LogEntry> logEntries;

    public LogProcessor(ParsedLogFile parsedLogFile, long logFileParsingTime) {
        this.parsedLogFile = parsedLogFile;
        this.logFileParsingTime = logFileParsingTime;
        this.logEntries = parsedLogFile.getLogEntries().
                stream().
                sorted(Comparator.comparing(LogEntry::getTime)).
                collect(Collectors.toList());
    }
    public LogFileAnalytics process() {

        return new LogFileAnalytics(parsedLogFile.getLogFilePath(),
                timeBetweenFirstLastLog(),
                Duration.ofMillis(logFileParsingTime),
                getLogLevelsOccurrences(),
                getLibraryOccurrences(),
                logEntries.size()

        );
    }

    private HashMap<String, Integer> getLibraryOccurrences() {
        Set<String> libraries = new HashSet<>();
        logEntries.forEach(logEntry -> libraries.add(logEntry.getLibrary()));
        HashMap<String, Integer> logLibraryOccurrences = new HashMap<>();
        libraries.forEach(lib -> logLibraryOccurrences.put(lib,
                (int) logEntries.stream().filter(logEntry -> logEntry.getLibrary().equals(lib)).count()
        ));
        return logLibraryOccurrences;
    }

    private HashMap<LogLevel, Integer> getLogLevelsOccurrences() {
        HashMap<LogLevel, Integer> logLevelsOccurrences = new HashMap<>();
        for (LogLevel logLevel : LogLevel.values())
            logLevelsOccurrences.put(logLevel,
                    (int) logEntries.stream().filter(logEntry -> logEntry.getLevel() == logLevel).count());
        return logLevelsOccurrences;
    }

    private Duration timeBetweenFirstLastLog() {
        LocalDateTime firstEntryTime = logEntries.get(0).getTime();
        LocalDateTime lastEntryTime = logEntries.get(logEntries.size() - 1).getTime();
        return Duration.between(firstEntryTime, lastEntryTime);
    }
}
