import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Freq implements  Command{
    @Override
    public String Name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Entrer le chemin du fichier : ");
        String path = scanner.nextLine();
        String text;
        try {
            text = Files.readString(Paths.get(path));
        } catch (IOException e) {
            System.out.print("Unreadable file : IOException ");
            e.printStackTrace();
            return false;
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
        return false;
    }
}
