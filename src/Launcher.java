import java.util.Scanner;

import static java.lang.System.exit;

public class Launcher {
    public static void main(String args[]){
        System.out.println("Bienvenue");
        Scanner scanner = new Scanner(System.in);
        String saisie = scanner.nextLine();
        if(saisie.equals("quit"))
        {
            exit(0);
        }
        else{
            System.out.println("Unknown command");
        }
    }
}
