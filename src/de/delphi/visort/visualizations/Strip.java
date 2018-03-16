package de.delphi.visort.visualizations;

import de.delphi.visort.Array;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Strip extends Visualization {
	
	private boolean grayScale=false;
	
	private int max;
	
	public Strip(boolean grayScale){
		this.grayScale=grayScale;
	}

	@Override
	public void setup(Array a) {
		max=a.getMax();
		setPrefWidth(a.getLength());
	}

	@Override
	public void render(Array a) {
		GraphicsContext g=getGraphics();
		double barWidth=getWidth()/a.getLength();
		for(int i=0;i<a.getLength();i++){
			if(grayScale){
				g.setFill(Color.gray((double) a.getSilently(i)/max));
			}else{
				g.setFill(Color.hsb((double) a.getSilently(i)/max*300,1.0,1.0));
			}
			g.fillRect(i*barWidth, 0, barWidth, getHeight());
		}
	}

}
