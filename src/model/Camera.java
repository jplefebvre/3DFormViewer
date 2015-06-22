package model;

import javafx.scene.PerspectiveCamera;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Camera {

    private PerspectiveCamera camera;
    private Rotate cameraRotateX, cameraRotateY, cameraRotateZ;
    private Translate cameraTranslate;
     
    private double mouseOldX, mouseNewX;
    private double mouseOldY, mouseNewY;
	
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
         mouseOldX = mouseNewX = mouseEvent.getSceneX();
         mouseOldY = mouseNewY = mouseEvent.getSceneY();
    }
     
    public void onMouseDragged(MouseEvent mouseEvent) {
         mouseOldX = mouseNewX;
         mouseOldY = mouseNewY;
         mouseNewX = mouseEvent.getSceneX();
         mouseNewY = mouseEvent.getSceneY();
          
         double mouseDeltaX = (mouseNewX - mouseOldX);
         double mouseDeltaY = (mouseNewY - mouseOldY);
          
         cameraRotateX.setAngle(cameraRotateX.getAngle() - mouseDeltaY);
         cameraRotateY.setAngle(cameraRotateY.getAngle() + mouseDeltaX);
    }
    
    public PerspectiveCamera getCamera(){
    	return camera;
    }
}
