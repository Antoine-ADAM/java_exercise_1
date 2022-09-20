import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("bienvenue");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("command:");
            String response = scanner.nextLine();
            if(!response.equals("quit")){
                System.out.println("Unknown command");
            }else {
                return;
            }
        }
    }
}
