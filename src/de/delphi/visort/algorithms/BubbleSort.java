package de.delphi.visort.algorithms;

import de.delphi.visort.Array;

public class BubbleSort extends Algorithm{
	
	public BubbleSort(){
		
	}

	@Override
	public void run() {
		Array a=getArray();
		for(int i=0;i<a.getLength();i++){
			for(int j=0;j<a.getLength()-i-1;j++){
				if(isCanceled())
					return;
				a.compareAndSwap(j, j+1);
				finishStep();
			}
		}
	}
}
