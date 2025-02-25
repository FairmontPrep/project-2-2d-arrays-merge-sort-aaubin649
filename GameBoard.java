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

        piecesArray = new String[32][2]; 
        loadPieces(); 

        System.out.println("Unsorted piecesArray:");
        for (int i = 0; i < piecesArray.length; i++) {
            System.out.println(piecesArray[i][0] + " at " + piecesArray[i][1]);
        }

        sortPieces();
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(100, 69, 100));
                } else {
                    squares[row][col].setBackground(new Color(255, 255, 255));
                }
                
                for (int i = 0; i < piecesArray.length; i++) {
                    int piecePosition = Integer.parseInt(piecesArray[i][1]);
                    int pieceRow = (piecePosition - 1) / SIZE;
                    int pieceCol = (piecePosition - 1) % SIZE;
    
                    if (row == pieceRow && col == pieceCol) {
                        ImageIcon pieceIcon = new ImageIcon(piecesArray[i][0]);
                        Image scaledImage = pieceIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));
                        squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    }
                }
    
                add(squares[row][col]);
            }
        }
    }
    
    private void loadPieces() {
        piecesArray[0] = new String[]{"W_Knight.png", "12"};
        piecesArray[1] = new String[]{"B_King.png", "41"};
        piecesArray[2] = new String[]{"W_Pawn.png", "14"};
        piecesArray[3] = new String[]{"B_Bishop.png", "47"};
        piecesArray[4] = new String[]{"W_Queen.png", "3"};
        piecesArray[5] = new String[]{"B_Pawn.png", "50"};
        piecesArray[6] = new String[]{"W_Bishop.png", "6"};
        piecesArray[7] = new String[]{"W_Pawn.png", "10"};
        piecesArray[8] = new String[]{"B_Rook.png", "57"};
        piecesArray[9] = new String[]{"B_Pawn.png", "40"};
        piecesArray[10] = new String[]{"W_King.png", "5"};
        piecesArray[11] = new String[]{"W_Pawn.png", "9"};
        piecesArray[12] = new String[]{"B_Knight.png", "63"};
        piecesArray[13] = new String[]{"B_Pawn.png", "37"};
        piecesArray[14] = new String[]{"W_Rook.png", "1"};
        piecesArray[15] = new String[]{"B_Queen.png", "60"};
        piecesArray[16] = new String[]{"W_Pawn.png", "16"};
        piecesArray[17] = new String[]{"B_Pawn.png", "34"};
        piecesArray[18] = new String[]{"B_Pawn.png", "55"};
        piecesArray[19] = new String[]{"B_Knight.png", "62"};
        piecesArray[20] = new String[]{"W_Pawn.png", "13"};
        piecesArray[21] = new String[]{"W_Bishop.png", "7"};
        piecesArray[22] = new String[]{"B_Pawn.png", "39"};
        piecesArray[23] = new String[]{"W_Pawn.png", "15"};
        piecesArray[24] = new String[]{"B_Bishop.png", "59"};
        piecesArray[25] = new String[]{"W_Rook.png", "8"};
        piecesArray[26] = new String[]{"B_Rook.png", "56"};
        piecesArray[27] = new String[]{"B_Pawn.png", "38"};
        piecesArray[28] = new String[]{"W_Knight.png", "2"};
        piecesArray[29] = new String[]{"B_Pawn.png", "36"};
        piecesArray[30] = new String[]{"B_Pawn.png", "35"};
        piecesArray[31] = new String[]{"B_Rook.png", "58"};
    }
    
    private void sortPieces() {
        mergeSort(piecesArray, 0, piecesArray.length - 1);
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
    
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }
    
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
