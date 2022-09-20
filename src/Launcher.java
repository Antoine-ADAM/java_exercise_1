import java.util.Scanner;

public class Launcher {
    public static int fibo(int n){
        if(n<=1){
            return n;
        }else {
            return fibo(n-1) + fibo(n-2);
        }
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
                    if(scanner.hasNextInt()){
                        int n = scanner.nextInt();
                        scanner.nextLine();
                        int res = Launcher.fibo(n);
                        System.out.println(res);
                    }else
                        scanner.nextLine();
                    break;
                default:
                    System.out.println("Unknown command");
                    break;
            }
        }
    }
}
