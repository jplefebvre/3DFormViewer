package application;
	
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		primaryStage.setTitle("Form3DViewer Application");
	        Group root = new Group();
	        Scene scene = new Scene(root, 800,600,null);
	        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	        root.setStyle("-fx-background-color: white;");

	        Form3DMenuBar menuBar = new Form3DMenuBar(scene.widthProperty());
	        root.getChildren().add(menuBar.getMenuBar());
	        menuBar.getMenuBar().setLayoutX(0);
	        menuBar.getMenuBar().setLayoutY(0);
	        Form3DSelectorMenu selectorMenu = new Form3DSelectorMenu(root,scene,primaryStage.heightProperty(), menuBar.getMenuBar().heightProperty());
	        root.getChildren().add(selectorMenu.getVBoxSelector());
	        selectorMenu.getVBoxSelector().setLayoutX(5);

	        primaryStage.setScene(scene);
	        primaryStage.show();
	}
	

	
	 
 	public static void main(String[] args) {
		launch(args);
	}
 	
 	
}
