package view;

import model.ColorList;
import model.ColorRectCell;
import model.Cube3D;
import model.Cylinder3D;
import model.Sphere3D;
import model.SubSceneForm3D;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ListCell;
import javafx.util.Callback;

public class Form3DSelectorMenu {
	private VBox vBox;
	private HBox hBox;
	private ToggleGroup group;
	private Label labelRadius = new Label("Radius: ");
	private Label labelHeight = new Label("Height: ");
	private Label labelWidth = new Label("Width: ");
	private Label labelDepth = new Label("Depth: ");
	private Label labelColor = new Label("Color:");
	private Label labelCube= new Label("BOX");
	private Label labelCylinder = new Label("CYLINDER");
	private Label labelSphere = new Label("SPHERE");
	private Label labelSelector = new Label("FORM SELECTOR");
	private Scene scene;
	private Button createButton = new Button();
	private Group root;
	private SubSceneForm3D subSceneForm3D;
	private Cube3D cube;
	private Sphere3D sphere;
	private Cylinder3D cylinder;
	private ComboBox  cbWidth,cbDepth,cbHeight, cbRadius;
	private ColorList colorList; 
	private String test;
	
	public Form3DSelectorMenu(Group root,Scene scene, ReadOnlyDoubleProperty stageHeight, ReadOnlyDoubleProperty menuHeight) {
		this.root = root;
		this.scene = scene;
		group = new ToggleGroup();
		RadioButton rbCube = new RadioButton("Box");
		rbCube.setUserData("Box");
		rbCube.setToggleGroup(group);
		RadioButton rbCylinder = new RadioButton("Cylinder");
		rbCylinder.setUserData("Cylinder");
		rbCylinder.setToggleGroup(group);
		RadioButton rbSphere = new RadioButton("Sphere");
		rbSphere.setUserData("Sphere");
		rbSphere.setToggleGroup(group);
		
		vBox = new VBox();
		vBox.toFront();
		vBox.prefHeightProperty().bind(stageHeight.divide(1.6));
		vBox.layoutYProperty().bind(menuHeight.multiply(4));
		vBox.getStyleClass().add("PrincipalVBoxForm");
		
		vBox.getChildren().addAll(labelSelector,rbCube,rbCylinder,rbSphere);

		vBox.setSpacing(10);
		
		labelWidth.getStyleClass().add("labelFormAttributes"); 
		labelRadius.getStyleClass().add("labelFormAttributes"); 
		labelHeight.getStyleClass().add("labelFormAttributes"); 
		labelDepth.getStyleClass().add("labelFormAttributes"); 
		labelColor.getStyleClass().add("labelFormAttributes");
		labelCube.getStyleClass().add("labelFormTitles");
		labelCylinder.getStyleClass().add("labelFormTitles");
		labelSphere.getStyleClass().add("labelFormTitles");
		labelSelector.getStyleClass().add("labelFormTitles");
		
		cbWidth = new ComboBox();
		cbWidth.getItems().addAll("1","2","3","4","5");
		cbWidth.setValue("1");
		cbHeight = new ComboBox();
		cbHeight.getItems().addAll("1","2","3","4","5");
		cbHeight.setValue("1");
		cbDepth = new ComboBox();
		cbDepth.getItems().addAll("1","2","3","4","5");
		cbDepth.setValue("1");
		cbRadius = new ComboBox();
		cbRadius.getItems().addAll("1","2","3","4","5");
		cbRadius.setValue("1");
		
		rbCube.setOnAction(event -> {
			vBox.getChildren().clear();
			vBox.getChildren().addAll(labelSelector,rbCube,rbCylinder,rbSphere);
			vBox.getChildren().add(getVCube(root,scene));
		});
		rbCylinder.setOnAction(event -> {
			vBox.getChildren().clear();
			vBox.getChildren().addAll(labelSelector,rbCube,rbCylinder,rbSphere);
			vBox.getChildren().add(getVCylinder(root,scene));
		});
		rbSphere.setOnAction(event -> {
			vBox.getChildren().clear();
			vBox.getChildren().addAll(labelSelector,rbCube,rbCylinder,rbSphere);
			vBox.getChildren().add(getVSphere(root,scene));
		});
	}

	private VBox getVCube(Group root,Scene scene) {
		VBox vBoxCube = new VBox();
		HBox hBoxCubeHeight = new HBox();
		HBox hBoxCubeWidth = new HBox();
		HBox hBoxCubeDepth = new HBox();
		HBox hBoxCubeColor = new HBox();
		colorList = new ColorList();
		hBoxCubeHeight.getChildren().addAll(labelHeight, cbHeight);
		hBoxCubeWidth.getChildren().addAll(labelWidth, cbWidth);
		hBoxCubeDepth.getChildren().addAll(labelDepth, cbDepth);
		hBoxCubeColor.getChildren().addAll(labelColor, colorList.getColorList());
		vBoxCube.getChildren().addAll(labelCube, hBoxCubeHeight,hBoxCubeWidth, hBoxCubeDepth, hBoxCubeColor);
		vBoxCube.getStyleClass().add("VBoxForm");
		vBoxCube.setSpacing(10);
		createButton = new Button("Create");
		createButton.getStyleClass().add("createButton");
		vBoxCube.getChildren().add(createButton);
		        
		createButton.setOnAction(event -> {
			cube = new Cube3D(Integer.parseInt((String) cbWidth.getValue()),
							Integer.parseInt((String) cbHeight.getValue()),
							Integer.parseInt((String) cbDepth.getValue()),
							colorList.getColorMap(colorList.getColorList().getSelectionModel().getSelectedItem()));
			subSceneForm3D = new SubSceneForm3D(scene,cube);
			if(root.getChildren().size() >2)root.getChildren().remove(2);
			root.getChildren().add(subSceneForm3D.getSubScene());
		});
		
		return vBoxCube;
	}

	private VBox getVCylinder(Group root,Scene scene) {
		VBox vBoxCylinder = new VBox();
		HBox hBoxCylinderHeight = new HBox();
		HBox hBoxCylinderRadius = new HBox();
		HBox hBoxCylinderColor = new HBox();
		colorList = new ColorList();
		hBoxCylinderHeight.getChildren().addAll(labelHeight,cbHeight);
		hBoxCylinderRadius.getChildren().addAll(labelRadius,cbRadius);
		hBoxCylinderColor.getChildren().addAll(labelColor, colorList.getColorList());
		vBoxCylinder.getChildren().addAll(labelCylinder,hBoxCylinderHeight, hBoxCylinderRadius,hBoxCylinderColor);
		vBoxCylinder.getStyleClass().add("VBoxForm");
		vBoxCylinder.setSpacing(10);
		createButton = new Button("Create");
		createButton.getStyleClass().add("createButton");
		vBoxCylinder.getChildren().add(createButton);
		createButton.setOnAction(event -> {
			cylinder = new Cylinder3D(Integer.parseInt((String) cbRadius.getValue()),
							Integer.parseInt((String) cbHeight.getValue()),
							colorList.getColorMap(colorList.getColorList().getSelectionModel().getSelectedItem()));
			subSceneForm3D = new SubSceneForm3D(scene,cylinder);
			if(root.getChildren().size() >2)root.getChildren().remove(2);
			root.getChildren().add(subSceneForm3D.getSubScene());
		});
		return vBoxCylinder;
	}

	private VBox getVSphere(Group root,Scene scene) {

		VBox vBoxSphere = new VBox();
		HBox hBoxSphereRadius = new HBox();
		HBox hBoxSphereColor = new HBox();
		hBoxSphereRadius.getChildren().addAll(labelRadius,cbRadius);
		hBoxSphereColor.getChildren().addAll(labelColor, colorList.getColorList());
		vBoxSphere.getChildren().addAll(labelSphere, hBoxSphereRadius, hBoxSphereColor);
		vBoxSphere.getStyleClass().add("VBoxForm");
		vBoxSphere.setSpacing(10);
		createButton =new Button("Create");
		createButton.getStyleClass().add("createButton");
		vBoxSphere.getChildren().add(createButton);
		createButton.setOnAction(event -> {
			sphere = new Sphere3D(Integer.parseInt((String) cbRadius.getValue()),
					colorList.getColorMap(colorList.getColorList().getSelectionModel().getSelectedItem()));
			subSceneForm3D = new SubSceneForm3D(scene,sphere);
			if(root.getChildren().size() >2)root.getChildren().remove(2);
			root.getChildren().add(subSceneForm3D.getSubScene());
		});	
		return vBoxSphere;
	}

	public VBox getVBoxSelector() {
		return vBox;
	}

}
