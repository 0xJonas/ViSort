package de.delphi.visort.visualizations;

import de.delphi.visort.Array;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

public abstract class Visualization extends Pane{
	
	private Canvas canvas;
	
	public Visualization(){
		canvas=new Canvas();
		canvas.widthProperty().bind(widthProperty());
		canvas.heightProperty().bind(heightProperty());
		getChildren().add(canvas);
	}
	
	public abstract void setup(Array a);
	
	public abstract void render(Array a);
	
	public GraphicsContext getGraphics(){
		return canvas.getGraphicsContext2D();
	}
	
}
