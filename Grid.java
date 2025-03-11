import java.util.Random;
// video link:  https://youtu.be/OxQwM_AH8Ig
class Grid{
    boolean [][]bombGrid;
    int [][] countGrid;
    int numRows;
    int numColumns;
    int numBombs ;
    
    public Grid(){
        bombGrid = new boolean[10][10];
        countGrid = new int[10][10];
        this.numRows = 10;
        this.numColumns = 10;
        this.numBombs = 25;
        createBombGrid();
        createCountGrid();
    }
    public Grid(int row, int columns){
        bombGrid = new boolean[row][columns];
        countGrid = new int[row][columns];
        this.numRows = row;
        this.numColumns = columns;
        this.numBombs = 25;
        createBombGrid();
        createCountGrid();
    }
   public Grid(int row, int columns, int bombs){
        bombGrid = new boolean[row][columns];
        countGrid = new int[row][columns]; // unchecked could cause error
        this.numRows = row;
        this.numColumns = columns;
        this.numBombs = bombs;
        createBombGrid();
        createCountGrid();
    }

    public int getNumRows(){
        return this.numRows;
    }
    public int getNumColumns(){
        return this.numColumns;
    }
    public int getNumBombs(){
        return this.numBombs;
    }
    public boolean [][] getBombGrid(){
        boolean [][] copy = new boolean[this.getNumRows()][this.getNumColumns()] ;
        for (int i = 0 ; i < this.getNumRows();i++){
            for(int j = 0 ; j < this.getNumColumns();j++){
                copy[i][j] = bombGrid[i][j];
            }
        }
        return copy;
    }

    public int [][] getCountGrid(){
        int [][] copy = new int[this.getNumRows()][this.getNumColumns()] ;
        for (int i = 0 ; i < this.getNumRows();i++){
            for(int j = 0 ; j < this.getNumColumns();j++){
                copy[i][j] = countGrid[i][j];
            }
        }
        return copy;
    }
    public boolean isBombAtLocation(int row, int col){
        return bombGrid[row][col]  ;
    }

    public int getCountAtLocation(int row, int col){
        
        return countGrid[row][col] ; 
    }

    private void createBombGrid() {
        
        Random random = new Random();

        int bombsPlaced = 0;
        while (bombsPlaced < this.numBombs) {
            int row = random.nextInt(numRows);
            int col = random.nextInt(numColumns);

            if (!bombGrid[row][col]) {
                bombGrid[row][col] = true;
                bombsPlaced++;
            }
        }
    }

    private int countAdj(int row, int col) {
        int count = 0;
    
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < bombGrid.length && j >= 0 && j < bombGrid[i].length) {
                    if (bombGrid[i][j]) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    private void createCountGrid(){
        //(25 pts) createCountGrid() Creates the int 10 x 10 countGrid shown on the right based on the bomb placement in the bombGrid     
        for(int row = 0 ; row < bombGrid.length ; row ++){
            for(int col = 0 ; col < bombGrid[row].length ; col ++ ){
                countGrid[row][col] = countAdj(row, col) ; 
            }
        }
    }


}