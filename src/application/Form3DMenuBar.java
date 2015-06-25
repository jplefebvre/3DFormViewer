package application;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Form3DMenuBar {
	private MenuBar menuBar;
	public Form3DMenuBar(ReadOnlyDoubleProperty stageWidth){
		menuBar = new MenuBar();
		Menu menuFile = new Menu("File");
	    MenuItem exit = new MenuItem("Exit");
	    exit.setOnAction(event ->  System.exit(0));
	    menuFile.getItems().addAll(exit);
		Menu menuInfo = new Menu("Info");
		MenuItem infoAuthor = new MenuItem("Author: Jean-Philippe Lefebvre");
		menuInfo.getItems().addAll(infoAuthor);
		menuBar.getMenus().addAll(menuFile, menuInfo);
		menuBar.prefWidthProperty().bind(stageWidth);
	}
	
	public MenuBar getMenuBar(){
		return menuBar;
	}
}
