package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * This abstract class defines a template method for all the 3DForm.
 */
public abstract class Abstract3DForm {
	    private final ObjectProperty<Paint> color;
	    private Stage primaryStage = null;
	    
	    public Abstract3DForm(Color color){
	    	if(color == null)
	    		color = Color.BLACK;
	    	this.color = new SimpleObjectProperty<>(this, "Color", color);
	    }
	    
	    public void setColor(Color color) {
	        this. color.set(color);
	    }

	    public ObjectProperty<Paint>  colorProperty() {
	        return  color;
	    }
	    
	     public abstract Box getCube();
	     public abstract Sphere getSphere();
	     public abstract Cylinder getCylinder();
}
