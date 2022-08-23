
import core.Sort;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class MainTest {

    @Test
    void sortFilesInDescendingOrder() throws IOException {
        Path dirPath = Paths.get("src/main/resources");
        for (Path logPath : Sort.sortFiles(dirPath)) {
            System.out.println(logPath.toString());
        }
    }
}