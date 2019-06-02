
import javafx.scene.control.Button;
/**
 *
 * @author Mahmoud
 */
public class ConnectButton extends Button {
    private int row;
    private int column;
    private String label;

    /**
     * constructs a button withe position row,column with text label on it
     * @param label the information written   in each button
     * @param row  the row of each button in the grid
     * @param column  the column of each button in the grid
     */
    public ConnectButton(String label,int row, int column){
        this.column = column;
        this.row = row;
        this.label = label;
    }

    /**
     *
     * @return the row of number of the button in the grid
     */
    public int getRow(){
        return row;
    }

    /**
     *
     * @return the column number of the button inthe grid
     */
    public int getColumn(){
        return column;
    }

    /**
     *
     * @return the text written on the button
     */
    public String getLabel(){
        return this.label;
    }
            
    public String toString(){
        String s ="";
        s += this.row + "," + this.column;
        return  s;
    }

   
}
