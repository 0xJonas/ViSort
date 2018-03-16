package de.delphi.visort.visualizations;

import de.delphi.visort.Array;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class VerticalBars extends Visualization {
	
	private boolean grayScale;
	
	private double max;
	
	public VerticalBars(boolean grayScale){
		this.grayScale=grayScale;
	}
	
	@Override
	public void setup(Array a){
		max=a.getMax();
		setPrefSize(a.getLength(),max);
	}

	@Override
	public void render(Array a) {
		GraphicsContext g=getGraphics();
		g.setFill(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		double barWidth=getWidth()/a.getLength();
		for(int i=0;i<a.getLength();i++){
			if(grayScale){
				g.setFill(Color.gray((double) a.getSilently(i)/max));
			}else{
				g.setFill(Color.hsb((double) a.getSilently(i)/max*300,1.0,1.0));
			}
			double height=(double) a.getSilently(i)/max*getHeight();
			g.fillRect(i*barWidth, getHeight()-height, barWidth, height); 
		}
	}
}
