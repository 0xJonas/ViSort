package de.delphi.visort;

import java.io.File;
import java.io.IOException;
import java.util.Observable;

import de.delphi.visort.algorithms.Algorithm;
import de.delphi.visort.algorithms.BubbleSort;
import de.delphi.visort.algorithms.InsertionSort;
import de.delphi.visort.algorithms.MergeSort;
import de.delphi.visort.algorithms.QuickSort;
import de.delphi.visort.algorithms.SelectionSort;
import de.delphi.visort.visualizations.ElementPersistance;
import de.delphi.visort.visualizations.Strip;
import de.delphi.visort.visualizations.VerticalBars;
import de.delphi.visort.visualizations.Visualization;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViSort extends Application{
	
	private final String[] algorithms={
			"Bubble Sort",
			"Selection Sort",
			"Insertion Sort",
			"Quicksort",
			"Merge Sort",
			"Shell Sort"
		};
	
	private final String[] visualizations={
			"Vertical Bars",
			"Strip",
			"Element Trace",
			"Element Persistance"
		};
	
	private final String[] arrays={
			"Every number once",
			"Random numbers",
			"Reverse sorted",
			"Almost sorted",
			"Few unique",
			"Already sorted"
		};
	
	@FXML private ComboBox<String> algorithmComboBox;
	@FXML private ComboBox<String> visualizationComboBox;
	@FXML private ComboBox<String> arrayComboBox;
	
	@FXML private CheckBox grayScaleCheckBox;
	
	@FXML private BorderPane rootPane;
	
	@FXML private Button pauseButton;
	@FXML private Button stepButton;
	@FXML private Slider delaySlider;
	
	private Stage mainStage;
	
	private Algorithm algo;
	
	private Visualization visual;
	
	private Array array;
	
	private long time;
	
	private double delay=3.0;
	
	private double delayAcc=0;
	
	private volatile boolean paused=false,nextStep=false;
	
	public void start(Stage stage){
		mainStage=stage;
		try{
			FXMLLoader loader=new FXMLLoader(new File("res/fxml/main.fxml").toURI().toURL());
			loader.setController(this);
			BorderPane root=loader.load();
			
			Scene scene=new Scene(root);
			
			stage.setTitle("ViSort - Sorting Visualizer");
			stage.setScene(scene);
			stage.show();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML
	private void initialize(){
		algorithmComboBox.getItems().addAll(algorithms);
		visualizationComboBox.getItems().addAll(visualizations);
		arrayComboBox.getItems().addAll(arrays);
		
		algorithmComboBox.getSelectionModel().select(0);
		visualizationComboBox.getSelectionModel().select(0);
		arrayComboBox.getSelectionModel().select(0);
		
		delaySlider.valueProperty().addListener((observable,oldVal,newVal)->{
			delay=Math.pow(10,newVal.doubleValue());
		});
	}
	
	@FXML
	private void onStart(){
		algo=getAlgorithm();
		visual=getVisualization();
		array=getArray(500);
		algo.setArray(array);
		time=System.currentTimeMillis();
		AnimationTimer timer=new AnimationTimer(){
			@Override
			public void handle(long time){
				synchronized(array){
					visual.render(array);
				}
			}
		};
		timer.start();
		
		delayAcc=0.0;
		algo.addObserver((Observable o,Object arg)->{
			try{
				long timeTaken=System.currentTimeMillis()-time;
				delayAcc+=delay;
				if(delayAcc>=delay){
					if(timeTaken<delayAcc){
						Thread.sleep((long) delayAcc-timeTaken);
					}
					delayAcc-=(int) delayAcc;
					time=System.currentTimeMillis();
				}
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			while(paused && !nextStep){
				//-_-
			}
			nextStep=false;
			algo.resume();
		});
		visual.setup(array);
		rootPane.setCenter(visual);
		mainStage.sizeToScene();
		Thread thread=new Thread(algo);
		thread.start();
	}
	
	@FXML
	private void onPause(){
		paused=!paused;
	}
	
	@FXML
	private void onStep(){
		nextStep=true;
	}
	
	private Algorithm getAlgorithm(){
		switch(algorithmComboBox.getSelectionModel().getSelectedIndex()){
		case 0:{
			return new BubbleSort();
		}case 1:{
			return new SelectionSort();
		}case 2:{
			return new InsertionSort();
		}case 3:{
			return new QuickSort();
		}case 4:{
			return new MergeSort();
		}
		}
		return null;
	}
	
	private Visualization getVisualization(){
		boolean grayScale=grayScaleCheckBox.isSelected();
		switch(visualizationComboBox.getSelectionModel().getSelectedIndex()){
		case 0:{
			return new VerticalBars(grayScale);
		}case 1:{
			return new Strip(grayScale);
		}case 2:{
			return null;
		}case 3:{
			return new ElementPersistance(grayScale);
		}
		}
		return null;
	}
	
	private Array getArray(int max){
		switch(arrayComboBox.getSelectionModel().getSelectedIndex()){
		case 0:{
			return ArrayGenerator.generate(ArrayGenerator.EVERY_NUMBER_ONCE, max);
		}case 1:{
			return ArrayGenerator.generate(ArrayGenerator.RANDOM_NUMBERS, max);
		}case 2:{
			return ArrayGenerator.generate(ArrayGenerator.REVERSE_SORTED, max);
		}case 3:{
			return ArrayGenerator.generate(ArrayGenerator.ALMOST_SORTED, max);
		}case 4:{
			return ArrayGenerator.generate(ArrayGenerator.FEW_UNIQUE, max);
		}case 5:{
			return ArrayGenerator.generate(ArrayGenerator.ALREADY_SORTED, max);
		}	
		}
		return null;
	}
	
	public static void main(String[] args){
		Application.launch(ViSort.class);
	}
}
