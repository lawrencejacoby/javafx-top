package de.wahl.javafx.top;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FxTopHelloWorldApp  extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.setFullScreen(true);
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setPadding(new Insets(10));
		borderPane.setStyle("-fx-background-color: rgba(0, 100, 100);");

		Scene scene = new Scene(borderPane, Color.TRANSPARENT);
		stage.setScene(scene);
		
		stage.show();
		
	}
}
