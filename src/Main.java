import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Test 1 starts");
            test1();
            System.out.println("Test 1 done");
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            System.out.println("exception " + e);
        }
    }

    public static void test1(){
        Game game = new Game(3, 3, 3, 2, 1000);
        game.runGame();






    }
}
