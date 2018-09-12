package project.maze;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class ShowMaze extends Application {
	private int width = 0;
	private int length = 0;
	private int[] left;
	private int[] bottom;
	
	private static final int UNIT = 20;
	
	/*public ShowMaze(int[] left, int[] bottom, int width, int length){
		this.left = left;
		this.bottom = bottom;
		this.width = width;
		this.length = length;
	}*/
	
	public void init(){
		this.width = 20;
		this.length = 20;
		int itemNum = width * length;
		left = new int[itemNum];
		bottom = new int[itemNum];
		for(int i = 0; i < itemNum; i++){
			left[i] = 1;
			bottom[i] = 1;
		}
		left[0] = 0;
	}
	public void reset(int width, int length){
		this.width = width;
		this.length = length;
		int itemNum = width * length;
		left = new int[itemNum];
		bottom = new int[itemNum];
		
	}
	
	
	public static void show(){
		launch();
	}
	public static void main(String[] args) {  
        launch(args);  
    }
	
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		Text text0 = new Text("Please enter the width and length of the Maze");
		Text text1 = new Text("Width");
		Text text2 = new Text("Length");
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		
		Button button1 = new Button("Submit"); 
	    Button button2 = new Button("Clear");
	    
	    GridPane gridPane = new GridPane();
	    gridPane.add(text0, 0, 0, 2, 1);
	    gridPane.add(text1, 0, 1); 
	    gridPane.add(textField1, 1, 1); 
	    gridPane.add(text2, 0, 2);       
	    gridPane.add(textField2, 1, 2); 
	    gridPane.add(button1, 0, 3); 
	    gridPane.add(button2, 1, 3);
	    
	    Scene scene1 = new Scene(gridPane);
	    
	    primaryStage.setTitle("MyMaze");
	    primaryStage.setScene(scene1);
	    
	    //submit event handler
	    EventHandler<MouseEvent> eventHandler1 = new EventHandler<MouseEvent>() {
	    	@Override 
	    	public void handle(MouseEvent e) {
	    		String widthInput = textField1.getCharacters().toString();
	    		String lengthInput = textField2.getCharacters().toString();
	    		
	    		width = Integer.parseInt(widthInput);
	    		length = Integer.parseInt(lengthInput);
	    		reset(width, length);
	    		
	    		MazeGeneration mg = new MazeGeneration(left, bottom, width, length);
	    		
	    		Scene scene2 = new Scene(new LinePaneMaze(left, bottom, width, length), width * (UNIT + 2), length * (UNIT + 2));
	    		primaryStage.setScene(scene2);
	    	} 
	    };
	    
	    //clear event handler
	    EventHandler<MouseEvent> eventHandler2 = new EventHandler<MouseEvent>() {
	    	@Override 
	    	public void handle(MouseEvent e) {
	    		textField1.setText("");
	    		textField2.setText("");
	    	} 
	    };
	    
	    button1.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler1);
	    button2.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler2);
		
		primaryStage.show(); // Display the stage
	}
}

class LinePaneMaze extends Pane {

	private static final int UNIT = 20;
	private static final int MARGIN = 10;
	
	public LinePaneMaze(int[] left, int[] bottom, int width, int length) {
	  
		int itemNum = width * length;
		
		for(int i = 0; i < itemNum; i++){
			int row = i / width;
			int colum = i % width;
			if(left[i] == 1){
				Line line = new Line(MARGIN + colum * UNIT, MARGIN + row * UNIT, MARGIN + colum * UNIT, MARGIN + row * UNIT + UNIT);
				line.setStrokeWidth(2);
			    line.setStroke(Color.GREEN);
			    getChildren().add(line);
			}
			if(bottom[i] == 1){			
				Line line = new Line(MARGIN + colum * UNIT, MARGIN + row * UNIT + UNIT, MARGIN + colum * UNIT + UNIT, MARGIN + row * UNIT + UNIT);
				line.setStrokeWidth(2);
			    line.setStroke(Color.GREEN);
			    getChildren().add(line);
			}
			
		}
		Line topLine = new Line(MARGIN, MARGIN, MARGIN + width * UNIT, MARGIN);
		topLine.setStrokeWidth(2);
		topLine.setStroke(Color.GREEN);
		getChildren().add(topLine);
		
		Line rightLine = new Line(MARGIN + width * UNIT, MARGIN, MARGIN + width * UNIT, MARGIN + length * UNIT - UNIT);
		rightLine.setStrokeWidth(2);
		rightLine.setStroke(Color.GREEN);
		getChildren().add(rightLine);
  }
}
