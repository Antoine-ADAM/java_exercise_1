import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static int fibo(int n){
        int f0 = 0;
        int f1 = 1;
        int fn = -1;
        if (n == 0){
            fn = f0;
        }
        if (n == 1){
            fn = f1;
        }
        for (int k = 2; k <= n; ++k){
            fn = f1 + f0;
            f0 = f1;
            f1 = fn;
        }
        return fn;
    }
    public static void main(String[] args) {
        System.out.println("bienvenue");
        Scanner scanner = new Scanner(System.in);
        String response;
        while (true){
            System.out.print("command:");
            response = scanner.nextLine().toLowerCase();
            switch (response){
                case "quit":
                    return;
                case "fibo":
                    System.out.print("n=");
                    try{
                        int n = scanner.nextInt();
                        scanner.nextLine();
                        int res = Launcher.fibo(n);
                        System.out.println(res);
                    }catch (Exception e){
                        System.out.println("n n'est pas valide !");
                    }
                    break;
                case "freq":
                    System.out.print("Chemin du fichier a analyser: ");
                    String path = scanner.nextLine();
                    Launcher.freq(path);
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }

    private static void freq(String path) {
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
            out.deleteCharAt(out.length()-1);
            System.out.println(out);
        }catch (IOException e){
            System.out.println("Unreadable file: "+e.getClass().getName()+" "+e.getMessage());
        }
    }
}
