package view;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class StartGame {
	public class TwoDArrayGUI extends Application {
	    
	    public void start(Stage primaryStage) {
	        // Create a 2D array of Buttons
	        Button[][] buttons = new Button[15][15];
	        
	        // Create a GridPane layout
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(10));
	        gridPane.setHgap(5);
	        gridPane.setVgap(5);
	        
	        // Add Buttons to the GridPane
	        for (int row = 0; row < 15; row++) {
	            for (int col = 0; col < 15; col++) {
	                Button button = new Button();
	                buttons[row][col] = button;
	                gridPane.add(button, col, row);
	            }
	        }
	        
	        // Create a Scene with the GridPane layout
	        Scene scene = new Scene(gridPane);
	        
	        // Set the Scene to the Stage
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("2D Array GUI");
	        primaryStage.show();
	    }
	    public static void main(String[] args) {
	        launch(args);
	    }
	}
}
