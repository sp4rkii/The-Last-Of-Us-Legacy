package view;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.imageio.ImageIO;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.characters.Character;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.Group;
import javafx.scene.media.MediaView;

public class StartupScreen extends Application implements EventHandler<MouseEvent>{
    private static boolean answer;
    private static String action;
    ImageView centerImageView;
	ImageView rightImageView;
	Hero Bat ;
	Stage primary;
	
	
    public void close(Stage primaryStage) {
		boolean answer = confirm.display("hello","sure u wanna close?");
		if(answer){
		System.out.println("lolita close me please");
		primaryStage.close();
		}
    }
public void start(Stage primaryStage) throws IOException {
	
		Game.heroes.clear();
		Game.availableHeroes.clear();
	    File iconFile = new File("./d7es7ls-981ed05e-7d7d-4ebb-b47f-a5864bcbeb65.png");
	    Image icon = new Image(iconFile.toURI().toString());
		primaryStage.getIcons().add(icon);
    	VBox buttonPane = new VBox(10);
    	buttonPane.setAlignment(Pos.CENTER_LEFT);
    	primaryStage.setWidth(1500);
    	primaryStage.setHeight(825);
    	primaryStage.setResizable(false);
        File backgroundImageFile = new File("./homeScreen.png");
        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());                                                                                             
        Button startGameButton = new Button("Start Game");
        startGameButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';");
        startGameButton.setMinWidth(200);
        startGameButton.setMinHeight(60);
        startGameButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
        startGameButton.setOnAction(event -> {
				try {
					selectHero(primaryStage);
				} catch (IOException e) {
					 Alert helpAlert = new Alert(AlertType.INFORMATION);
					 	helpAlert.initStyle(StageStyle.UNDECORATED);
		                helpAlert.initOwner(primaryStage);
			            helpAlert.setTitle("Error");
			            helpAlert.setHeaderText(null);
			            helpAlert.setContentText(e.getMessage());
			            helpAlert.showAndWait();
				}
        });
        Button helpButton = new Button("Help");
        helpButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';");
        helpButton.setMinWidth(200);
        helpButton.setMinHeight(60);
        helpButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
        helpButton.setOnAction(e -> {
            Alert helpAlert = new Alert(AlertType.INFORMATION);
            helpAlert.initStyle(StageStyle.UNDECORATED);
            helpAlert.initOwner(primaryStage);
            helpAlert.setTitle("Game Description");
            helpAlert.setHeaderText(null);
            helpAlert.setContentText("The Last of Us: Legacy is a single player survival game set in a zombie apocalyptic world.\r\n"
            		+ "The game is conducted in a turn based manner, in which each player character receives a specific\r\n"
            		+ "number of action points per turn, which they can use to move, attack or cure zombies, or use\r\n"
            		+ "special actions.\r\n"
            		+ "The player starts the game controlling only one hero, but can gain additional heroes by curing\r\n"
            		+ "zombies. The objective of the game for the player is to survive as long as it takes in order to\r\n"
            		+ "cure a sufficient number of zombies enough to build a community to survive the apocalypse.\r\n"
            		+ "");

            helpAlert.getDialogPane().setMinWidth(600);
            helpAlert.getDialogPane().setMinHeight(400);
            helpAlert.showAndWait();
        });
        
       
        Button quitButton = new Button("Quit");
        quitButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold';");
        quitButton.setMinWidth(200);
        quitButton.setMinHeight(60);
        quitButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
        quitButton.setOnAction(event -> {
     	close(primaryStage); 
       });

      
        buttonPane.getChildren().addAll(startGameButton, helpButton, quitButton);
       StackPane root1 = new StackPane();
       root1.getChildren().addAll(backgroundImageView, buttonPane);
       
       
       Scene scene1 = new Scene(root1);
       primaryStage.setScene(scene1);
       primaryStage.setTitle("The Last Of Us");
       primaryStage.show();
       primaryStage.setOnCloseRequest(e ->{
			e.consume(); 
			close(primaryStage);
		});
    }
   
    public void displayMap(Stage primaryStage) {
    	primaryStage.setTitle("The Last of Us");
    	primaryStage.setWidth(1500);
    	primaryStage.setHeight(825);
    	primaryStage.setResizable(false);
    	File backgroundImageFile = new File("./the-last-of-us-part-ii-4k-artwork-dz.png");
    	Image backgroundImage2 = new Image(backgroundImageFile.toURI().toString());
        GridPane mapGrid = new GridPane();
        mapGrid.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1px;");
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                Button cellButton = new Button();
                File backgroundImageFile3 = new File("./latest.22png");
                Image visibleImage = new Image(backgroundImageFile3.toURI().toString());
                cellButton.setPrefWidth(70);
                cellButton.setPrefHeight(49);
                cellButton.setStyle("-fx-border-color: black"); 
                if (Game.map[14 - row][col].isVisible()) {
                	if((Game.map[14-row][col] instanceof CharacterCell && ((CharacterCell)Game.map[14-row][col]).getCharacter()==null) || Game.map[14-row][col] instanceof TrapCell) {
                	 BackgroundImage backgroundImage = new BackgroundImage(
                        visibleImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(70, 49, false, false, false, false));
                	 cellButton.setBackground(new Background(backgroundImage));}
                	 else {
                		 if(Game.map[14-row][col] instanceof CharacterCell) {
                			 Character h=((CharacterCell)Game.map[14-row][col]).getCharacter();
                			 String y = null;
                				switch (h.getName()){
                				case "Joel Miller":
                					y= "./418a4cb33049217f82b65a9059f77b46.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Fighter");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText("Can attack as many times in a turn without costing action points, for 1 turn\r\n"
                		    					+ "whenever a supply is used \n" + "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		        		 });
                					break;
                				case "Ellie Williams":
                					y="./ellie_large.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Medic");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText( "Can heal and restore health to other heroes or themselves, each process of healing\r\n"
                		    					+ "uses 1 supply \n"+ "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		               
                		        		 });
                					break;
                				case "Tess":
                					y="./250.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Explorer");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
                		    					+ "a supply is used \n"+  "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		            });
                					break;
                				case "Riley Abel":
                					y="./latest.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Explorer");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
                		    					+ "a supply is used \n" +  "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		                
                		            });
                					break;
                				case "Tommy Miller":
                					y="./9kk.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Explorer");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
                		    					+ "a supply is used \n"+  "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		               
                		            });
                					break;
                				case "Bill":
                					y="./9k.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Medic");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText( "Can heal and restore health to other heroes or themselves, each process of healing\r\n"
                		    					+ "uses 1 supply \n"+  "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		                
                		            });
                					break;
                				case "David":
                					y="./HBFE8fN.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Fighter");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText("Can attack as many times in a turn without costing action points, for 1 turn\r\n"
                		    					+ "whenever a supply is used \n"+ "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		              
                		            });
                					break;
                				case "Henry Burell":
                					y="./images.jpg";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.setTitle("Medic");
                		                helpAlert.setHeaderText(null);
                		                helpAlert.setContentText(" Can heal and restore health to other heroes or themselves, each process of healing\r\n"
                		    					+ "uses 1 supply \n"+  "Name: "+h.getName()+"\n Health: "+h.getCurrentHp()+ "\n Damage: "+h.getAttackDmg()+"\n available actions: "+((Hero)h).getActionsAvailable()+"\n VaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\n SupplyInventory: "+((Hero)h).getSupplyInventory().size());
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
                		                confirm2(primaryStage,helpAlert,h);
                		            });
                					break;
                				default: y="./188.png";		
                				}
                				
                				File backgroundImageFile1 = new File(y);
                	    	    Image image = new Image(backgroundImageFile1.toURI().toString());
                		        BackgroundImage backgroundImage = new BackgroundImage(
                                        image,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.DEFAULT,
                                        new BackgroundSize(70, 49,  false, false, false, false));
                                	 	cellButton.setBackground(new Background(backgroundImage));
 
                		        
                		 }
                	else {
                		if(Game.map[14-row][col] instanceof CollectibleCell) {
                			if(((CollectibleCell)Game.map[14-row][col]).getCollectible() instanceof Supply) {
                				 File backgroundImageFile1 = new File("./supply.png");
                	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
                	                BackgroundImage backgroundImage = new BackgroundImage(
                	                        visibleImage1,
                	                        BackgroundRepeat.NO_REPEAT,
                	                        BackgroundRepeat.NO_REPEAT,
                	                        BackgroundPosition.DEFAULT,
                	                        new BackgroundSize(70, 49,  false, false, false, false));
                	                	 cellButton.setBackground(new Background(backgroundImage));}
                			else {
                				File backgroundImageFile1 = new File("./vaccine.png");
            	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
            	                BackgroundImage backgroundImage = new BackgroundImage(
            	                        visibleImage1,
            	                        BackgroundRepeat.NO_REPEAT,
            	                        BackgroundRepeat.NO_REPEAT,
            	                        BackgroundPosition.DEFAULT,
            	                        new BackgroundSize(70, 49,  false, false, false, false));
            	                	 cellButton.setBackground(new Background(backgroundImage));}
                			}
                			}
                		}
                	 }
                else {
                	File backgroundImageFile1 = new File("./Night (2).png");
	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
	                BackgroundImage backgroundImage = new BackgroundImage(
	                        visibleImage1,
	                        BackgroundRepeat.NO_REPEAT,
	                        BackgroundRepeat.NO_REPEAT,
	                        BackgroundPosition.DEFAULT,
	                        new BackgroundSize(70, 49,  false, false, false, false));
	                	 cellButton.setBackground(new Background(backgroundImage));
                }
                mapGrid.add(cellButton, col, row);

                int finalRow = 14 - row;
                int finalCol = col;
                if(Game.map[14-row][col].isVisible()) {
                    System.out.println("Clicked on cell (" + finalRow + ", " + finalCol + ")");
                    if(Game.map[14-row][col] instanceof CharacterCell) {
                    Character h=((CharacterCell)Game.map[finalRow][finalCol]).getCharacter();
                    if(h != null) {
                    switch (h.getName()){
    				case "Joel Miller":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Fighter");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Fighter \nCan attack as many times in a turn without costing action points, for 1 turn\r\n"
    		    					+ "whenever a supply is used\n" + "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable() +"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);
    		        		 });
    					break;
    				case "Ellie Williams":
    					
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Medic");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText( "Medic \nCan heal and restore health to other heroes or themselves, each process of healing\r\n"
    		    					+ "uses 1 supply \n"+ "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);
    		        		 });
    					break;
    				case "Tess":
    					
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Explorer");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Explorer\nAllows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
    		    					+ "a supply is used\n"+  "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);   
    		            });
    					break;
    				case "Riley Abel":
    					
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Explorer");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Explorer\nAllows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
    		    					+ "a supply is used\n" +  "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);  
    		            });
    					break;
    				case "Tommy Miller":
    					
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Explorer");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Explorer\nAllows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
    		    					+ "a supply is used \n"+  "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);
    		               
    		            });
    					break;
    				case "Bill":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Medic");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText( "Medic\nCan heal and restore health to other heroes or themselves, each process of healing\r\n"
    		    					+ "uses 1 supply \n"+  "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);
    		                
    		            });
    					break;
    				case "David":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Fighter");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Fighter\nCan attack as many times in a turn without costing action points, for 1 turn\r\n"
    		    					+ "whenever a supply is used \n"+ "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);		              
    		            });
    					break;
    				case "Henry Burell":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.setTitle("Medic");
    		                helpAlert.setHeaderText(null);
    		                helpAlert.setContentText("Medic\nCan heal and restore health to other heroes or themselves, each process of healing\r\n"
    		    					+ "uses 1 supply \n"+  "Name: "+h.getName()+"\nHealth: "+h.getCurrentHp()+ "\nDamage: "+h.getAttackDmg()+"\navailable actions: "+((Hero)h).getActionsAvailable()+"\nVaccineInventory: "+((Hero)h).getVaccineInventory().size() +"\nSupplyInventory: "+((Hero)h).getSupplyInventory().size());
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
    		                confirm2(primaryStage,helpAlert,h);
    		            });}}}
                }
                
            }
        } if(Game.checkWin()) {
        	gameWinScene(primaryStage);
        }
        else if(Game.checkGameOver()) {
        	gameOverScene(primaryStage);
        }
       Button endTurnButton = new Button("End Turn");
       endTurnButton.setPrefSize(100, 10);
       endTurnButton.setOnAction(e -> {
    	   try {
			Game.endTurn();
    	   	} catch (NotEnoughActionsException e1) {
    	   	 Alert helpAlert = new Alert(AlertType.INFORMATION);
	            helpAlert.setTitle("Error");
	            helpAlert.setHeaderText(null);
	            helpAlert.setContentText(e1.getMessage());
	            helpAlert.showAndWait();
	            helpAlert.initStyle(StageStyle.UNDECORATED);
                helpAlert.initOwner(primaryStage);
    	   	} catch (InvalidTargetException e1) {
    	   	 Alert helpAlert = new Alert(AlertType.INFORMATION);
	            helpAlert.setTitle("Error");
	            helpAlert.setHeaderText(null);
	            helpAlert.setContentText(e1.getMessage());
	            helpAlert.showAndWait();
	            helpAlert.initStyle(StageStyle.UNDECORATED);
                helpAlert.initOwner(primaryStage);
    	   	}
    	   	displayMap(primaryStage);
            });
       	VBox map = new VBox();
        endTurnButton.setPrefWidth(900);
        endTurnButton.setPrefHeight(10);
        endTurnButton.setStyle("-fx-background-color: transparent; -fx-font-size: 30px; -fx-text-fill: white; -fx-font-family: 'Abade';");
        map.getChildren().addAll(mapGrid,endTurnButton);
        HBox map2 = new HBox();
         map2.getChildren().addAll(map,healthbar(primaryStage));
         Pane root = new Pane();
         BackgroundImage background = new BackgroundImage(
                 backgroundImage2,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 null,
                 new BackgroundSize(
                     1.0, 1.0, true, true, false, false
                 )
             );
        root.setBackground(new Background(background));
        root.getChildren().add(map2);
        Scene mapScene = new Scene(root);
        primaryStage.setScene(mapScene);
    }
    public void confirm1(Stage primaryStage,Hero h,Alert helpAlert) {
        boolean answer = confirmation(primaryStage,helpAlert);
        if (answer) {
            Game.startGame(h);
            displayMap(primaryStage);
        }
    }
 
    public boolean confirmation(Stage primaryStage, Alert helpAlert) {
        ButtonType confirmButton = new ButtonType("Confirm", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        helpAlert.getButtonTypes().setAll(confirmButton, cancelButton);

        Optional<ButtonType> result = helpAlert.showAndWait();
        if (result.isPresent() && result.get() == confirmButton) {
            answer = true;
        } else {
            answer = false;
        }
        return answer;
    }
    
//    public void selectHero(Stage primaryStage) throws IOException  {
//    	Game.loadHeroes("./Heroes.csv");
//    	HBox buttonPane = new HBox(30);
//        buttonPane.setAlignment(Pos.CENTER);
//        String y="./HBFE8fN.png";  
//        for(int i=0;i<Game.availableHeroes.size();i++) {
//        	Button button = new Button();
//    		switch (Game.availableHeroes.get(i).getName()){
//    		case "Joel Miller":
//    			y= "./418a4cb33049217f82b65a9059f77b46.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Fighter");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText("Can attack as many times in a turn without costing action points, for 1 turn\r\n"
//        					+ "whenever a supply is used \n" + "Name: "+Game.availableHeroes.get(0).getName()+"\n Health: "+Game.availableHeroes.get(0).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(0).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(0).getActionsAvailable());
//                    confirm1(primaryStage,Game.availableHeroes.get(0),helpAlert);
//            		 });
//    			break;
//    		case "Ellie Williams":
//    			y="./ellie_large.png";
//
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Medic");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText( "Can heal and restore health to other heroes or themselves, each process of healing\r\n"
//        					+ "uses 1 supply \n"+ "Name: "+Game.availableHeroes.get(1).getName()+"\n Health: "+Game.availableHeroes.get(1).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(1).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(1).getActionsAvailable());
//                    confirm1(primaryStage,Game.availableHeroes.get(1),helpAlert);
//            		 });
//    			break;
//    		case "Tess":
//    			y="./250.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Explorer");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
//        					+ "a supply is used \n"+ "Name: "+Game.availableHeroes.get(2).getName()+"\n Health: "+Game.availableHeroes.get(2).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(2).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(2).getActionsAvailable());
//                    confirm1(primaryStage,Game.availableHeroes.get(2),helpAlert);
//                });
//    			break;
//    		case "Riley Abel":
//    			y="./latest.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Explorer");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
//        					+ "a supply is used \n" + "Name: "+Game.availableHeroes.get(3).getName()+"\n Health: "+Game.availableHeroes.get(3).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(3).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(3).getActionsAvailable());
//
//                    confirm1(primaryStage,Game.availableHeroes.get(3),helpAlert);
//                });
//    			break;
//    		case "Tommy Miller":
//    			y="./9kk.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Explorer");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText("Allows the player to be able to see the entirety of the map for 1 turn whenever\r\n"
//        					+ "a supply is used \n"+ "Name: "+Game.availableHeroes.get(4).getName()+"\n Health: "+Game.availableHeroes.get(4).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(4).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(4).getActionsAvailable());
//                    confirm1(primaryStage,Game.availableHeroes.get(4),helpAlert);
//                });
//    			break;
//    		case "Bill":
//    			y="./9k.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Medic");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText( "Can heal and restore health to other heroes or themselves, each process of healing\r\n"
//        					+ "uses 1 supply \n"+ "Name: "+Game.availableHeroes.get(5).getName()+"\n Health: "+Game.availableHeroes.get(5).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(5).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(5).getActionsAvailable());
// 
//                    confirm1(primaryStage,Game.availableHeroes.get(5),helpAlert);
//                });
//    			break;
//    		case "David":
//    			y="./HBFE8fN.png";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Fighter");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText("Can attack as many times in a turn without costing action points, for 1 turn\r\n"
//        					+ "whenever a supply is used \n"+ "Name: "+Game.availableHeroes.get(6).getName()+"\n Health: "+Game.availableHeroes.get(6).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(6).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(6).getActionsAvailable());
//
//                    confirm1(primaryStage,Game.availableHeroes.get(6),helpAlert);
//                });
//    			break;
//    		case "Henry Burell":
//    			y="./images.jpg";
//    			button.setOnAction(e -> {
//                    Alert helpAlert = new Alert(AlertType.INFORMATION);
//                    helpAlert.setTitle("Medic");
//                    helpAlert.setHeaderText(null);
//                    helpAlert.setContentText(" Can heal and restore health to other heroes or themselves, each process of healing\r\n"
//        					+ "uses 1 supply \n"+ "Name: "+Game.availableHeroes.get(7).getName()+"\n Health: "+Game.availableHeroes.get(7).getCurrentHp()+ "\n Damage: "+Game.availableHeroes.get(7).getAttackDmg()+"\n available actions: "+Game.availableHeroes.get(7).getActionsAvailable());
//
//                    confirm1(primaryStage,Game.availableHeroes.get(7),helpAlert);
//                });
//    			break;
//    		}
//    		File backgroundImageFile = new File(y);
//    	    Image image = new Image(backgroundImageFile.toURI().toString());
//            ImageView imageView = new ImageView(image);
//            imageView.setFitWidth(120);
//            imageView.setFitHeight(120);
//            Text buttonText = new Text(Game.availableHeroes.get(i).getName());
//            VBox contentBox = new VBox();
//            contentBox.setAlignment(Pos.BOTTOM_CENTER);
//            contentBox.getChildren().addAll(imageView, buttonText);
//            button.setGraphic(contentBox);
//            buttonPane.getChildren().add(button);
//       }
//        Label label = new Label();
//        label.setText("Select your Hero ");
//        label.setStyle("-fx-font-weight: bold; -fx-font-size: 100px; -fx-text-fill: red; -fx-font-family: 'Chiller';");
//        HBox labelPane = new HBox();
//        labelPane.setAlignment(Pos.CENTER);
//        labelPane.getChildren().add(label);
//        VBox buttonPane2 = new VBox(200);
//        buttonPane2.getChildren().addAll(labelPane,buttonPane);
//        StackPane lolita=new StackPane();
//        File backgroundImageFile = new File("./selectScreen.png");
//        Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
//        ImageView backgroundImageView = new ImageView(backgroundImage);
//        lolita.getChildren().addAll(backgroundImageView,buttonPane2);
//        Scene scene = new Scene(lolita);
//        primaryStage.setScene(scene);  
//        backgroundImageView.fitWidthProperty().bind(primaryStage.widthProperty());
//        backgroundImageView.fitHeightProperty().bind(primaryStage.heightProperty());
//    }
    public void confirm3(Stage primaryStage,Alert helpAlert,Hero hero,Character target,Scene old) {
    	ButtonType confirm=new ButtonType("Confim Target",ButtonData.OK_DONE);
    	ButtonType cancel=new ButtonType("Cancel",ButtonData.OK_DONE);
    	helpAlert.getButtonTypes().setAll(confirm,cancel);
    	helpAlert.initStyle(StageStyle.UNDECORATED);
        helpAlert.initOwner(primaryStage);
    	Optional<ButtonType> result = helpAlert.showAndWait();
        if (result.isPresent()) {
         ButtonType buttonClicked = result.get();
         if(buttonClicked==confirm) {
        	 hero.setTarget(target);
     	     displayMap(primaryStage);	
         }
         if(buttonClicked==cancel) {
        	 helpAlert.close();
         }
        }	
    }
    public void confirm2(Stage primaryStage, Alert helpAlert,Character hero) {
        ButtonType target = new ButtonType("choose target", ButtonData.OK_DONE);
        ButtonType attackButton = new ButtonType("Attack", ButtonData.OK_DONE);
        ButtonType cureButton = new ButtonType("Cure", ButtonData.OK_DONE);
        ButtonType useSpecialButton = new ButtonType("Use Special", ButtonData.OK_DONE);
        ButtonType move = new ButtonType("Move", ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        helpAlert.getButtonTypes().setAll(move, attackButton, target, cureButton, useSpecialButton, cancelButton);
        helpAlert.initStyle(StageStyle.UNDECORATED);
        helpAlert.initOwner(primaryStage);
        Optional<ButtonType> result = helpAlert.showAndWait();
         Hero h = (Hero)hero;
        if (result.isPresent()) {
            ButtonType buttonClicked = result.get();

            if (buttonClicked == attackButton) {
            	try {
					h.attack();
					Game.adjustVisibility(h);
					if(h.getTarget()!=null) {
						if(h.getTarget().getCurrentHp()==0)
							h.setTarget(null);
					}
		     	    displayMap(primaryStage);	
				} catch (NotEnoughActionsException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
				} catch (InvalidTargetException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
				}
            	
            } else if (buttonClicked == target) {
            	selectTargetScene(primaryStage,h,primaryStage.getScene());
            }
            else if (buttonClicked == cureButton) {
            	try {
					h.cure();
					if(h.getTarget()!=null) {
						if(h.getTarget().getCurrentHp()==0)
							h.setTarget(null);
					}
					 displayMap(primaryStage);
		     	     
				} catch (NoAvailableResourcesException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
					
				} catch (InvalidTargetException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
					
				} catch (NotEnoughActionsException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
				}
            } else if (buttonClicked == useSpecialButton) {
            	try {
					h.useSpecial();
					  displayMap(primaryStage);
				} catch (NoAvailableResourcesException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();	
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
				} catch (InvalidTargetException e) {
					Alert helpAlert1 = new Alert(AlertType.INFORMATION);
		            helpAlert1.setTitle("Error");
		            helpAlert1.setHeaderText(null);
		            helpAlert1.setContentText(e.getMessage());
		            helpAlert1.showAndWait();	
		            helpAlert1.initStyle(StageStyle.UNDECORATED);
	                helpAlert1.initOwner(primaryStage);
				}
            }else if (buttonClicked == move) {
            	Stage popup = new Stage();
            	HBox mid = new HBox();
            	VBox directions = new VBox();
            	Button up = new Button("  UP  ");
            	up.setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #808080; -fx-font-size: 16px;");
            	up.setOnAction(e -> {
            	    try {
            	    	int h1=h.getCurrentHp();
            	        h.move(Direction.UP);
            	        int h2=h.getCurrentHp();
            	        if(h2<h1)
            	        	Trapped(h);
            	    } catch (MovementException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    } catch (NotEnoughActionsException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    }
            	    popup.close();
            	    displayMap(primaryStage);
            	});
            	Button down = new Button("DOWN");
            	down.setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #808080; -fx-font-size: 16px;");
            	down.setOnAction(e -> {
            	    try {
            	    	int h1=h.getCurrentHp();
            	        h.move(Direction.DOWN);
            	        int h2=h.getCurrentHp();
            	        if(h2<h1)
            	        	Trapped(h);
            	    } catch (MovementException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    } catch (NotEnoughActionsException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    }
            	    popup.close();
            	    displayMap(primaryStage);
            	});
            	Button left = new Button("LEFT");
            	left.setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #808080; -fx-font-size: 16px;");
            	left.setOnAction(e -> {
            	    try {
            	    	int h1=h.getCurrentHp();
            	        h.move(Direction.LEFT);
            	        int h2=h.getCurrentHp();
            	        if(h2<h1)
            	        	Trapped(h);
            	    } catch (MovementException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    } catch (NotEnoughActionsException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    }
            	    popup.close();
            	    displayMap(primaryStage);
            	});
            	Button right = new Button("RIGHT");
            	right.setStyle("-fx-background-color: #d3d3d3; -fx-border-color: #808080; -fx-font-size: 16px;");
            	right.setOnAction(e -> {
            	    try {
            	    	int h1=h.getCurrentHp();
            	        h.move(Direction.RIGHT);
            	        int h2=h.getCurrentHp();
            	        if(h2<h1)
            	        	Trapped(h);
            	    } catch (MovementException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    } catch (NotEnoughActionsException e1) {
            	    	Alert helpAlert2 = new Alert(AlertType.INFORMATION);
			            helpAlert2.setTitle("Error");
			            helpAlert2.setHeaderText(null);
			            helpAlert2.setContentText(e1.getMessage());
			            helpAlert2.showAndWait();
			            helpAlert2.initStyle(StageStyle.UNDECORATED);
		                helpAlert2.initOwner(primaryStage);
            	    }
            	    popup.close();
            	    displayMap(primaryStage);
            	});
            	mid.getChildren().addAll(left, right);
            	directions.getChildren().addAll(up, mid, down);
            	directions.setAlignment(Pos.CENTER);
            	Scene scene = new Scene(directions);
            	popup.setScene(scene);
            	popup.showAndWait();
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
	public static void Trapped(Hero h) {
		 Alert helpAlert = new Alert(AlertType.INFORMATION);
         helpAlert.setTitle("Trapped");
         helpAlert.setHeaderText(null);
         helpAlert.setContentText(" You have been caught in a trap \n Health has been reduced to: " + h.getCurrentHp());
         helpAlert.showAndWait();
	}
	public VBox healthbar(Stage primaryStage) {
    	String y = " "; 
    	String x = " ";
    	VBox vbox = new VBox();
    	ArrayList<Hero> heroesCopy = new ArrayList<>(Game.heroes);
    	for(Hero hero : heroesCopy) {
    		int CurrHelth = hero.getCurrentHp();
    		int MaxHealth = hero.getMaxHp();
    		int actionpoint = hero.getActionsAvailable();
    		switch (hero.getName()){
    		case "Joel Miller":
    			y= "./418a4cb33049217f82b65a9059f77b46.png";
    			x = "Fighter";break;
    		case "Ellie Williams":
    			y="./ellie_large.png";
    			x = "Medic";
    			break;
    		case "Tess":
    			y="./250.png";
    			x = "Explorer";
    			break;
    		case "Riley Abel":
    			y="./latest.png";
    			x = "Explorer";
    			break;
    		case "Tommy Miller":
    			y="./9kk.png";
    			x = "Explorer";
    			break;
    		case "Bill":
    			y="./9k.png";
    			x = "Medic";
    			break;
    		case "David":
    			y="./HBFE8fN.png";  
    			x = "Fighter";
    			break;
    		case "Henry Burell":
    			y="./images.jpg";
    			x = "Medic";
    			break;
    		}
    		VBox vbox2 = new VBox();
        	HBox hbox = new HBox();	
    		Label actions = new Label("Actions Available : "+actionpoint);
    		Label name = new Label("Name : "+hero.getName());
    		Label type = new Label("Type : "+ x);
    		Label actions2 = new Label("Max Actions per Turn : "+ hero.getMaxActions());
    		Label target;
    		if(hero.getTarget()==null) 
    			 target = new Label("Target not selected");
    		else
    			target = new Label("Target : "+ hero.getTarget().getName());
    		VBox vBox = new VBox();
    		HBox hBox = new HBox();
    		name.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-font-family: Agency FB;");
    		type.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-font-family: Agency FB;");
    		actions.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-font-family: Agency FB;");
    		target.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-font-family: Agency FB;");
    		actions2.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px; -fx-font-family: Agency FB;");
    		Rectangle background = new Rectangle(200, 10);
            background.setStyle("-fx-fill: lightgray;");
            Rectangle background2 = new Rectangle(200, 10);
            background2.setStyle("-fx-fill: lightgray;");
            Rectangle healthLevel = new Rectangle((double) CurrHelth / MaxHealth * 200, 10);
            healthLevel.setStyle("-fx-fill: green;");
            if( CurrHelth  <= 40) {
            healthLevel.setStyle("-fx-fill: red;");
            }

            
            hBox.getChildren().add(healthLevel);
            vBox.getChildren().addAll(background,hBox,background2);
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(vBox);
            
            File backgroundImageFile = new File(y);
            Image backgroundImage = new Image(backgroundImageFile.toURI().toString());
            ImageView backgroundImageView = new ImageView(backgroundImage);
            backgroundImageView.setFitWidth(120); 
            backgroundImageView.setFitHeight(120); 

            vbox2.getChildren().addAll(name,type,actions2,actions,target,stackPane);
            
            vbox2.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(backgroundImageView, vbox2);
            vbox.getChildren().add(hbox);
    	}
    	return vbox;
    }
	public void selectTargetScene(Stage primaryStage,Hero currentHero,Scene old) {
		primaryStage.setTitle("Select Target");    
		primaryStage.setWidth(1500);
    	primaryStage.setHeight(825);
    	primaryStage.setResizable(false);
    	File backgroundImageFile = new File("./the-last-of-us-part-ii-4k-artwork-dz.png");
    	Image backgroundImage2 = new Image(backgroundImageFile.toURI().toString());
        GridPane mapGrid = new GridPane();
        mapGrid.setStyle("-fx-border-color: black;" +
                "-fx-border-width: 1px;");
        for (int row = 0; row < 15; row++) {
            for (int col = 0; col < 15; col++) {
                Button cellButton = new Button();
                File backgroundImageFile3 = new File("./latest.22png");
                Image visibleImage = new Image(backgroundImageFile3.toURI().toString());
                cellButton.setPrefWidth(70);
                cellButton.setPrefHeight(49);
                cellButton.setStyle("-fx-border-color: black"); 
                if (Game.map[14 - row][col].isVisible()) {
                	if((Game.map[14-row][col] instanceof CharacterCell && ((CharacterCell)Game.map[14-row][col]).getCharacter()==null) || Game.map[14-row][col] instanceof TrapCell) {
                	 BackgroundImage backgroundImage = new BackgroundImage(
                        visibleImage,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        new BackgroundSize(70, 49, false, false, false, false));
                	 cellButton.setBackground(new Background(backgroundImage));}
                	 else {
                		 if(Game.map[14-row][col] instanceof CharacterCell) {
                			 Character h=((CharacterCell)Game.map[14-row][col]).getCharacter();
                			 String y = null;
                				switch (h.getName()){
                				case "Joel Miller":
                					y= "./418a4cb33049217f82b65a9059f77b46.png";
                					cellButton.setOnAction(e -> {
                						   Alert helpAlert = new Alert(AlertType.INFORMATION);
                							helpAlert.setTitle("Choose Target");
                							helpAlert.initStyle(StageStyle.UNDECORATED);
                    		                helpAlert.initOwner(primaryStage);
                							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
                							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		        		 });
                					break;
                				case "Ellie Williams":
                					y="./ellie_large.png";
                					cellButton.setOnAction(e -> {
                						   Alert helpAlert = new Alert(AlertType.INFORMATION);
                							helpAlert.setTitle("Choose Target");
                							helpAlert.initStyle(StageStyle.UNDECORATED);
                    		                helpAlert.initOwner(primaryStage);
                							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
                							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		               
                		        		 });
                					break;
                				case "Tess":
                					y="./250.png";
                					cellButton.setOnAction(e -> {
                						   Alert helpAlert = new Alert(AlertType.INFORMATION);
                							helpAlert.setTitle("Choose Target");
                							helpAlert.initStyle(StageStyle.UNDECORATED);
                    		                helpAlert.initOwner(primaryStage);
                							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
                							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		            });
                					break;
                				case "Riley Abel":
                					y="./latest.png";
                					cellButton.setOnAction(e -> {
                		              
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
             							helpAlert.setTitle("Choose Target");
             							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
             							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		                
                		            });
                					break;
                				case "Tommy Miller":
                					y="./9kk.png";
                					cellButton.setOnAction(e -> {
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
             							helpAlert.setTitle("Choose Target");
             							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
             							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		               
                		            });
                					break;
                				case "Bill":
                					y="./9kk.png";
                					cellButton.setOnAction(e -> {
                		         
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
             							helpAlert.setTitle("Choose Target");
             							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
             							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		                
                		            });
                					break;
                				case "David":
                					y="./HBFE8fN.png";
                					cellButton.setOnAction(e -> {
                		          
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
             							helpAlert.setTitle("Choose Target");
             							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
             							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		              
                		            });
                					break;
                				case "Henry Burell":
                					y="./images.jpg";
                					cellButton.setOnAction(e -> {
                		             
                		                Alert helpAlert = new Alert(AlertType.INFORMATION);
                		                helpAlert.initStyle(StageStyle.UNDECORATED);
                		                helpAlert.initOwner(primaryStage);
             							helpAlert.setTitle("Choose Target");
             							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
             							confirm3(primaryStage,helpAlert,currentHero,h,old);
                		            });
                					break;
                				default: y="./188.png";		
                				}
                				
                				File backgroundImageFile1 = new File(y);
                	    	    Image image = new Image(backgroundImageFile1.toURI().toString());
                		        BackgroundImage backgroundImage = new BackgroundImage(
                                        image,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundRepeat.NO_REPEAT,
                                        BackgroundPosition.DEFAULT,
                                        new BackgroundSize(70, 49,  false, false, false, false));
                                	 	cellButton.setBackground(new Background(backgroundImage));
 
                		        
                		 }
                	else {
                		if(Game.map[14-row][col] instanceof CollectibleCell) {
                			if(((CollectibleCell)Game.map[14-row][col]).getCollectible() instanceof Supply) {
                				 File backgroundImageFile1 = new File("./supply.png");
                	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
                	                BackgroundImage backgroundImage = new BackgroundImage(
                	                        visibleImage1,
                	                        BackgroundRepeat.NO_REPEAT,
                	                        BackgroundRepeat.NO_REPEAT,
                	                        BackgroundPosition.DEFAULT,
                	                        new BackgroundSize(70, 49,  false, false, false, false));
                	                	 cellButton.setBackground(new Background(backgroundImage));}
                			else {
                				File backgroundImageFile1 = new File("./vaccine.png");
            	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
            	                BackgroundImage backgroundImage = new BackgroundImage(
            	                        visibleImage1,
            	                        BackgroundRepeat.NO_REPEAT,
            	                        BackgroundRepeat.NO_REPEAT,
            	                        BackgroundPosition.DEFAULT,
            	                        new BackgroundSize(70, 49,  false, false, false, false));
            	                	 cellButton.setBackground(new Background(backgroundImage));}
                			}
                			}
                		}
                	 }
                else {
                	File backgroundImageFile1 = new File("./Night (2).png");
	                Image visibleImage1 = new Image(backgroundImageFile1.toURI().toString());
	                BackgroundImage backgroundImage = new BackgroundImage(
	                        visibleImage1,
	                        BackgroundRepeat.NO_REPEAT,
	                        BackgroundRepeat.NO_REPEAT,
	                        BackgroundPosition.DEFAULT,
	                        new BackgroundSize(70, 49,  false, false, false, false));
	                	 cellButton.setBackground(new Background(backgroundImage));
                }
                mapGrid.add(cellButton, col, row);

                int finalRow = 14 - row;
                int finalCol = col;
                if(Game.map[14-row][col].isVisible()) {
                    System.out.println("Clicked on cell (" + finalRow + ", " + finalCol + ")");
                    if(Game.map[14-row][col] instanceof CharacterCell) {
                    Character h=((CharacterCell)Game.map[finalRow][finalCol]).getCharacter();
                    if(h != null) {
                    switch (h.getName()){
    				case "Joel Miller":
    					cellButton.setOnAction(e -> {
    		               
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
 							
    		        		 });
    					break;
    				case "Ellie Williams":
    					
    					cellButton.setOnAction(e -> {
    		              
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		               
    		        		 });
    					break;
    				case "Tess":
    					
    					cellButton.setOnAction(e -> {
    		              
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		                
    		            });
    					break;
    				case "Riley Abel":
    					
    					cellButton.setOnAction(e -> {
    		              
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		                
    		            });
    					break;
    				case "Tommy Miller":
    					
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		               
    		            });
    					break;
    				case "Bill":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		                
    		            });
    					break;
    				case "David":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);	              
    		            });
    					break;
    				case "Henry Burell":
    					cellButton.setOnAction(e -> {
    		                Alert helpAlert = new Alert(AlertType.INFORMATION);
    		                helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
 							helpAlert.setTitle("Choose Target");
 							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
 							confirm3(primaryStage,helpAlert,currentHero,h,old);
    		            });
    					break;
    					default: 
    					cellButton.setOnAction(e -> {
    						Alert helpAlert = new Alert(AlertType.INFORMATION);
    						helpAlert.initStyle(StageStyle.UNDECORATED);
    		                helpAlert.initOwner(primaryStage);
							helpAlert.setTitle("Choose Target");
							helpAlert.setHeaderText("Are you sure you want "+h.getName()+" to be your target ?");
							confirm3(primaryStage,helpAlert,currentHero,h,old);
    						});}}}}
                }
                
            }
         VBox map = new VBox();
         Button useless=new Button("Select Your Target");
         useless.setPrefWidth(900);
         useless.setPrefHeight(10);
         useless.setStyle("-fx-background-color: transparent; -fx-font-size: 30px; -fx-text-fill: white; -fx-font-family: 'Abade';");
        map.getChildren().addAll(mapGrid,useless);
        HBox map2 = new HBox();
         map2.getChildren().addAll(map,healthbar(primaryStage));
         Pane root = new Pane();
         BackgroundImage background = new BackgroundImage(
                 backgroundImage2,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 null,
                 new BackgroundSize(
                     1.0, 1.0, true, true, false, false
                 )
             );
        root.setBackground(new Background(background));
        root.getChildren().add(map2);
        Scene mapScene = new Scene(root);
        primaryStage.setScene(mapScene);
    }
	public void gameOverScene(Stage primaryStage) {
    	Stage gameOverStage = new Stage();
    	gameOverStage.setHeight(500);
    	gameOverStage.setWidth(600);
    	gameOverStage.setResizable(false);
    	File backgroundImageFile = new File("./gameover.png");
    	Image backgroundImage2 = new Image(backgroundImageFile.toURI().toString());
    	 Pane root = new Pane();
         BackgroundImage background = new BackgroundImage(
                 backgroundImage2,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 null,
                 new BackgroundSize(
                     1.0, 1.0, true, true, false, false
                 )
             );
        root.setBackground(new Background(background));
    	Label label = new Label("Game Over!");
    	label.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;");
    	HBox hbox = new HBox(20);
    	VBox vbox = new VBox(300);
    	Button useless = new Button("");
    	
    	 Button tryAgainButton = new Button("Main Menu");
    	 tryAgainButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: red; -fx-font-family: 'Abadi';");
    	 tryAgainButton.setMinWidth(100);
    	 tryAgainButton.setMinHeight(20);
    	 tryAgainButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
         tryAgainButton.setOnAction(event -> {
        	 gameOverStage.close();
        	 Platform.runLater(() -> {
                 try {
                     start(new Stage());
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             });
         });
             
         Button quitButton = new Button("Quit");
         quitButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: red; -fx-font-family: 'Abadi';");
         quitButton.setMinWidth(100);
         quitButton.setMinHeight(20);
         quitButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
         quitButton.setOnAction(event -> {
        	 Platform.exit();
        	 System.exit(0);
        });
         
        hbox.getChildren().addAll(tryAgainButton,quitButton);
        hbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().addAll(useless,hbox);
        root.getChildren().add(vbox);
        
        Scene scene = new Scene(root);
        gameOverStage.setScene(scene);

       
        gameOverStage.show();
    }
    
    public void gameWinScene(Stage primaryStage) {
    	VBox vbox = new VBox(300);
    	primaryStage.setHeight(500);
    	primaryStage.setWidth(600);
    	primaryStage.setResizable(false);
    	Stage stage = new Stage();
    	Label label = new Label("");
    	label.setStyle("-fx-font-size: 16px; -fx-text-fill: red; -fx-font-weight: bold;");
    	File backgroundImageFile = new File("./IMG_6808.jpg");
    	Image backgroundImage2 = new Image(backgroundImageFile.toURI().toString());
    	 Pane root = new Pane();
         BackgroundImage background = new BackgroundImage(
                 backgroundImage2,
                 BackgroundRepeat.NO_REPEAT,
                 BackgroundRepeat.NO_REPEAT,
                 null,
                 new BackgroundSize(
                     1.0, 1.0, true, true, false, false
                 )
             );
        root.setBackground(new Background(background));
    	HBox hbox = new HBox(20);
    	 Button playAgainButton = new Button("Main Menu");
    	 playAgainButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: white; -fx-font-family: 'Abadi';");
    	 playAgainButton.setMinWidth(100);
    	 playAgainButton.setMinHeight(60);
    	 playAgainButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
         playAgainButton.setOnAction(event -> {
        	 stage.close();
        	 Platform.runLater(() -> {
                 try {
                     start(new Stage());
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             });
         });
         Button quitButton = new Button("Quit");
         quitButton.setStyle("-fx-background-color: transparent; -fx-font-size: 50px; -fx-text-fill: white; -fx-font-family: 'Abadi';");
         quitButton.setMinWidth(100);
         quitButton.setMinHeight(60);
         quitButton.setContentDisplay(ContentDisplay.TEXT_ONLY);
         quitButton.setOnAction(event -> {
        	 
        	 Platform.exit();
        	 System.exit(0);
        });

    	hbox.getChildren().addAll(playAgainButton,quitButton);
    	vbox.getChildren().addAll(label,hbox);
    	root.getChildren().add(vbox);
    	 
        
         

    	Scene scene = new Scene(root);

    	
    	stage.setScene(scene);
    	stage.show();
    }
    @Override
	public void handle(MouseEvent mouseEvent) {
		if(((MouseEvent) mouseEvent).getButton()==MouseButton.PRIMARY){
            if(((MouseEvent) mouseEvent).getClickCount() == 2){
            	Game.startGame(Bat);
            	displayMap(this.primary);
            }
        }
		
	}
    public void selectHero(Stage primaryStage)  throws IOException  {
   
    	Game.loadHeroes("./Heroes.csv");
        String y="./HBFE8fN.png";  
        BorderPane borderPane = new BorderPane();
        borderPane.setPrefWidth(primaryStage.getWidth());
        borderPane.setPrefHeight(primaryStage.getHeight());
        File ImageFile = new File("./FaYD-JkX0AYLRxj.png");
        Image image = new Image(ImageFile.toURI().toString());
        Pane root = new Pane();
        BackgroundImage background = new BackgroundImage(
        		image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                null,
                new BackgroundSize(
                    1.0, 1.0, true, true, false, false
                )
            );
        root.setBackground(new Background(background));
        HBox Fighters = new HBox(20);
        Label fighters = new Label("Fighters");
        fighters.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 25;");
        
        HBox Medics = new HBox(20);
        Label medics = new Label("Medics");
        medics.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 25;");
        
        HBox Explorers = new HBox(20);
        Label explorers = new Label("Explorers");
        explorers.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 25;");
        
        VBox Left = new VBox(40);
        borderPane.setLeft(Left);
        Left.setAlignment(Pos.CENTER_LEFT);
        
      
        for(Hero hero : Game.availableHeroes) {
        	Button button = new Button();
        	button.setPrefWidth(100);
        	button.setPrefHeight(100);
        	
        	switch (hero.getName()){
    		case "Joel Miller":
    			y= "./418a4cb33049217f82b65a9059f77b46.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./My project (1).png");
    		         Image image2 = new Image(backgroundImageFile1.toURI().toString());
    		          ImageView img = new ImageView(image2);  
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Fighter \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		         label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");
    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		        Bat = hero;
    		        primary=primaryStage;
            		 });
    			button.setOnMouseClicked(this); 
    			break;
    		case "Ellie Williams":
    		    y = "./ellie_large.png";

    		    button.setOnAction(e -> {
    		        File backgroundImageFile1 = new File("./My project.png");
    		          Image image2 = new Image(backgroundImageFile1.toURI().toString());
    		          ImageView img = new ImageView(image2);  
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Medic \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    		    break;

    		case "Tess":
    			y="./250.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./Tess.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          	ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Explorer \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		case "Riley Abel":
    			y="./latest.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./Riley.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Explorer \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		case "Tommy Miller":
    			y="./9kk.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./Tommy.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Explorer \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		case "Bill":
    			y="./9k.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./Bill.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Medic \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: " + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		case "David":
    			y="./HBFE8fN.png";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./david.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: David \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		case "Henry Burell":
    			y="./images.jpg";
    			button.setOnAction(e -> {
    				File backgroundImageFile1 = new File("./latest444.png");
    				Image image2 = new Image(backgroundImageFile1.toURI().toString());
  		          ImageView img = new ImageView(image2); 
    		          Label label = new Label("Name: " + hero.getName() + "\n" + "Type: Medic \n"
    		        		  +"Max Hp: " + hero.getMaxHp() + "\n" + "Current Hp: " + hero.getCurrentHp() + "\n"
    		        		  +"Attack Damage: "  + hero.getAttackDmg() + "\n" + "Max Actions: " + hero.getMaxActions());
    		          label.setStyle("-fx-background-color: transparent; -fx-font-size: 35; -fx-text-fill: white; -fx-font-family: 'Arial Rounded MT Bold'");    		          borderPane.setCenter(img);
    		          borderPane.setRight(label);
    		          Bat = hero;
    		          primary=primaryStage;
       		 });
			button.setOnMouseClicked(this); 
    			break;
    		}
        	File backgroundImageFile1 = new File(y);
	        Image image11 = new Image(backgroundImageFile1.toURI().toString());
	        BackgroundImage backgroundImage1 = new BackgroundImage(
                    image11,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.DEFAULT,
                    new BackgroundSize(100, 100, false, false, false, false));
            	 button.setBackground(new Background(backgroundImage1));

        	if(hero instanceof Fighter) {
        		Fighters.getChildren().add(button);
        	}
        	else if(hero instanceof Medic) {
        		Medics.getChildren().add(button);
        	}
        	else if(hero instanceof Explorer) {
        		Explorers.getChildren().add(button);
            }
        	
    }
        root.getChildren().add(borderPane);
        Left.getChildren().addAll(fighters,Fighters,medics,Medics,explorers,Explorers);
        Scene scene = new Scene(root);       
        primaryStage.setScene(scene);
        primaryStage.setWidth(1500);
    	primaryStage.setHeight(825);
    	primaryStage.setResizable(false);
    }
    
}