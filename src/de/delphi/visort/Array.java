package de.delphi.visort;

public class Array {
	
	private int[] content;
	
	private int accessCount=0;
	
	private int compareCount=0;
	
	private int lastCompared1=-1,lastCompared2=-1,lastAccessed=-1;
	
	private int max=0;
	
	public Array(int[] content,int max){
		this.content=content;
		this.max=max;
	}
	
	public int getMax(){
		return max;
	}
	
	public int getLength(){
		return content.length;
	}
	
	public int getAccessCount(){
		return accessCount;
	}
	
	public int getLastAccessed(){
		return lastAccessed;
	}
	
	public int getCompareCount(){
		return compareCount;
	}
	
	public int getLastCompared1(){
		return lastCompared1;
	}
	
	public int getLastCompared2(){
		return lastCompared2;
	}
	
	public synchronized int get(int index){
		if(index<0 || index>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: index "+index+" length "+content.length);
		accessCount++;
		lastAccessed=index;
		return content[index];
	}
	
	public synchronized int getSilently(int index){
		if(index<0 || index>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: index "+index+" length "+content.length);
		return content[index];
	}
	
	public synchronized void set(int index,int value){
		if(index<0 || index>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: index "+index+" length "+content.length);
		accessCount++;
		lastAccessed=index;
		content[index]=value;
	}
	
	public synchronized void swap(int index1,int index2){
		if(index1<0 || index1>=content.length || index2<0 || index2>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: length "+content.length);
		accessCount+=2;
		lastAccessed=index2;
		int temp=content[index1];
		content[index1]=content[index2];
		content[index2]=temp;
	}
	
	public synchronized int compareWithValue(int index,int value){
		if(index<0 || index>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: index "+index+" length "+content.length);
		compareCount++;
		lastCompared1=index;
		lastCompared2=-1;
		return value-content[index];
	}
	
	public synchronized int compareWithIndex(int index1,int index2){
		if(index1<0 || index1>=content.length || index2<0 || index2>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: length "+content.length);
		compareCount++;
		lastCompared1=index1;
		lastCompared2=index2;
		return content[index2]-content[index1];
	}
	
	public synchronized void compareAndSwap(int index1,int index2){
		if(index1<0 || index1>=content.length || index2<0 || index2>=content.length)
			throw new ArrayIndexOutOfBoundsException("Array index out of Bounds: length "+content.length);
		compareCount++;
		lastCompared1=index1;
		lastCompared2=index2;
		int temp=0;
		if(index1>index2){
			temp=index1;
			index1=index2;
			index2=temp;
		}
		if(content[index2]-content[index1]<0){
			accessCount+=2;
			lastAccessed=index2;
			temp=content[index1];
			content[index1]=content[index2];
			content[index2]=temp;
		}
	}
}
