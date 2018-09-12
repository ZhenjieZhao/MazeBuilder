package project.maze;

import java.util.Random;

public class MazeGeneration {
	private int itemNum = 0;
	private int width = 0;
	private int length = 0;
	private int[] left;
	private int[] bottom;
	private DisjSets ds;
	
	private void initial(){
		for(int i = 0; i < itemNum; i++){
			left[i] = 1;
			bottom[i] = 1;
		}
		left[0] = 0;
		genertaMaze();
	}
	
	
	public MazeGeneration(int[] left, int[] bottom, int a, int b){
		width = a;
		length = b;
		itemNum = a * b;
		
		this.left = left;
		this.bottom = bottom;
		ds  = new DisjSets(itemNum);
		initial();
	}
	
	private boolean isConnected(int a, int b){
		return ds.find(a) == ds.find(b);
	}
	
	private boolean allConnected(){
		for(int i = 0; i < itemNum; i++){
			for(int j = i + 1; j < itemNum; j++){
				if(!isConnected(i, j))
					return false;
			}
		}
		return true;
	}
	
	private void genertaMaze(){
		Random random = new Random();
		while(!allConnected()){
			int wall = random.nextInt(2 * itemNum);
			if(wall > itemNum - 1){ //process left edge
				wall -= itemNum - 1;
				if(wall % width == 0 || left[wall] == 0)
					continue;
				if(!isConnected(wall, wall - 1)){
					ds.union(ds.find(wall), ds.find(wall - 1));
					left[wall] = 0;
				}
			}else{//process bottom edge
				if(wall + width > itemNum - 1 || bottom[wall] == 0)
					continue;
				if(!isConnected(wall, wall + width)){
					ds.union(ds.find(wall), ds.find(wall + width));
					bottom[wall] = 0;
				}
			}
		}
		
	}

}
