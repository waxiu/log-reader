package core;

import utilities.Utilities;
import model.LogEntry;
import model.LogLevel;
import model.ParsedLogFile;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
    private final Path logFilePath;
    private final ParsedLogFile parsedLogFile;

    public LogParser(Path logFilePath) {
        this.logFilePath = logFilePath;
        this.parsedLogFile = new ParsedLogFile(logFilePath);
    }

    public ParsedLogFile parse() {
        try {
            String logString = Utilities.readFileAsString(logFilePath);
            List<String> logEntriesAsString = splitLogEntries(logString);
            for (String logEntryAsStr : logEntriesAsString) {
                LogEntry logEntry = parseLogEntry(logEntryAsStr);
                if (logEntry != null)
                    parsedLogFile.getLogEntries().add(logEntry);

            }
            return parsedLogFile;
        } catch (Exception e) {
            System.out.println("problem with file: " + logFilePath.toString());
            return null;
        }
    }

    private List<String> splitLogEntries(String logString) {
        String regex = "^\\d{4}-\\d{2}-\\d{2}";
        Matcher matcher = Pattern.compile(regex, Pattern.MULTILINE).matcher(logString);

        List<Integer> matchesIndex = new ArrayList<>();
        while (matcher.find()) {
            matchesIndex.add(matcher.start());
        }
        Collections.reverse(matchesIndex);
        List<String> result = new ArrayList<>();
        for(Integer index : matchesIndex) {
            result.add(logString.substring(index));
            logString = logString.substring(0, index);
        }
        Collections.reverse(result);
        return result;
    }

    private LogEntry parseLogEntry(String logEntryAsString) {
        String regex = "([^\\s]*)\\s+([^\\s]*)\\s+([^\\s]*)\\s+([^\\s]*)\\s+(.*)";

        Matcher matcher = Pattern.compile(regex, Pattern.DOTALL).matcher(logEntryAsString);
        if(matcher.find()) {
            return new LogEntry(getTime(matcher.group(1) + " " + matcher.group(2)),
                    LogLevel.valueOf(matcher.group(3)), matcher.group(4), matcher.group(5));
        }
        return null;
    }

    private LocalDateTime getTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        long nanos = Long.parseLong(timeString.substring(20))*1000000;
        return LocalDateTime.parse(timeString.substring(0,19), formatter).plusNanos(nanos);
    }


}
