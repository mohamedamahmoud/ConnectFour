


/**
 * @author Mahmoud
 */
public class ConnectFourTestClient {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.RED);
        System.out.println(game.toString());
        try{
        game.takeTurn(0, 0);
        game.takeTurn(0, 1);
        game.takeTurn(1, 0);
        game.takeTurn(1, 1);
        game.takeTurn(2, 0);
        game.takeTurn(2, 1);
        game.takeTurn(3, 0);
        }catch(Exception e){
            System.out.println("the down button is empty");
        } 
        
        
    }
    
}
