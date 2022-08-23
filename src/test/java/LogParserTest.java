import core.LogParser;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LogParserTest {

    @Test
    void parse() {
        LogParser logParser = new LogParser(Paths.get("src/main/resources/server.txt"));
        logParser.parse();
    }
}
