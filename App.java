import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class App{
    Grid game;
    JFrame gameScreen = new JFrame();
    JButton [] board ;
    int emptySpaces ;
    App(){
     game= new Grid(10,10);
    emptySpaces = (game.getNumColumns() * game.getNumRows()) - game.getNumBombs() ;
    }

    public static void printMatrix(boolean[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }    
    }
    public void startGame(){
        game = new Grid();
        gameScreen.getContentPane().removeAll();
        gameScreen.repaint();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(10, 10));
        printMatrix(game.getBombGrid());
        board = new JButton[100];

        for (int i = 0 ; i < board.length;i++) {
        
        board[i] = new JButton();
        board[i].setFocusable(false);
        board[i].setName(Integer.toString(i));
        board[i].addActionListener(e->{
        String location = ((JComponent) e.getSource()).getName() ;
            makeMove(location);
;
        });
        buttonPanel.add(board[i]);

        }
        gameScreen.add(buttonPanel);
        gameScreen.repaint();
        SwingUtilities.updateComponentTreeUI(gameScreen);
    }
    public void makeMove(String location){
        int locNum = Integer.parseInt(location);
        int row = locNum / 10 ;
        int col  = locNum % 10; 
        boolean isBomb = game.isBombAtLocation(row, col);
        if(isBomb) endGame();
        else{
            
          board[locNum].setText(Integer.toString(game.getCountAtLocation(row, col)));  
          board[locNum].setEnabled(false);
            emptySpaces = emptySpaces -1;
            if (emptySpaces == 0) {
                endGame();
            }
        }
    }

    public void revealBoard(){
        for(int i = 0 ; i < board.length ; i++){
            int row = i /10;
            int col = i %10;
            Dimension buttonSize = board[i].getSize();
            int width = (int) buttonSize.getWidth();
            int height = (int) buttonSize.getHeight();
            int cellValue = game.getCountAtLocation(row, col);
            ImageIcon bombIcon = new ImageIcon("bomb.png");
            ImageIcon scaledIcon = new ImageIcon(bombIcon.getImage().getScaledInstance(width,height,100));
            board[i].setText("");
            if(game.getBombGrid()[row][col] == true) board[i].setIcon(scaledIcon);
            else board[i].setText(Integer.toString(cellValue));
        }
    }
    public void endGame(){
        String messgae ; 
        if (emptySpaces == 0) messgae = "You Won!";
        else {
            messgae = "You lost" ;
            revealBoard();
        }

        System.out.println("you lose");
        String [] options = {"Play Again" , "Exit"};
        int choice = JOptionPane.showOptionDialog(gameScreen,"you lose", "Minesweeper",JOptionPane.YES_NO_OPTION,0,null,options,0);
        if (choice == JOptionPane.YES_OPTION) { 
            // If the user chose 'Yes' 
            // show a message indicating that they are 
            // proceeding 
            startGame();
            JOptionPane.showMessageDialog(null,"New Game Has Begun"); 
        } 
        else if (choice == JOptionPane.NO_OPTION) { 
            // If the user chose 'No' 
            // show a message indicating that they are not 
            // proceeding 
            gameScreen.dispose();

        } 
    }
public static void main(String[] args) {
    App sweeper = new App();
    JFrame gameScreen = sweeper.gameScreen;
    JLabel title = new JLabel("MineSweeper",SwingConstants.CENTER);
    title.setForeground(Color.BLACK);
    title.setFont(new Font("Serif",Font.PLAIN,48));
    title.setVisible(true);

    JButton playB = new JButton("Play");
    playB.setVisible(true);
    playB.setBounds(new Rectangle(10,10));
    playB.addActionListener(e->{
        sweeper.startGame();
        
    });

    gameScreen.add(playB,BorderLayout.PAGE_END);
    gameScreen.add(title,BorderLayout.CENTER);
    gameScreen.setSize(500,500);//400 width and 500 height  
    gameScreen.setVisible(true);//making the frame visible  
    gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}  

}  