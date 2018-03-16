package de.delphi.visort.algorithms;

import de.delphi.visort.Array;

public class InsertionSort extends Algorithm {
	
	public InsertionSort(){
		
	}

	@Override
	public void run() {
		Array a=getArray();
		for(int i=0;i<a.getLength();i++){
			for(int j=i;j>=1;j--){
				if(isCanceled())
					return;
				if(a.compareWithIndex(j, j-1)>0){
					a.swap(j, j-1);
					finishStep();
				}else{
					finishStep();
					break;
				}
			}
		}
	}
}
