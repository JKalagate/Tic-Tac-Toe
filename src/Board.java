import java.util.Arrays;

public class Board {

    public char[] createBoard() {
        char[] board = new char[9];
        Arrays.fill(board, ' ');
        return board;
    }
}
