package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Cylinder3D extends Abstract3DForm {

//	radius, height
    private final IntegerProperty radius;
    private final IntegerProperty height;
    private Cylinder cylinder;
    private PhongMaterial pm;
    
    
    public Cylinder3D(int radius, int height, Color color){
    	super(color);
    	if(color == null)
    		color = Color.BLACK;
    	this.radius = new SimpleIntegerProperty(radius);
    	this.height = new SimpleIntegerProperty(height);
    	cylinder = new Cylinder(radius*0.8, height);
    	pm = new PhongMaterial();
    	pm.setDiffuseColor(color);
    	pm.setSpecularColor(color);
    	cylinder.setMaterial(pm);
    	
    }

    public int getRadius() {
        return radius.get();
    }

    public void setRadius(int radius) {
        this.radius.set(radius);
    }

    /* This method automatically update the view when the property is modified */
    public IntegerProperty radiusProperty() {
        return radius;
    }
    
    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }
    
    /* This method automatically update the view when the property is modified */
    public IntegerProperty heightProperty() {
        return height;
    }
    

    @Override
    public Box getCube() {
        return null;
    }

	@Override
	public Sphere getSphere() {
		return null;
	}

	@Override
	public Cylinder getCylinder() {
		return cylinder;
	}
}
