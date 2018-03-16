package de.delphi.visort.algorithms;

import de.delphi.visort.Array;

public class MergeSort extends Algorithm {

	@Override
	public void run() {
		mergeSort(getArray(),0,getArray().getLength());
	}
	
	public void mergeSort(Array a,int start,int end){
		if(start==end-1)
			return;
		int center=(start+end)/2;
		
		mergeSort(a,start,center);
		mergeSort(a,center,end);
		
		int[] left=new int[center-start],right=new int[end-center];
		for(int i=0;i<left.length;i++){
			left[i]=a.get(start+i);
		}
		for(int i=0;i<right.length;i++){
			right[i]=a.get(center+i);
		}
		
		int i1=0,i2=0;
		for(int i=0;i<left.length+right.length;i++){
			a.compareWithIndex(0, 0);	//Count comparison
			if(i1>=left.length){
				a.set(start+i, right[i2]);
				i2++;
			}else if(i2>=right.length){
				a.set(start+i, left[i1]);
				i1++;
			}else if(left[i1]<right[i2]){
				a.set(start+i, left[i1]);
				i1++;
			}else{
				a.set(start+i, right[i2]);
				i2++;
			}
			finishStep();
		}
	}
}
