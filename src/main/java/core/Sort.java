package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Sort {

    public static List<Path> sortFiles(Path dirPath) throws IOException {
        List<Path> sortedPaths;
        try (Stream<Path> walk = Files.walk(dirPath, 1)) {
            sortedPaths = walk.filter(Files::isRegularFile)
                    .sorted(Comparator.comparingLong(p -> p.toFile().lastModified()))
                    .collect(Collectors.toList());
        }
        return sortedPaths;
    }
}
