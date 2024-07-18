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
        Game game = new Game(3, 3, 3, 2, 1);
        game.runGame();
        Random rand = new Random(2);

        System.out.print(rand.nextInt(3));
        System.out.print(rand.nextInt(3));
        System.out.println(rand.nextInt(3));
        System.out.print(rand.nextInt(3));
        System.out.print(rand.nextInt(3));
        System.out.println(rand.nextInt(3));
        System.out.print(rand.nextInt(3));
        System.out.print(rand.nextInt(3));
        System.out.println(rand.nextInt(3));




    }
}
