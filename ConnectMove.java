

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Mahmoud
 */ 
public class ConnectMove {
    private int row;
    private int column;
    private ConnectFourEnum colour;
     
    /**
     *
     * @param row the row that the player placed his checker in 
     * @param column the column that the player placed his checker in 
     */
    public ConnectMove(int row, int column){
        this.row = row;
        this.column = column;

    }

    /**
     *
     * @return the row of the checker in the grid
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return the column of the checker in the grid
     */
    public int getColumn (){
        return column;
    }

    /**
     *
     * @return the color/the player that was droped the last 
     */
    public ConnectFourEnum getColour(){
        return colour;
    }

    /**
     *
     * @param o the color/ the player that would play the next 
     * @return
     */
    public ConnectFourEnum setColour(ConnectFourEnum o){
        return this.colour = o;
    }
}
