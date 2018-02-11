package krSudoku;

import krSudoku.Models.GridModel;

public class Main {
	public static void main(String args[]) {
		GridModel playGrid = new GridModel();
		
		playGrid.setNum(1, 1, 3);
		playGrid.setNum(4, 4, 7);
		playGrid.setNum(8, 8, 8);
		
		for(int i = 0; i < 9; i++) {
			//playGrid.setNum(0, i, i+1);
			playGrid.setNum(i, 0, i+1);
		}
		
		playGrid.printPlayGrid();
		playGrid.checkSolved();
	}
}