package de.wahl.javafx.top;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FxTopApp extends Application{
		
		public static void main(String[] args) {
//			System.setProperty("prism.order", "sw");
//		    System.setProperty("glass.gtk.disableAppIndicator", "true");			
			launch(args);
		}

		private double xOffset = 0;
	    private double yOffset = 0;
	    private int radius = 100;

	    @Override
	    public void start(Stage primaryStage) {
	        // 1. Der Kreis
	        Circle circle = new Circle(radius, Color.CORAL);
	        //circle.setStroke(Color.BLACK);
	        //circle.setStrokeWidth(5);

	        // 2. Container mit fixer Mindestgröße
	        StackPane root = new StackPane(circle);
	        root.setPrefSize(radius*2, radius*2);
	        // Zum Testen: Ändere TRANSPARENT zu RED, um zu sehen ob das Fenster da ist
	        root.setStyle("-fx-background-color: transparent;"); 

	        // 3. Drag-and-Drop Logik (damit man es bewegen kann)
	        root.setOnMousePressed(event -> {
	            xOffset = event.getSceneX();
	            yOffset = event.getSceneY();
	        });
	        root.setOnMouseDragged(event -> {
	            primaryStage.setX(event.getScreenX() - xOffset);
	            primaryStage.setY(event.getScreenY() - yOffset);
	        });

	        // 4. Scene & Stage
	        Scene scene = new Scene(root);
	        scene.setFill(Color.TRANSPARENT); // WICHTIG

	        primaryStage.initStyle(StageStyle.TRANSPARENT); // WICHTIG
	        primaryStage.setOpacity(0.99);
	        primaryStage.setScene(scene);
	        primaryStage.setAlwaysOnTop(true);
	        primaryStage.show();
	        //primaryStage.requestFocus(); // Erzwinge den Fokus
	    }

}
