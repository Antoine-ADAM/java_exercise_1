import commands.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("bienvenue");
        Scanner scanner = new Scanner(System.in);
        List<Command> commands = new ArrayList<>();
        commands.add(new Fibo());
        commands.add(new Freq());
        commands.add(new Quit());
        commands.add(new Predict());
        String response;
        boolean found;
        while (true){
            System.out.print("command:");
            response = scanner.nextLine().toLowerCase();
            found = false;
            for (Command command : commands){
                if (command.name().equals(response)){
                    if (command.run(scanner)){
                        return;
                    }
                    found = true;
                }
            }
            if (!found){
                System.out.println("Unknown command");
            }
        }
    }
}
