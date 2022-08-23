package core;

import model.LogFileAnalytics;
import model.ParsedLogFile;
import utilities.Rendering;
import java.nio.file.Path;
import java.nio.file.Paths;
import utilities.*;

public class Main {

    public static void main(String[] args) {
        try {
            start();
        } catch (Exception e) {
            System.out.println("Please make sure to write a valid DirectoryPah in input/dirPath.txt");
        }
    }

    public static void start() throws Exception {
        Path dirPath = Paths.get(Utilities.readFileAsString(Paths.get("input/logDirPath.txt")));
        for(Path logFilePath : Sort.sortFiles(dirPath)) {
            long start = System.currentTimeMillis();
            LogParser logParser = new LogParser(logFilePath);
            ParsedLogFile parsedLogFile = logParser.parse();
            long end = System.currentTimeMillis();
            if (parsedLogFile == null) continue;
            long logFileParsingTime = end - start;
            LogProcessor logProcessor = new LogProcessor(parsedLogFile, logFileParsingTime);
            LogFileAnalytics logFileAnalytics = logProcessor.process();
            Rendering.render(logFileAnalytics);
        }
    }
}
