
package tictactoe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Listener implements ActionListener {
    GameFrame parrent;
    static int emptySquaresLeft = 9;
    static int win, lost;
    
    public Listener(GameFrame parent) {
       this.parrent = parent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == GameFrame.newGameButton){
            for(int i=0;i<9;i++){
            GameFrame.button[i].setEnabled(true);
            GameFrame.button[i].setLabel("");
            GameFrame.button[i].setBackground(Color.orange);
            }  
        emptySquaresLeft = 9;
        GameFrame.label.setText("Your turn!");
        GameFrame.newGameButton.setEnabled(false);
            return;
        }
        String winner = "";
        for ( int i=0; i<9; i++ ) {
            if ( e.getSource() == GameFrame.button[i] ) {
                GameFrame.button[i].setLabel("X");
                GameFrame.button[i].setEnabled(false);
                winner = lookForWinner();
            if(!"".equals(winner)){
                endTheGame();
            } else {
                computerMove();
                winner = lookForWinner();
                if ( !"".equals(winner)){
                    endTheGame();
                }
            }
            break;
            }
        }
            win = 0;
            lost = 0;
            if ( winner.equals("X") ) {
                GameFrame.label.setText("You won!");
                win ++;
                GameFrame.win.setText(GameFrame.win.getText());
            } else if (winner.equals("O")){
                GameFrame.label.setText("You lost!");
                lost ++;
                GameFrame.lost.setText("LOST : "+lost);
            } else if (winner.equals("T")){
                GameFrame.label.setText("It's a tie!");
            }       
        } 
  
    private void computerMove() {
        int selectedSquare;
        selectedSquare = findEmptySquare("O");
        if (selectedSquare == -1){
            selectedSquare = findEmptySquare("X");
        }
        if ((selectedSquare == -1) && (GameFrame.button[4].getLabel().equals(""))){
            selectedSquare = 4;
        }
        if ( selectedSquare == -1 ){
            selectedSquare = getRandomSquare();
        }
            GameFrame.button[selectedSquare].setLabel("O");
            GameFrame.button[selectedSquare].setEnabled(false);
    }

    private String lookForWinner() {
        String theWinner = "";
        emptySquaresLeft -- ;
        if (emptySquaresLeft == 0){
            return "T";
        }
        if (! GameFrame.button[0].getLabel().equals("")&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[1].getLabel())&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[2].getLabel())){
            theWinner = GameFrame.button[0].getLabel();
            higlightWinner(0,1,2);
        } else  if (! GameFrame.button[3].getLabel().equals("")&&
                GameFrame.button[3].getLabel().equals(GameFrame.button[4].getLabel())&&
                GameFrame.button[3].getLabel().equals(GameFrame.button[5].getLabel())){
            theWinner = GameFrame.button[3].getLabel();
            higlightWinner(3,4,5);
        } else if (! GameFrame.button[6].getLabel().equals("")&&
                GameFrame.button[6].getLabel().equals(GameFrame.button[7].getLabel())&&
                GameFrame.button[6].getLabel().equals(GameFrame.button[8].getLabel())){
            theWinner = GameFrame.button[6].getLabel();
            higlightWinner(6,7,8);
        }else if (! GameFrame.button[0].getLabel().equals("")&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[3].getLabel())&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[6].getLabel())){
            theWinner = GameFrame.button[0].getLabel();
            higlightWinner(0,3,6);
        }else if (! GameFrame.button[1].getLabel().equals("")&&
                GameFrame.button[1].getLabel().equals(GameFrame.button[4].getLabel())&&
                GameFrame.button[1].getLabel().equals(GameFrame.button[7].getLabel())){
            theWinner = GameFrame.button[1].getLabel();
            higlightWinner(1,4,7);
        } else if (! GameFrame.button[2].getLabel().equals("")&&
                GameFrame.button[2].getLabel().equals(GameFrame.button[5].getLabel())&&
                GameFrame.button[2].getLabel().equals(GameFrame.button[8].getLabel())){
            theWinner = GameFrame.button[2].getLabel();
            higlightWinner(2,5,8);
        } else if (! GameFrame.button[0].getLabel().equals("")&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[4].getLabel())&&
                GameFrame.button[0].getLabel().equals(GameFrame.button[8].getLabel())){
            theWinner = GameFrame.button[0].getLabel();
            higlightWinner(0,4,8);
        } else if (! GameFrame.button[2].getLabel().equals("")&&
                GameFrame.button[2].getLabel().equals(GameFrame.button[4].getLabel())&&
                GameFrame.button[2].getLabel().equals(GameFrame.button[6].getLabel())){
            theWinner = GameFrame.button[2].getLabel();
            higlightWinner(2,4,6);
        }
        return theWinner;
        }
    
    private void endTheGame() {
       GameFrame.newGameButton.setEnabled(true);
       for(int i = 0; i < 9; i++){
           GameFrame.button[i].setEnabled(false);
       }
    }

    private void higlightWinner(int win1, int win2, int win3) {
        GameFrame.button[win1].setBackground(Color.CYAN);
        GameFrame.button[win2].setBackground(Color.CYAN);
        GameFrame.button[win3].setBackground(Color.CYAN);
    }

    private int findEmptySquare(String player) {
       int weight[] =  new int[9];
       for (int i = 0; i < 9; i++){
           if (GameFrame.button[i].getLabel().equals("O"))
               weight [i] = -1;
           else if (GameFrame.button[i].getLabel().equals("X"))
               weight[i] = 1;
           else
               weight[i] = 0;
       }
       int twoWeights = player.equals("O")? -2 : 2;
       if (weight[0] + weight[1] + weight[2] == twoWeights){
           if (weight[0] == 0)
               return 0;
           else if (weight[1] == 0)
               return 1;
           else
               return 2;
       }
        if (weight[3] +weight[4] + weight[5] == twoWeights) {
            if ( weight[3] == 0 )
                return 3;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 5;
        }
        if (weight[6] + weight[7] +weight[8] == twoWeights ) {
            if ( weight[6] == 0 )
                return 6;
            else if ( weight[7] == 0 )
                return 7;
            else
                return 8;
        }
        if (weight[0] + weight[3] + weight[6] == twoWeights) {
            if ( weight[0] == 0 )
                return 0;
            else if ( weight[3] == 0 )
                return 3;
            else
                return 6;
        }
        if (weight[1] +weight[4] + weight[7] == twoWeights ) {
            if ( weight[1] == 0 )
                return 1;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 7;
        }
        if (weight[2] + weight[5] + weight[8] == twoWeights ){
            if ( weight[2] == 0 )
                return 2;
            else if ( weight[5] == 0 )
                return 5;
            else
                return 8;
        }
        if (weight[0] + weight[4] + weight[8] == twoWeights ){
            if ( weight[0] == 0 )
                return 0;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 8;
        }
        if (weight[2] + weight[4] + weight[6] == twoWeights ){
            if ( weight[2] == 0 )
                return 2;
            else if ( weight[4] == 0 )
                return 4;
            else
                return 6;
        }
        // Не найдено двух одинаковых соседних клеток
        return -1;
    }

    private int getRandomSquare() {
        boolean gotEmptySquare = false;
        int selectedSquare = -1;
        do {
            selectedSquare = (int)(Math.random()*9);
            if (GameFrame.button[selectedSquare].getLabel().equals("")){
                gotEmptySquare = true;
            }
        } while (! gotEmptySquare);
        return selectedSquare;
    }
}