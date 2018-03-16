package de.delphi.visort.algorithms;

import de.delphi.visort.Array;

public class QuickSort extends Algorithm {

	@Override
	public void run() {
		quickSort(getArray(),0,getArray().getLength()-1);
	}
	
	private void quickSort(Array a,int left,int right){
		if(left>=right || right>=a.getLength() || left>=a.getLength() || left<0 || right<0)
			return;
		int pivot=a.get((left+right)/2);
		int l=left,r=right;
		while(l<r){
			if(isCanceled())
				return;
			if(a.compareWithValue(l,pivot)>0){
				l++;
			}else if(a.compareWithValue(r,pivot)<=0){
				r--;
			}
			a.compareAndSwap(l, r);
			finishStep();
		}
		quickSort(a,left,l);
		quickSort(a,l+1,right);
	}
}
