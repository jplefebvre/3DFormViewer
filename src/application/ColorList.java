package application;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

public class ColorList {
	public ObservableList<String> data;
	private ListView<String> list;
	private HashMap<String, Color> colorMap;
    public ListView<String> getColorList(){
		return list; 
	 }
    public ColorList(){
	  list = new ListView<String>();
	 list.setMaxWidth(72);
	    data = FXCollections.observableArrayList(
	            "ORANGE", "RED","BLUE","GREEN","BLACK");
	    list.setItems(data);
	    list.setCellFactory(new Callback<ListView<String>, 
	            ListCell<String>>() {
	                @Override 
	                public ListCell<String> call(ListView<String> list) {
	                    return new ColorRectCell();
	                }
	            }
	        );
	    
	    colorMap = new  HashMap<String, Color>();
	    colorMap.put("RED", Color.RED);
	    colorMap.put("ORANGE", Color.ORANGE);
	    colorMap.put("GREEN", Color.GREEN);
	    colorMap.put("BLUE", Color.BLUE);
	    colorMap.put("BLACK", Color.BLACK);
	    } 
    
    public  Color getColorMap(String color){
    	return colorMap.get(color);
    }
  
    
}
