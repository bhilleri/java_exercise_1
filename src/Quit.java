import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Quit implements Command {
    @Override
    public String Name() {
        return "quit";
    }

    @Override
    public boolean run(Scanner scanner) {
        return true;
    }
}
