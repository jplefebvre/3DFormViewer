package model;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;

public class SubSceneForm3D {

	private SubScene subsceneForm3D;
	
	public SubSceneForm3D(Scene scene, Abstract3DForm form) {
		
		Group root = new Group();
		root.setStyle("-fx-background-color: black;");
		subsceneForm3D = new SubScene(root, 800, 600, true,
				SceneAntialiasing.DISABLED);
		
		if(form.getCube() != null)
			root.getChildren().add(form.getCube());
		else if(form.getSphere() != null)
			root.getChildren().add(form.getSphere());
		else if(form.getCylinder() != null)
			root.getChildren().add(form.getCylinder());
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
}
