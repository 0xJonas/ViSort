package de.delphi.visort.visualizations;

import de.delphi.visort.Array;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ElementPersistance extends Visualization {
	
	private int[] prev,count;
	
	private int max;
	
	private boolean grayScale;
	
	public ElementPersistance(boolean grayScale){
		this.grayScale=grayScale;
	}

	@Override
	public void setup(Array a) {
		max=a.getMax();
		setPrefWidth(a.getLength());
		prev=new int[a.getLength()];
		count=new int[a.getLength()];
		for(int i=0;i<prev.length;i++){
			prev[i]=-1;
		}
	}

	@Override
	public void render(Array a) {
		GraphicsContext g=getGraphics();
		double barWidth=getWidth()/a.getLength();
		for(int i=0;i<a.getLength();i++){
			if(a.getSilently(i)!=prev[i]){
				prev[i]=a.getSilently(i);
				count[i]=0;
			}else if(count[i]<max){
				count[i]++;
			}
			if(grayScale){
				g.setFill(Color.gray(1-((double) count[i]/max)));
			}else{
				g.setFill(Color.hsb((double) count[i]/max*300,1.0,1.0));
			}
			g.fillRect(i*barWidth, 0, barWidth, getHeight());
		}
	}

}
