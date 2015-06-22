package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

/**
 * This class defines a 3D cube.
 */
public class Cube3D extends Abstract3DForm {
//	width, height, depth
    private final IntegerProperty width;
    private final IntegerProperty height;
    private final IntegerProperty depth;
    private Box cube;
    private PhongMaterial pm;
    
    public Cube3D(int width, int height,int depth, Color color){
    	super(color);
    	if(color == null)
    		color = Color.BLACK;
    	this.width = new SimpleIntegerProperty(width);
    	this.height = new SimpleIntegerProperty(height);
    	this.depth = new SimpleIntegerProperty(depth);
    	cube = new Box(width, height, depth);
    	pm = new PhongMaterial();
    	pm.setDiffuseColor(color);
    	pm.setSpecularColor(color);
    	cube.setMaterial(pm);
    }

    public int getWidth() {
        return width.get();
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public IntegerProperty widthProperty() {
        return width;
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
    
    public int getDepth() {
        return depth.get();
    }

    public void setDepth(int depth) {
        this.depth.set(depth);
    }

    /* This method automatically update the view when the property is modified */
    public IntegerProperty depthProperty() {
        return depth;
    }
    
    @Override
    public Box getCube() {
        return cube;
    }

	@Override
	public Sphere getSphere() {
		return null;
	}

	@Override
	public Cylinder getCylinder() {
		return null;
	}

}
