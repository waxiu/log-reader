package model;

import java.time.LocalDateTime;

public class LogEntry {
    private final LocalDateTime time;
    private final LogLevel level;
    private final String library;
    private final String message;

    public LogEntry(LocalDateTime time, LogLevel level, String library, String message) {
        this.time = time;
        this.level = level;
        this.library = library;
        this.message = message;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getLibrary() {
        return library;
    }

    public String getMessage() {
        return message;
    }
}
