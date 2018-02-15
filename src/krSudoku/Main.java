package krSudoku;

import krSudoku.Models.GridModel;
import krSudoku.Views.GridView;

public class Main {
	public static void main(String args[]) {
		GridModel playGrid = new GridModel();
		
		playGrid.generateTestPuzzle();
		
		new GridView("krSudoku", playGrid);
		
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