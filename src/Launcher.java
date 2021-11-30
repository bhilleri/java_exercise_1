import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.stream.Stream;

import static java.lang.System.exit;

public class Launcher {
    public static void main(String args[]){
        System.out.println("Bienvenue");
        Scanner scanner = new Scanner(System.in);
        String saisie = null;
        List <Command> CommandList = new ArrayList<Command>();
        CommandList.add(new Fibo());
        CommandList.add(new Freq());
        CommandList.add(new Quit());
        CommandList.add(new Predict());

        boolean end = false;
        do {
            saisie = scanner.nextLine();
            int selection = -1;
            if(saisie != null)
            {
                for(int i = 0; i < CommandList.size(); i++)
                {
                    if(CommandList.get(i).Name().equals(saisie))
                    {
                        selection = i;
                    }
                }
                if(selection == -1)
                {
                    System.out.println("Unknown command");
                    end = false;
                }
                else{
                    end = CommandList.get(selection).run(scanner);
                }
            }
            else{
                end = false;
            }

        }while(end == false);
    }

}
