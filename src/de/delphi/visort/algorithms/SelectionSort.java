package de.delphi.visort.algorithms;

import de.delphi.visort.Array;

public class SelectionSort extends Algorithm {
	
	public SelectionSort(){
		
	}

	@Override
	public void run() {
		Array a=getArray();
		for(int i=0;i<a.getLength();i++){
			int min=i;
			for(int j=i;j<a.getLength();j++){
				if(isCanceled())
					return;
				if(a.compareWithIndex(j,min)>0){
					min=j;
				}
				finishStep();
			}
			a.swap(i,min);
		}
	}
}
