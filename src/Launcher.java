import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                else if(saisie.equals("freq")){
                    Launcher.ThreeWords();
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

    public static int ThreeWords() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrer le chemin du fichier : ");
        String path = scanner.nextLine();
        String text;
        try {
            text = java.nio.file.Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Unreadable file : IOException ");
            e.printStackTrace();
            return -1;
        }
        text = text.replaceAll("[,!.?:;(){}]", " ");
        text = text.toLowerCase(Locale.ROOT);
        String [] stringTab= text.split(" ");
        for(int i = 0; i < stringTab.length; i++)
        {
            System.out.println(stringTab[i]);
        }
        Map<String, Long> ListeDeMot = Arrays.stream(stringTab).collect(Collectors.groupingBy(p -> p, Collectors.counting()));
        return 0;
    }
}
