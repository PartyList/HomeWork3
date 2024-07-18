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
        //Game game = new Game(3, 3, 3, 2, 1);
        //game.runGame();
        Board board = new Board(4,4,20,2131);
        System.out.println(board);
        board.nextGeneration();
        System.out.println(board);
    }
}
