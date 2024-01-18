package view;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.geometry.*;
public class confirm {
	static boolean answer;
	public static boolean display(String title,String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(250);
		Label label = new Label(message);
	
		Button yes = new Button("Yes");
		Button no = new Button("No");
		 yes.setOnAction(e -> { 
			 answer = true;
			 window.close();
		 });
		 no.setOnAction(e -> { 
			 answer = false;
			 window.close();
		 });
		 
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label,yes,no);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		return answer;
	}
}