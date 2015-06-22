package model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;

public class SubSceneForm3D {

	private SubScene subsceneForm3D;
	private Scene scene;
	private Group root;
	
	
	public SubSceneForm3D(Scene scene) {
		
		this.scene = scene;
		root = new Group();
		subsceneForm3D = new SubScene(root, 800, 600, true,SceneAntialiasing.DISABLED);
		subsceneForm3D.heightProperty().bind(scene.heightProperty());
		subsceneForm3D.widthProperty().bind(scene.widthProperty());
		subsceneForm3D.layoutXProperty().bind(scene.widthProperty().divide(10));
		subsceneForm3D.layoutYProperty().bind(scene.heightProperty().divide(10));
		Camera camera = new Camera();
		subsceneForm3D.setCamera(camera.getCamera());
		
		scene.addEventFilter(MouseEvent.MOUSE_PRESSED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						camera.onMousePressed(mouseEvent);
					}
				});

		scene.addEventFilter(MouseEvent.MOUSE_DRAGGED,
				new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						camera.onMouseDragged(mouseEvent);
					}
				});
	}
	
	public SubScene getSubScene(){
		return subsceneForm3D;
	}

	public void addBoxToSubScene(Box box) {
		root.getChildren().add(box);
		root.getChildren().add(subsceneForm3D);	
	}
	public void addCylinderToSubScene(Cylinder cylinder) {
		root.getChildren().add(cylinder);
		root.getChildren().add(subsceneForm3D);	
	
	}
	public void addSphereToSubScene(Sphere sphere) {
		root.getChildren().add(sphere);
		root.getChildren().add(subsceneForm3D);
	}
}
