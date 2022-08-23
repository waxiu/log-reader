import org.junit.jupiter.api.Test;
import utilities.Utilities;

import java.nio.file.Paths;

class UtilitiesTest {

    @Test
    void readFileAsString() throws Exception {

        String fileContent = Utilities.readFileAsString(Paths.get("src/main/resources/server.txt"));
        System.out.println(fileContent);

    }
}