package krSudoku;

import krSudoku.Models.GridModel;
import krSudoku.Views.GridView;

public class Main {
	public static void main(String args[]) {
		GridModel playGrid = new GridModel();
		
		playGrid.generateTestPuzzle();
		
		new GridView("Test", playGrid);
		
		/*
		playGrid.setCanEdit(5, 2, true);
		playGrid.setNum(5, 2, 0);
		
		playGrid.setNum(1, 3, 4);
		playGrid.setCanEdit(1, 3, false);
		
		playGrid.setNum(2, 6, 4);
		playGrid.setCanEdit(2, 6, false);
		
		playGrid.setNum(3, 1, 4);
		playGrid.setCanEdit(3, 1, false);
		
		playGrid.setNum(4, 4, 4);
		playGrid.setCanEdit(4, 4, false);
		/**/
		playGrid.printPlayGrid();
		
		/**/
		if(playGrid.autoSolve()) {
			System.out.println("Solved!");
		}
		else {
			System.out.println("No solution");
		}
		playGrid.printPlayGrid();
		/**/
	}
}