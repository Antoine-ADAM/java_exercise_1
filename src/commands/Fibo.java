package commands;

import java.util.Scanner;

public class Fibo implements Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public boolean run(Scanner scanner) {
        System.out.print("n=");
        try{
            int n = scanner.nextInt();
            scanner.nextLine();
            int res = fibo(n);
            System.out.println(res);
        }catch (Exception e){
            System.out.println("n n'est pas valide !");
        }
        return false;
    }
    public int fibo(int n){
        if (n == 0){
            return 0;
        }
        int a = 0;
        int b = 1;
        int tmp;
        for (int i = 1; i < n; ++i){
            tmp = a;
            a = b;
            b += tmp;
        }
        return b;
    }
}
