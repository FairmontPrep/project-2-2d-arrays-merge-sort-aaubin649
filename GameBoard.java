import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameBoard extends JFrame {
    private static final int SIZE = 8;
    private JPanel[][] squares = new JPanel[SIZE][SIZE];

    public String[][] piecesArray;

    public GameBoard() {
        setTitle("Chess Board");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        // Initialize the piecesArray to store image and position information
        piecesArray = new String[32][2]; // Each row: [imagePath, position]
        loadPieces(); // Load the pieces with their corresponding images

        // Print the contents of the 2D array to check if it is unsorted initially
        System.out.println("Unsorted piecesArray:");
        for (int i = 0; i < piecesArray.length; i++) {
            for (int j = 0; j < piecesArray[i].length; j++) {
                System.out.println("piecesArray[" + i + "][" + j + "] = " + piecesArray[i][j]);
            }
        }

        initializeBoard();
    }

    private void initializeBoard() {
        // Sort the pieces array before placing pieces on the board
        sortPieces();
    
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                squares[row][col] = new JPanel(new BorderLayout());
    
                // Creates the checkered pattern with two colors
                if ((row + col) % 2 == 0) {
                    squares[row][col].setBackground(new Color(100, 69, 100)); // Dark Brown
                } else {
                    squares[row][col].setBackground(new Color(255, 255, 255)); // White
                }
    
                // Adding pieces to the board based on their position
                for (int i = 0; i < piecesArray.length; i++) {
                    int piecePosition = Integer.parseInt(piecesArray[i][1]);
                    int pieceRow = (piecePosition - 1) / SIZE;
                    int pieceCol = (piecePosition - 1) % SIZE;
    
                    if (row == pieceRow && col == pieceCol) {
                        ImageIcon pieceIcon = new ImageIcon(piecesArray[i][0]); // Load the image from the array
                        Image scaledImage = pieceIcon.getImage().getScaledInstance(70, 80, Image.SCALE_SMOOTH); // Width height chess pieces
                        JLabel pieceLabel = new JLabel(new ImageIcon(scaledImage));

                        squares[row][col].add(pieceLabel, BorderLayout.CENTER);
                    }
                }
    
                add(squares[row][col]);
            }
        }
    }
    
    private void loadPieces() {
        // Load images for white pieces with corresponding position info
        piecesArray[0] = new String[]{"W_Rook.png", "1"};
        piecesArray[1] = new String[]{"W_Knight.png", "2"};
        piecesArray[2] = new String[]{"W_Bishop.png", "3"};
        piecesArray[3] = new String[]{"W_Queen.png", "4"};
        piecesArray[4] = new String[]{"W_King.png", "5"};
        piecesArray[5] = new String[]{"W_Bishop.png", "6"};
        piecesArray[6] = new String[]{"W_Knight.png", "7"};
        piecesArray[7] = new String[]{"W_Rook.png", "8"};
        for (int i = 8; i < 16; i++) {
            piecesArray[i] = new String[]{"W_Pawn.png", String.valueOf(i + 1)};
        }
    
        // Load images for black pieces with corresponding position info
        for (int i = 16; i < 24; i++) {
            piecesArray[i] = new String[]{"B_Pawn.png", String.valueOf(i + 33)};
        }
        piecesArray[24] = new String[]{"B_Rook.png", "57"};
        piecesArray[25] = new String[]{"B_Knight.png", "58"};
        piecesArray[26] = new String[]{"B_Bishop.png", "59"};
        piecesArray[27] = new String[]{"B_Queen.png", "60"};
        piecesArray[28] = new String[]{"B_King.png", "61"};
        piecesArray[29] = new String[]{"B_Bishop.png", "62"};
        piecesArray[30] = new String[]{"B_Knight.png", "63"};
        piecesArray[31] = new String[]{"B_Rook.png", "64"};
    }
    
    private void sortPieces() {
        // Sort the pieces array based on the position number (second column)
        Arrays.sort(piecesArray, (a, b) -> Integer.compare(Integer.parseInt(a[1]), Integer.parseInt(b[1])));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameBoard board = new GameBoard();
            board.setVisible(true);
        });
    }
}

