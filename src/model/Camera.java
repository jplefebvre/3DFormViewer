package model;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Camera {

    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;
    private double oldXPosition, newXPosition;
    private double oldYPosition, newYPosition;
	
    public Camera(){
    	
    	   camera = new PerspectiveCamera(true);
           cameraRotateX = new Rotate(-20, Rotate.X_AXIS);
           cameraRotateY = new Rotate(0, Rotate.Y_AXIS);
           cameraRotateZ = new Rotate(0, Rotate.Z_AXIS);
           cameraTranslate = new Translate(0, 0, -20);
           camera.getTransforms().addAll(
                     cameraRotateX,
                     cameraRotateY,
                     cameraRotateZ,
                     cameraTranslate);
    }

    public void onMousePressed(MouseEvent mouseEvent) {
         oldXPosition = newXPosition = mouseEvent.getSceneX();
         oldYPosition = newYPosition = mouseEvent.getSceneY();
    }
     
    public void onMouseDragged(MouseEvent mouseEvent) {
         oldXPosition = newXPosition;
         oldYPosition = newYPosition;
         newXPosition = mouseEvent.getSceneX();
         newYPosition = mouseEvent.getSceneY();
          
         double mouseDeltaX = (newXPosition - oldXPosition);
         double mouseDeltaY = (newYPosition - oldYPosition);
          
         cameraRotateX.setAngle(cameraRotateX.getAngle() - mouseDeltaY);
         cameraRotateY.setAngle(cameraRotateY.getAngle() + mouseDeltaX);
    }
    
    public PerspectiveCamera getCamera(){
    	return camera;
    }
}
