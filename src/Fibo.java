import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String Name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Entrer la valeur : ");
        int nombre = scanner.nextInt();
        int resultat = 0;
        scanner.nextLine();
        if(nombre == 0)
        {
            resultat = 0;
        }
        else if(nombre == 1)
        {
            resultat = 1;
        }
        else
        {
            int n1 = 1;
            int n2 = 0;

            for(int i = 2; i <= nombre; i++)
            {
                resultat = n1+n2;
                n2 = n1;
                n1 = resultat;
            }
        }
        System.out.println("resultat : " + resultat);
        return false;
    }
}
