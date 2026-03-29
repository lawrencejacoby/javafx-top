package de.wahl.javafx.top;

import de.wahl.top.CpuIdle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
	    private int radius = 50;

	    @Override
	    public void start(Stage primaryStage) {
	    	StringProperty value = new SimpleStringProperty("99");

	    	Circle circle = new Circle(radius, Color.CORAL);
	        
	        Text percentageText = new Text();
	        percentageText.textProperty().bind(value);
	        percentageText.setFont(Font.font("Arial", FontWeight.BOLD, 35)); // Schriftart und Größe
	        percentageText.setFill(Color.BLACK); // Schriftfarbe

	        StackPane root = new StackPane();
	        root.setPrefSize(radius*2, radius*2);
	        // Zum Testen: Ändere TRANSPARENT zu RED, um zu sehen ob das Fenster da ist
	        root.setStyle("-fx-background-color: transparent;"); 
	        
	        root.getChildren().addAll(circle, percentageText);

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
	        
	        Thread thread = new Thread(() -> {
	        	CpuIdle.watch(idle->{
		        	Platform.runLater(() -> value.set("" + idle));
		        });
	        });
	        thread.setDaemon(true); // Beendet den Thread, wenn die App schließt
	        thread.start();
	        
	        //primaryStage.requestFocus(); // Erzwinge den Fokus
	    }

}
