import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.HashMap;

public class Predict implements Command{
    @Override
    public String Name() {
        return "predict";
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

        // Retrait des mots vides
        List<String> stringList = Arrays.stream(stringTab).filter(predicate).collect(Collectors.toList());

        // Création d'une map rassemblant pour chaque mot la liste des mots qui le suive
        Map<String, List<String>> NextWord = new HashMap<>();
        for(int i = 0; i < stringList.size() -1; i++)
        {
            NextWord.putIfAbsent(stringList.get(i), new ArrayList<>());
            NextWord.get(stringList.get(i)).add(stringList.get(i+1));
        }
        Map<String, String> ListMostProbableWord = new HashMap<>();
        NextWord.forEach((key,listTargetWord)->{
            Map<String, Long> WordMap = listTargetWord.stream().collect(Collectors.groupingBy(p -> p, Collectors.counting()));
            List<Map.Entry<String, Long>> WordList = new ArrayList(WordMap.entrySet());
            Optional<Map.Entry<String, Long>> max = WordList.stream().max(Map.Entry.comparingByValue(Comparator.comparingLong(Long::longValue)));
            ListMostProbableWord.put(key, max.get().getKey());
        });
        System.out.print("Entrer le mot : ");
        String word = scanner.nextLine().toLowerCase(Locale.ROOT);
        if(ListMostProbableWord.containsKey(word))
        {
            String currentWord = word;
            System.out.print(currentWord + " ");
            for(int i=0; i < 19; i++)
            {
                if(ListMostProbableWord.containsKey(currentWord)){
                    currentWord = ListMostProbableWord.get(currentWord);
                    System.out.print(currentWord + " ");
                }
            }
        }
        else
        {
            System.out.println("Impossible de trouver un successeur pour ce mot : " + word);
        }
        return false;
    }
}
