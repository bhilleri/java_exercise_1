import java.util.Scanner;

import static java.lang.System.exit;

public class Launcher {
    public static void main(String args[]){
        System.out.println("Bienvenue");
        Scanner scanner = new Scanner(System.in);
        String saisie = null;
        do {
            if(saisie != null)
            {
                System.out.println("Unknown command");
            }
            saisie = scanner.nextLine();
        }while(! saisie.equals("quit"));

    }
}
