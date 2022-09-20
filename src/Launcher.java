import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("bienvenue, command:");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if(!response.equals("quit")){
            System.out.println("Unknown command");
        }
    }
}
