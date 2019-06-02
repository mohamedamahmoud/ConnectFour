
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @auther Mahmoud 
 */
public class ConnectFourApplication extends Application implements Observer {
    private static final int NUM_COLUMNS = 8;
    private static final int NUM_ROWS = 8;
    private static final int NUM_TO_WIN = 4;
    private static final int BUTTON_SIZE = 20;
    private ConnectFourGame gameEngine;
    private ConnectButton button[][];
    private Button b;

   
    public TextField  text;
    private ConnectFourEnum turn;
    
    @Override
    public void start(Stage primaryStage){
        this.gameEngine = new ConnectFourGame(ConnectFourEnum.BLACK);
        gameEngine.addObserver(this);
        
        BorderPane root = new BorderPane();
        Pane topBranch = new HBox();
        GridPane middleBranch = new GridPane(); 
        Pane buttonBranch = new HBox(); 
        middleBranch.setMaxWidth(Double.MAX_VALUE);
        turn = ConnectFourEnum.BLACK;
        text = new TextField(turn + " begins");
        text.setEditable(false);
        topBranch.getChildren().add(text);
        button = new ConnectButton[NUM_ROWS][NUM_COLUMNS];
        
        EventHandler<ActionEvent> sharedHandler = new ButtonHandler(); 
        
                
        for (int i = (NUM_ROWS - 1); i >= 0 ; i--) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    button[i][j] = new ConnectButton("EMPTY",i,j);
                    button[i][j].setMinWidth(BUTTON_SIZE);
                    button[i][j].setMinHeight(BUTTON_SIZE);
                    button[i][j].setText("EMPTY");
                    button[i][j].setOnAction(sharedHandler);
                    middleBranch.add(button[i][j], j, 7-i);
            }
        } 
        
        b = new Button("Take my Turn");   
        b.setOnAction(new EventHandler<ActionEvent>() { // TakeMyTurn has to be clicked first before 
            @Override                                   // choosing the button
            public void handle(ActionEvent event) {
                System.out.println("Drop the checker!");
                if (event.getSource() instanceof Button) {
                    if (gameEngine.getTurn().equals(ConnectFourEnum.BLACK)) {
                        text.setText("it's RED's turn" );
                        b.setOnAction(sharedHandler); // since the player clicked the rake my turn button  button
                    }else{                            // then we drop the checker at the last button he clicked on
                        text.setText("it's BLACK's turn" );
                        b.setOnAction(sharedHandler);
                    }  
                }  
                    
            }
        });
        
        buttonBranch.getChildren().add(b);
        
        
        root.setTop(topBranch);
        root.setBottom(buttonBranch);
        root.setCenter(middleBranch);
        Scene scene = new Scene(root, 440, 250);
        
        primaryStage.setTitle("Connect Four");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            for (int i = (NUM_ROWS - 1); i >= 0 ; i--) {
                for (int j = 0; j < NUM_COLUMNS; j++) {
                    this.button[i][j].setText("EMPTY");
                    button[i][j].setDisable(false);
                }
            }
        }else{
            ConnectMove c = (ConnectMove)arg;
            if(c.getColour().equals(ConnectFourEnum.BLACK)){
                button[c.getRow()][c.getColumn()].setText("BLACK");
                button[c.getRow()][c.getColumn()].setDisable(true);
                button[c.getRow()][c.getColumn()].setDisable(true);
                text.setText("it's RED's turn" );
            }else{
                button[c.getRow()][c.getColumn()].setText("RED");
                button[c.getRow()][c.getColumn()].setDisable(true);
                text.setText("it's BLACK's turn" );
             }
        // dealing with the winner
            ConnectFourEnum winner = this.gameEngine.findWinner();
            if (winner.equals(ConnectFourEnum.BLACK)) {
               Alert alert = new Alert(AlertType.CONFIRMATION.INFORMATION);
               alert.setHeaderText("Game Over");
               alert.setContentText("BLACK Wins");
               alert.showAndWait().ifPresent(response ->{
                if (response == ButtonType.OK) {
                    gameEngine.reset(turn);
                }
                });
            }        
     
           if (winner.equals(ConnectFourEnum.  RED)) {
               Alert alert = new Alert(AlertType.CONFIRMATION.INFORMATION);
               alert.setHeaderText("Game Over");
               alert.setContentText("RED Wins");
               alert.showAndWait().ifPresent(response ->{
               if (response == ButtonType.OK) {
                   gameEngine.reset(turn);
               }
               });
            }
            if (winner.equals(ConnectFourEnum.  RED)) {
               Alert alert = new Alert(AlertType.CONFIRMATION.INFORMATION);
               alert.setHeaderText("Draw");
               alert.showAndWait().ifPresent(response ->{
               if (response == ButtonType.OK) {
                    gameEngine.reset(turn);
                }
                });
           }  
        }   
    }

class ButtonHandler implements EventHandler<ActionEvent> {
    private int x;  // indices of the last button the player clicked on 
    private int y;
    @Override
    public void handle(ActionEvent event) {
        System.out.println("ActionEvent " + event.getSource());
        Object source = event.getSource();
        if ( source instanceof ConnectButton ) {   // checking if it is the burrons in the grid 
            ConnectButton b = (ConnectButton)source; // or the TakeMyTurn button
            x = b.getRow();
            y = b.getColumn();
            // allowing the game to be played only when the player chooses his final button and
            // clicks nn Take my turn buttom
        }else{
            ConnectFourEnum takeTurn = gameEngine.takeTurn(x, y);
            x = -1; // so if tke turn is pressed directly the button clicked by the other player will ot change
            y = -1;
        }   
            
            
        
    }
}  
        
}



    
    
    


