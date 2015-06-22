package model;

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

public class Sphere3D extends Abstract3DForm {

    private final IntegerProperty radius;
    private Sphere sphere;
    private PhongMaterial pm;
    
    
    public Sphere3D(int radius, Color color){
    	super(color);
    	if(color == null)
    		color = Color.BLACK;
    	this.radius = new SimpleIntegerProperty(radius);
    	sphere = new Sphere(radius*0.8);
    	pm = new PhongMaterial();
    	pm.setDiffuseColor(color);
    	pm.setSpecularColor(color);
    	sphere.setMaterial(pm);
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
    
    @Override
    public Sphere getSphere(){
    	return sphere;
    }
    @Override
    public Box getCube() {
        return null;
    }
	@Override
	public Cylinder getCylinder() {
		return null;
	}
}
