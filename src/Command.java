import java.util.Scanner;

public interface Command {
    public abstract String  Name();
    public abstract boolean run(Scanner scanner);
}
