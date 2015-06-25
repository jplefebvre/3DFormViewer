package application;

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
	private VBox vBox, subVBox;
	private HBox hBox, hBoxHeight, hBoxWidth, hBoxDepth, hBoxRadius, hBoxColor;
	private ToggleGroup group;
	private Label labelRadius = new Label("Radius: ");
	private Label labelHeight = new Label("Height: ");
	private Label labelWidth = new Label("Width: ");
	private Label labelDepth = new Label("Depth: ");
	private Label labelColor = new Label("Color:");
	private Label labelCube = new Label("BOX");
	private Label labelCylinder = new Label("CYLINDER");
	private Label labelSphere = new Label("SPHERE");
	private Label labelSelector = new Label("FORM SELECTOR");
	private Scene scene;
	private Group root;
	private Button createButton = new Button();
	private SubSceneForm3D subSceneForm3D;
	private Cube3D cube;
	private Sphere3D sphere;
	private Cylinder3D cylinder;
	private ComboBox cbWidth, cbDepth, cbHeight, cbRadius;
	private ColorList colorList;
	private String test;

	/**
	 * The constructor for the Selector Menu create the radioButton used to select a form type and add an action event to all of them.
	 * 
	 * @param: The main scene and the parent root
	 */
	public Form3DSelectorMenu(Group root, Scene scene,
			ReadOnlyDoubleProperty stageHeight,
			ReadOnlyDoubleProperty menuHeight) {
		this.root = root;
		this.scene = scene;

		group = new ToggleGroup();
		RadioButton rbCube = new RadioButton("Box");
		rbCube.setToggleGroup(group);
		RadioButton rbCylinder = new RadioButton("Cylinder");
		rbCylinder.setToggleGroup(group);
		RadioButton rbSphere = new RadioButton("Sphere");
		rbSphere.setToggleGroup(group);

		vBox = new VBox();
		vBox.prefHeightProperty().bind(stageHeight.divide(1.6));
		vBox.layoutYProperty().bind(menuHeight.multiply(4));
		vBox.getChildren().addAll(labelSelector, rbCube, rbCylinder, rbSphere);
		vBox.setSpacing(10);
		
		initSubVbox();
		initCSS();
		
		rbCube.setOnAction(event -> {
			vBox.getChildren().removeAll(subVBox,createButton);
			createVCube();
			vBox.getChildren().add(subVBox);
			vBox.getChildren().add(createButton);
			createButton.setOnAction(clickEvent -> {createVCube();root.getChildren().add(subSceneForm3D.getSubScene());});
		});
		rbCylinder.setOnAction(event -> {
			vBox.getChildren().removeAll(subVBox,createButton);
			createVCylinder();
			vBox.getChildren().add(subVBox);
			vBox.getChildren().add(createButton);
			createButton.setOnAction(clickEvent -> {createVCylinder();root.getChildren().add(subSceneForm3D.getSubScene());});
		});
		rbSphere.setOnAction(event -> {
			vBox.getChildren().removeAll(subVBox,createButton);
			createVSphere();
			vBox.getChildren().add(subVBox);
			vBox.getChildren().add(createButton);
			createButton.setOnAction(clickEvent -> {createVSphere();root.getChildren().add(subSceneForm3D.getSubScene());});
		});

	}
	
	/**
	 * This method create the VBox containing the attribute controls used to create a 3D Form.
	 */
	private void initSubVbox() {
		subVBox = new VBox();
		colorList = new ColorList();

		hBoxHeight = new HBox();
		hBoxWidth = new HBox();
		hBoxDepth = new HBox();
		hBoxRadius = new HBox();
		hBoxColor = new HBox();
		
		cbWidth = new ComboBox();
		cbWidth.getItems().addAll("1", "2", "3", "4", "5");
		cbWidth.setValue("1");
		cbHeight = new ComboBox();
		cbHeight.getItems().addAll("1", "2", "3", "4", "5");
		cbHeight.setValue("1");
		cbDepth = new ComboBox();
		cbDepth.getItems().addAll("1", "2", "3", "4", "5");
		cbDepth.setValue("1");
		cbRadius = new ComboBox();
		cbRadius.getItems().addAll("1", "2", "3", "4", "5");
		cbRadius.setValue("1");
		
		hBoxHeight.getChildren().addAll(labelHeight, cbHeight);
		hBoxWidth.getChildren().addAll(labelWidth, cbWidth);
		hBoxDepth.getChildren().addAll(labelDepth, cbDepth);
		hBoxRadius.getChildren().addAll(labelRadius, cbRadius);
		hBoxColor.getChildren().addAll(labelColor, colorList.getColorList());

		createButton = new Button("Create");
		createButton.getStyleClass().add("createButton");

		subVBox.setSpacing(10);
	}


	/**
	 * This method set the CSS style to the Label and the VBox 
	 */
	private void initCSS() {
		labelWidth.getStyleClass().add("labelFormAttributes");
		labelRadius.getStyleClass().add("labelFormAttributes");
		labelHeight.getStyleClass().add("labelFormAttributes");
		labelDepth.getStyleClass().add("labelFormAttributes");
		labelColor.getStyleClass().add("labelFormAttributes");
		labelCube.getStyleClass().add("labelFormTitles");
		labelCylinder.getStyleClass().add("labelFormTitles");
		labelSphere.getStyleClass().add("labelFormTitles");
		labelSelector.getStyleClass().add("labelFormTitles");
		vBox.getStyleClass().add("PrincipalVBoxForm");
		subVBox.getStyleClass().add("VBoxForm");
	}

	/**
	 * This method create a box and add it to the subscene.
	 */
	private void createVCube() {
		if(subSceneForm3D != null)
			root.getChildren().remove(subSceneForm3D.getSubScene());
		subVBox.getChildren().clear();
		subVBox.getChildren().addAll(labelCube, hBoxHeight, hBoxWidth,
				hBoxDepth, hBoxColor);
		cube = new Cube3D(Integer.parseInt((String) cbWidth.getValue()),
				Integer.parseInt((String) cbHeight.getValue()),
				Integer.parseInt((String) cbDepth.getValue()),
				colorList.getColorMap(colorList.getColorList()
						.getSelectionModel().getSelectedItem()));
		subSceneForm3D = new SubSceneForm3D(scene);
		subSceneForm3D.addBoxToSubScene(cube.getCube());
	}


	/**
	 * This method create a cylinder and add it to the subscene.
	 */
	private void createVCylinder() {
		if(subSceneForm3D != null)
			root.getChildren().remove(subSceneForm3D.getSubScene());
		subVBox.getChildren().clear();
		subVBox.getChildren().addAll(labelCylinder, hBoxHeight, hBoxRadius,
				hBoxColor);
		cylinder = new Cylinder3D(
				Integer.parseInt((String) cbRadius.getValue()),
				Integer.parseInt((String) cbHeight.getValue()),
				colorList.getColorMap(colorList.getColorList()
						.getSelectionModel().getSelectedItem()));
		subSceneForm3D = new SubSceneForm3D(scene);
		subSceneForm3D.addCylinderToSubScene(cylinder.getCylinder());
	}


	/**
	 * This method create a sphere and add it to the subscene.
	 */
	private void createVSphere() {
		if(subSceneForm3D != null)
			root.getChildren().remove(subSceneForm3D.getSubScene());
		
		subVBox.getChildren().clear();
		subVBox.getChildren().addAll(labelSphere, hBoxRadius, hBoxColor);
		sphere = new Sphere3D(Integer.parseInt((String) cbRadius.getValue()),
				colorList.getColorMap(colorList.getColorList()
						.getSelectionModel().getSelectedItem()));
		subSceneForm3D = new SubSceneForm3D(scene);
		subSceneForm3D.addSphereToSubScene(sphere.getSphere());
	}


	/**
	 * This method create a box and add it to the subscene.
	 * 
	 * @return A VBox containing the selector controls
	 */
	public VBox getVBoxSelector() {
		return vBox;
	}

}
