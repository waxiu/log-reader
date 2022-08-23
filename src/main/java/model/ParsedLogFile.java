package model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ParsedLogFile {
    private final Path logFilePath;
    private final List<LogEntry> logEntries = new ArrayList<>();

    public ParsedLogFile(Path logFilePath) {
        this.logFilePath = logFilePath;
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public Path getLogFilePath() {
        return logFilePath;
    }
}
