import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}

class Game {
    Player playerOne = new Player('X');
    Player playerTwo = new Player('O');
    Board board = new Board();
    private final char[] userInput = board.createBoard();
    List<Player> playerList = new ArrayList<>(Arrays.asList(playerOne, playerTwo));


    public void run() {
        System.out.println("Tic-Tac-Toe Game");
        boolean shouldBreak = false;
        while (!shouldBreak) {
            for (Player player : playerList) {

                if (!checkDraw()) {
                    showBoard();
                    System.out.println("Draw!");
                    shouldBreak = true;
                    break;
                }

                showBoard();
                playerMove(player);

                if (checkResult(player)) {
                    showBoard();
                    System.out.println(player.getSign() + " wins!");
                    shouldBreak = true;
                    break;
                }
            }
        }
    }

    private Boolean checkDraw() {
        ArrayList<Character> charList = new ArrayList<>();
        for (char c : userInput) {
            charList.add(c);
        }
        return charList.contains(' ');
    }


    private void showBoard() {
        int j = 1;
        System.out.println("    1 2 3  ");
        System.out.println("  ---------");
        for (int i = 0; i < 9; i += 3) {
            System.out.printf(j++ + " " + "| %c %c %c |%n", userInput[i], userInput[i + 1], userInput[i + 2]);
        }
        System.out.println("  ---------");
    }


    private void playerMove(Player player) {
        char[][] board = new char[3][3];

        //Create MD arrays
        int num = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = userInput[num++];
            }
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter two numbers:");
        while (true) {
            String[] userMove = scanner.nextLine().split(" ");
            int col;
            int row;

            if (userMove.length != 2) {
                System.out.println("Follow this format: \"num num\"!");
                continue;
            }

            try {
                col = Integer.parseInt(userMove[0]);
                row = Integer.parseInt(userMove[1]);
            } catch (NumberFormatException e) {
                System.out.println("You should enter numbers!");
                continue;
            }

            List<Integer> checkInput = Arrays.asList(1, 2, 3);
            if (!checkInput.contains(col) || !checkInput.contains(row)) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (board[col - 1][row - 1] != ' ') {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }

            board[col - 1][row - 1] = player.getSign();
            break;

        }

        // Back Md arrays to list
        int num2 = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                userInput[num2++] = board[i][j];
            }
        }
    }

    private Boolean checkResult(Player player) {
        char sign = player.getSign();
        //check row
        for (int i = 0; i < 9; i += 3) {
            if (userInput[i] == sign && userInput[i + 1] == sign && userInput[i + 2] == sign) return true;
        }
        //check col
        for (int i = 0; i < 3; i++) {
            if (userInput[i] == sign && userInput[i + 3] == sign && userInput[i + 6] == sign) return true;
        }
        //check slant
        if (userInput[0] == sign && userInput[4] == sign && userInput[8] == sign) return true;
        return userInput[2] == sign && userInput[4] == sign && userInput[6] == sign;
    }
}