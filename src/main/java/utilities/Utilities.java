package utilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class Utilities {

    public static String readFileAsString(Path filePath) throws Exception {
        return new String(Files.readAllBytes(filePath));
    }
    public static String formatDuration(Duration d) {
        return String.format("%2d years, %02d months, %02d days, %02d hours, %02d minutes, %02d seconds, %03d milliseconds",
                d.toDaysPart() / 366,
                d.toDaysPart() % 366 / 30,
                d.toDaysPart() % 366 % 30,
                d.toHoursPart(),
                d.toMinutesPart(),
                d.toSecondsPart(),
                d.toMillisPart());
    }

    public static String formatSmaleDuration(Duration d) {
        return String.format("%02d seconds, %03d milliseconds",
                d.toSecondsPart(),
                d.toMillisPart());
    }

}
