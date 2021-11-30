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
            text = Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Unreadable file : IOException ");
            e.printStackTrace();
            return -1;
        }

        // Récupération des mots
        text = text.replaceAll("[,!.?:;(){}\n\r]", " ");
        text = text.toLowerCase(Locale.ROOT);
        String [] stringTab= text.split(" ");
        Predicate<String> predicate = s -> !(s.isEmpty());

        // Regroupement des mots pour les compter
        Map<String, Long> WordMap = Arrays.stream(stringTab).filter(predicate).collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        // Trie des mots
        List<Map.Entry<String, Integer>> WordList = new ArrayList(WordMap.entrySet());
        WordList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Séléction des 3 mots les plus réccurrent
        WordList = WordList.stream().limit(3).collect(Collectors.toList());
        WordList.forEach((Map.Entry<String, Integer> element)-> {
            System.out.println(element.getKey() + " : " + element.getValue());
        });
        return 0;
    }
}
