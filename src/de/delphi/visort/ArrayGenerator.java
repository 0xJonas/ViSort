package de.delphi.visort;

public class ArrayGenerator {
	
	public static final int EVERY_NUMBER_ONCE=0,
							RANDOM_NUMBERS=1,
							REVERSE_SORTED=2,
							ALMOST_SORTED=3,
							FEW_UNIQUE=4,
							ALREADY_SORTED=5;

	private ArrayGenerator(){
		
	}
	
	public static Array generate(int type,int max){
		int[] array=new int[max];
		switch(type){
		case EVERY_NUMBER_ONCE:{
			for(int i=1;i<=max;i++){
				array[i-1]=i;
			}
			for(int i=0;i<max;i++){
				swap(array,i,(int) (max*Math.random()));
			}
			break;
		}case RANDOM_NUMBERS:{
			for(int i=0;i<max;i++){
				array[i]=(int) (max*Math.random());
			}
			break;
		}case REVERSE_SORTED:{
			for(int i=0;i<max;i++){
				array[i]=max-i;
			}
			break;
		}case ALMOST_SORTED:{
			for(int i=1;i<=max;i++){
				array[i-1]=i;
			}
			int index1=(int) (max*Math.random());
			int index2=(int) (max*Math.random());
			swap(array,index1,index2);
			break;
		}case FEW_UNIQUE:{
			double scale=max/5.0;
			for(int i=0;i<max;i++){
				array[i]=(int) (scale+scale*(int) (5*Math.random()));
			}
			break;
		}case ALREADY_SORTED:{
			for(int i=1;i<=max;i++){
				array[i-1]=i;
			}
			break;
		}
		}
		return new Array(array,max);
	}
	
	private static void swap(int[] array,int index1,int index2){
		int temp=array[index1];
		array[index1]=array[index2];
		array[index2]=temp;
	}
}
