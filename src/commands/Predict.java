package commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Chemin du fichier a analyser: ");
        String path = scanner.nextLine();
        System.out.print("Premier mot: ");
        String first = scanner.nextLine();
        predict(path, first);
        return false;
    }
    private void predict(String path, String first) {
        try{
            String content = Files.readString(Paths.get(path));
            var keys = Arrays.stream(content.replaceAll("[,.;:?()\\[\\]'\\s]"," ").toLowerCase().split(" +")).toList();
            String[] uniques = keys.stream().distinct().toArray(String[]::new);
            HashMap<Integer, Integer>[] nextKeys = new HashMap[uniques.length];
            if (keys.size() < 1)
                return;
            int indexActual = findIndex(uniques, keys.get(0));
            String[] keysArray = keys.toArray(String[]::new);
            for (int i = 1; i < keysArray.length; ++i){
                int indexNext = findIndex(uniques, keysArray[i]);
                if (nextKeys[indexActual] == null){
                    nextKeys[indexActual] = new HashMap<>();
                }
                if (nextKeys[indexActual].containsKey(indexNext)){
                    nextKeys[indexActual].put(indexNext, nextKeys[indexActual].get(indexNext)+1);
                }else{
                    nextKeys[indexActual].put(indexNext, 1);
                }
                indexActual = indexNext;
            }

            StringBuilder out= new StringBuilder();
            final int limit = 20;
            out.append(first.toLowerCase());
            int index = findIndex(uniques, first.toLowerCase());
            if (index == -1){
                System.out.println("Le mot n'est pas dans le texte");
                return;
            }
            for (int i = 1; i < limit; ++i){
                if (nextKeys[index] == null){
                    break;
                }
                var select = nextKeys[index].entrySet().stream().max(Map.Entry.comparingByValue());
                if (select.isPresent()){
                    index = select.get().getKey();
                    out.append(" ").append(uniques[index]);
                }else {
                    break;
                }
            }
            if (out.length() > 0){
                System.out.println(out);
            }



        }catch (IOException e){
            System.out.println("Unreadable file: "+e.getClass().getName()+" "+e.getMessage());
        }
    }

    private int findIndex(String[] uniques, String s) {
        for (int i = 0; i < uniques.length; ++i){
            if (uniques[i].equals(s)){
                return i;
            }
        }
        return -1;
    }
}
