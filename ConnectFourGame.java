/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Observable;
 
/**
 * @author Mahmoud
 */
public class ConnectFourGame extends Observable{
        private int nColumns;
        private int nRows;
        private int numToWin;
        private ConnectFourEnum[][] Grid;
        private ConnectFourEnum gameState;
        private ConnectFourEnum turn;
        
    /**constructs a game with grid size 8 x8 as a default with only 4 checkers   
     * of the same player must be above each other for the player to win and the first player will start is 
     * initialTurn
     * @param initialTurn the first player that will take the first turn
     */
    public ConnectFourGame(ConnectFourEnum initialTurn){
            this(8,8,4,initialTurn);
        }

    /**
     *
     * @param nRows the width of the grid
     * @param nColumns the length of the grid
     * @param numToWin number of coins that must be above each other  of the same player to win
     * @param initialTurn the first player
     */
    public ConnectFourGame(int nRows, int nColumns, int numToWin,ConnectFourEnum initialTurn ){
            this.nColumns = nColumns;
            this.nRows = nRows;
            this.numToWin = numToWin;
            this.turn = initialTurn;
            Grid = new ConnectFourEnum[nRows][nColumns];
            this.gameState = turn;
            reset(initialTurn);
            
        }

    /**
     *
     * @param initialTurn the first player after the game is reset
     */
    public void reset(ConnectFourEnum initialTurn){
            this.turn = initialTurn;
            for (int i = 0; i < this.nRows; i++) {
                for (int j = 0; j < this.nColumns; j++) {
                    Grid[i][j] = this.turn.EMPTY;
                }
            }
            this.setChanged();
            this.notifyObservers(null);
        }

    /**
     *
     * @param row the row chose to place the checker in 
     * @param column the column chose to drop the checker in 
     * @return the next player to take his turn
     */
    public ConnectFourEnum takeTurn(int row , int column){
            int row2 = row - 1;
            if (row > 0) {
                if ( this.Grid[row2][column].equals(turn.EMPTY)  ) {
                    throw new IllegalArgumentException();
                }
            }
            ConnectMove passedInfo = new ConnectMove(row,column);
            passedInfo.setColour(turn);
            this.Grid[row][column] = turn;
            this.setChanged();
            this.notifyObservers(passedInfo);
            System.out.println(toString());
            System.out.println(findWinner());
            if(turn.equals(turn.BLACK)){
               return turn = turn.RED;
            }else{
               return turn =  turn.BLACK;
            }
        } 
        
    /**
     *
     * @return the current player taking turn
     */
    public ConnectFourEnum getTurn(){
            return turn;
        }         

    /**
     *
     * @return the state of the game or the player who won 
     */
    public ConnectFourEnum findWinner(){
            int countRed = 0;
            int countBlack = 0;
            int isGridEmpty = 0;
            for (int i = 0; i < this.Grid.length/2; i++) {
                for (int j = 0; j < this.Grid.length/2; j++) {
                    if (this.Grid[j][i].equals(turn.RED)) {
                        countRed ++ ;
                        if (countRed == this.numToWin) {
                            System.out.println("RED wins");
                            return this.turn.RED;
                        }
                    }else{
                        countRed = 0;
                    }
                    if (this.Grid[j][i].equals(turn.BLACK)) {
                        countBlack ++ ;
                        if (countBlack == this.numToWin) {
                            System.out.println("BLACK wins");
                            return this.turn.BLACK;
                        }
                    }else{
                        countBlack = 0;
                    }
                    // check if the game is a draw
                    if (!this.Grid[i][j].equals(turn.EMPTY)) {
                        isGridEmpty ++;
                        if (isGridEmpty == (nRows * nColumns )) {
                            return ConnectFourEnum.DRAW;
                        }else{
                            isGridEmpty = 0;
                        }
                    }
                }
            }
            return ConnectFourEnum.IN_PROGRESS;
        }

    /**
     *
     * @return the state of the game if it is IN_PROGRESS or a DRAW
     */
    public ConnectFourEnum getGameState(){
            return this.gameState;
        }
        @Override
        public String toString(){
            String str = "";
            for (int i = (nRows - 1); i >= 0 ; i--) {
                for (int j = 0; j < this.nColumns; j++) {
                    str += Grid[i][j] + " | ";
                }
                str += "\n";
            }
            return str;
        }    
        
}   
