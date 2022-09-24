package commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Freq implements Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("Chemin du fichier a analyser: ");
        String path = scanner.nextLine();
        freq(path);
        return false;
    }
    private void freq(String path) {
        try{
            String content = Files.readString(Paths.get(path));
            var keys = Arrays.stream(content.replaceAll("[,.;:?'\\s]"," ").toLowerCase().split(" "));
            var map = keys.collect(Collectors.groupingBy(String::toString, Collectors.counting()));
            map.remove("");
            StringBuilder out= new StringBuilder();
            for (int i = 0; i < 3; ++i){
                var select = map.entrySet().stream().max(Map.Entry.comparingByValue());
                if (select.isPresent()){
                    out.append(select.get().getKey()).append(" ");
                    map.remove(select.get().getKey());
                }
            }
            if (out.length() > 0){
                System.out.println(out.substring(0, out.length()-1));
            }
        }catch (IOException e){
            System.out.println("Unreadable file: "+e.getClass().getName()+" "+e.getMessage());
        }
    }
}
