import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TicTacToe {

    //medidas del JFrame
    int boardWidth =600;
    int boardheight = 670; // 50px adicionales para panel arriba / 20px para boton restart


//Definicion del JFrame

    //titulo de la pagina
    JFrame frame = new JFrame("Tic-Tac-Toe");

    //top
    JLabel textLable = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    //tablero
    JPanel tablero = new JPanel();
    JButton[][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    //bottom
    JPanel bottom = new JPanel();
    JButton restart = new JButton();


    //players score
    int playerXscore = 0;
    int playerOscore = 0;


    // win condition variable
    boolean gameOver = false;

    //tie condition variable
    int turns = 0;


    TicTacToe() {

        // window creation
        frame.setVisible(true);
        frame.setSize(boardWidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // window styling
        textLable.setBackground(Color.darkGray);
        textLable.setForeground(Color.white);
        textLable.setFont(new Font("Arial", Font.BOLD, 50));
        textLable.setHorizontalAlignment(JLabel.CENTER);
        textLable.setText("Tic-Tac-Toe");
        textLable.setOpaque(true);

        
        //page title
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLable);
        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(bottom, BorderLayout.SOUTH);

        // Create the board as a 3x3 array
        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);


        //create buttons for the game
        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                //styling tile buttons
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);


                //pressing the button work
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currentPlayer);

                            // increment truns for checking a possible tie
                            turns++;
                            //win condition 
                            checkWinner();
                            if (!gameOver) {

                                //change player as pressing button

                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLable.setText(currentPlayer + "'s turn");
                            }
                        }
                    }
                });
            }
        }
        }

        // win conditions
    void checkWinner(){

        //horizontal win condition
        for (int r = 0; r <3; r++) {

            //if 1st column = empty continue checking
            if (board[r][0].getText() == "") continue;

            // if 1st column = to 2nd column and 2nd column = to 3rd is game over
            if (board [r][0].getText() == board [r][1].getText() && board [r][1].getText() == board [r][2].getText()){

                //winner
                for (int i = 0; i < 3; i++){
                    setWinner(board[r][i]);
                }
                gameOver= true;
                return;
            }
        }

        //vertical win condition
        for (int c = 0; c <3; c++){

             //if 1st row = empty continue checking
             if (board[0][c].getText() == "") continue;

             // if 1st row = to 2nd row and 2nd row = to 3rd is game over
             if (board [0][c].getText() == board [1][c].getText() && board [1][c].getText() == board [2][c].getText()){
 
                 //winner
                 for (int i = 0; i < 3; i++){
                     setWinner(board[i][c]);
                 }
                 gameOver= true;
                 return;
             }

        }

        // diagonal win conditions

        // this one is only from top-left to bottom-right
        if(board [0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
            for (int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        // this is only from top-right to bottom-left
        if(board [0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
            
                setWinner(board[0][2]);
                setWinner(board[1][1]);
                setWinner(board[2][0]);
            gameOver = true;
            return;
        }



        //tie
        if(turns == 9){
            for(int r = 0; r <3; r++){
                for (int c = 0; c <3; c++){
                    setTie(board[r][c]);
                }
            }
            gameOver = true;
            return;
        }
    }

//winner button and lable styling
void setWinner(JButton tile) {
    tile.setBackground(Color.gray);
    tile.setForeground(Color.green);
    textLable.setText(currentPlayer + " is the winner");
    textLable.setForeground(Color.green);
}

//tie button and lable styling
void setTie(JButton tile){
    tile.setBackground(Color.gray);
    tile.setForeground(Color.orange);
    textLable.setText("It is a tie");
    textLable.setForeground(Color.orange);
}


}