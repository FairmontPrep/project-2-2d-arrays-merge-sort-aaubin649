import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];
    public String[][] piecesArray;

    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        piecesArray = new String[][]{
            {"W_Knight.png", "7"}, {"W_Bishop.png", "6"}, {"W_Rook.png", "8"}, {"W_Queen.png", "4"},
            {"W_King.png", "5"}, {"W_Bishop.png", "3"}, {"W_Knight.png", "2"}, {"W_Rook.png", "1"},
            {"W_Pawn.png", "14"}, {"W_Pawn.png", "9"}, {"W_Pawn.png", "12"}, {"W_Pawn.png", "11"},
            {"W_Pawn.png", "10"}, {"W_Pawn.png", "16"}, {"W_Pawn.png", "15"}, {"W_Pawn.png", "13"},
            {"B_Pawn.png", "52"}, {"B_Pawn.png", "49"}, {"B_Pawn.png", "50"}, {"B_Pawn.png", "56"},
            {"B_Pawn.png", "55"}, {"B_Pawn.png", "51"}, {"B_Pawn.png", "53"}, {"B_Pawn.png", "54"},
            {"B_Rook.png", "64"}, {"B_Knight.png", "63"}, {"B_Bishop.png", "62"}, {"B_Queen.png", "60"},
            {"B_King.png", "61"}, {"B_Bishop.png", "59"}, {"B_Knight.png", "58"}, {"B_Rook.png", "57"}
        };

        mergeSort(piecesArray, 0, piecesArray.length - 1);
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                squares[row][col].setBackground((row + col) % 2 == 0 ? new Color(100, 69, 100) : new Color(255, 255, 255));
                add(squares[row][col]);
            }
        }

        for (String[] piece : piecesArray) {
            int piecePosition = Integer.parseInt(piece[1]) - 1;
            int pieceRow = piecePosition / SIZE;
            int pieceCol = piecePosition % SIZE;
            ImageIcon pieceIcon = new ImageIcon(piece[0]);
            Image scaledImage = pieceIcon.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH);
            JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
            squares[pieceRow][pieceCol].add(pieceLabel, BorderLayout.CENTER);
        }
    }

    private void mergeSort(String[][] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(String[][] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[][] leftArray = new String[n1][2];
        String[][] rightArray = new String[n2][2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (Integer.parseInt(leftArray[i][1]) <= Integer.parseInt(rightArray[j][1])) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}
