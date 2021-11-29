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
                if(saisie.equals("fibo"))
                {
                    System.out.print("Entrer la valeur : ");
                    int nombre = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(Launcher.Fibonacci(nombre));
                }
                else{
                    System.out.println("Unknown command");
                }
            }
            saisie = scanner.nextLine();
        }while(! saisie.equals("quit"));
    }

    public static int Fibonacci(int nombre)
    {
        if(nombre == 0)
        {
            return 0;
        }
        if(nombre == 1)
        {
            return 1;
        }
        int n1 = 1;
        int n2 = 0;
        int resultat = 0;
        for(int i = 2; i <= nombre; i++)
        {
            resultat = n1+n2;
            n2 = n1;
            n1 = resultat;
        }
        return resultat;
    }
}
