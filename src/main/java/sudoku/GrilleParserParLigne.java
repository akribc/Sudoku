package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GrilleParserParLigne {
    
 
    public static char[][] GRID_TO_SOLVE = new char[9][9];
    private char[][] board;
    public static final int EMPTY = '@'; // empty cell
    public static final int SIZE = 9; // la taille de nos grilles de Sudoku
    
    public GrilleParserParLigne(char[][] board)  {
        board = fileToArray("sudoku1.txt");
        this.board = new char[SIZE][SIZE];
        
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = board[i][j];
            }
        }
    }
    
    // on v�rifie si un nombre possible est d�j� dans une rang�e
    public boolean isInRow(int row, char number) {
        for (int i = 0; i < SIZE; i++)
            if (board[row][i] == number)
                return true;
        
        return false;
    }
    
    // we check if a possible number is already in a column
    public boolean isInCol(int col, char number) {
        for (int i = 0; i < SIZE; i++)
            if (board[i][col] == number)
                return true;
        
        return false;
    }
    
    // on v�rifie si un nombre possible est dans sa bo�te 3x3
    public boolean isInBox(int row, int col, char number) {
        int r = row - row % 3;
        int c = col - col % 3;
        
        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (board[i][j] == number)
                    return true;
        
        return false;
    }
    
    // M�thode combin�e pour v�rifier si un nombre possible � une position row,col est correct.
    public boolean isOk(int row, int col, char number) {
        return !isInRow(row, number)  &&  !isInCol(col, number)  &&  !isInBox(row, col, number);
    }
    
    // M�thode de r�solution. Nous utiliserons un algorithme r�cursif de BackTracking.
       public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
         for (int col = 0; col < SIZE; col++) {
          // nous cherchons une cellule vide
          if (board[row][col] == EMPTY) {
            // nous essayons des nombres possibles
            for (int number = 1; number <= SIZE; number++) {
              char  numbeer =  Character.forDigit(number , 10);  
              if (isOk(row, col, numbeer)) {
                board[row][col] = numbeer;
                if (solve()) { // nous commen�ons � revenir en arri�re de fa�on r�cursive
                  return true;
                } else { // si ce n'est pas une solution, on vide la cellule et on continue
                  board[row][col] = EMPTY;
                }
             }
            }

            return false; // we return false
           }
          }
         }

         return true; // sudoku r�solu
    }
    
    public void display() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j]);
            }
        
            System.out.println();
        }
        
        System.out.println();
    }
    
    public static char[][] fileToArray(String filename)  {
        
        Scanner sc;
        int rows = 9;
        int columns = 9;
        char[][] grille = new char[rows][columns];
        try {
            sc = new Scanner(new BufferedReader(new FileReader(filename)));
           
            while(sc.hasNextLine()) {
               for (int i=0; i<columns; i++) {
                  String[] line = sc.nextLine().trim().split(" ");
                  for (int j=0; j<line.length; j++) {
                      grille[i][j] = line[j].charAt(0);
                  }
               }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

       
      
        
        return grille;
    }
    public static void main(String[] args) throws FileNotFoundException {
        
        
        GrilleParserParLigne sudoku = new GrilleParserParLigne(GRID_TO_SOLVE);
        System.out.println("Grille de Sudoku � r�soudre");
        sudoku.display();
        
        // nous essayons la r�solution
        if (sudoku.solve()) {
            System.out.println("Grille de Sudoku r�solue avec un simple BT");
            sudoku.display();
        } else {
            System.out.println("Impossible � r�soudre");
        }
    }

}
