package de.delphi.visort.algorithms;

import java.util.Observable;

import de.delphi.visort.Array;

public abstract class Algorithm extends Observable implements Runnable{
	
	private Array array;
	
	private volatile boolean canceled=false;
	private volatile boolean canResume=true;
	
	public Algorithm(){
		
	}
	
	public abstract void run();
	
	public void cancel(){
		canceled=true;
	}
	
	public boolean isCanceled(){
		return canceled;
	}
	
	public void setArray(Array array){
		this.array=array;
	}
	
	public Array getArray(){
		return array;
	}
	
	public void resume(){
		canResume=true;
	}
	
	public void finishStep(){
		if(canceled)
			return;
		canResume=false;
		setChanged();
		notifyObservers();
		while(!canResume){
			//-_-
		}
	}
}
