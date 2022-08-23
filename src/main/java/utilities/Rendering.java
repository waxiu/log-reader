package utilities;

import model.LogFileAnalytics;
import model.LogLevel;

import static utilities.Utilities.formatDuration;

public class Rendering {
    public static void render(LogFileAnalytics analytics) {
        System.out.println("\nProcessing file:");
        System.out.println("\t" + analytics.getLogFilePath());
        System.out.println("Time taken in processing:");
        System.out.println("\t" + Utilities.formatSmaleDuration(analytics.getTimeProcessingLogFile()));
        System.out.println("Time difference between first and last log:");
        System.out.println("\t" + formatDuration(analytics.getTimeBetweenFirstLastLog()));

        System.out.println("Number of logs based on severity:");
        for (LogLevel logLevel : analytics.getLogLevelsOccurrences().keySet()) {
            System.out.printf("\t%-6s %d%n", logLevel + ":", analytics.getLogLevelOccurrence(logLevel));
        }
        System.out.println("Ratio of logs with severity:");
        double ratio = 0;
        for (LogLevel logLevel : LogLevel.values()) {
            if (LogLevel.ERROR.compareTo(logLevel) <= 0) ratio += analytics.getLogLevelOccurrence(logLevel);
        }
        System.out.printf("\t%-6s %f%%%n", "ERROR or higher" + ":", 100 * ratio / analytics.getLogTotalNumber());
        System.out.println("Number of unique library occurrences in logs:");
        for (String library : analytics.getLibrariesOccurrences().keySet()) {
            System.out.println("\t" + library + " " + analytics.getLibraryOccurrence(library));
        }
        System.out.println("******************************************************************");
    }

}
