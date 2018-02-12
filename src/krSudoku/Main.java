package krSudoku;

import krSudoku.Models.GridModel;
import java.util.Random;

public class Main {
	public static void main(String args[]) {
		GridModel playGrid = new GridModel();
		
		Random rand = new Random(3005003);
		
		for(int i = 0; i < 62; i++) {
			int row = rand.nextInt(9);
			int col = rand.nextInt(9);
			int num = rand.nextInt(8) + 1;
			
			if(playGrid.getCanEdit(row, col)) {
				playGrid.setNum(row, col, num);
				playGrid.setCanEdit(row, col, false);
				
				//Check valid
				if(!playGrid.checkSolved(false)) {
					playGrid.setCanEdit(row, col, true);
					playGrid.setNum(row, col, 0);
					i--;
					//System.out.println("Invalid");
				}
			}
			else {
				i -= 1;
				//System.out.println("Dup");
			}
		}
		
		int tally = 0;
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				if(playGrid.getNum(i, k) != 0) {
					tally += 1;
				}
			}
		}
		
		/*playGrid.setNum(1, 1, 3);
		playGrid.setNum(4, 5, 1);
		playGrid.setNum(7, 2, 9);
		
		playGrid.setCanEdit(1, 1, false);
		playGrid.setCanEdit(4, 5, false);
		playGrid.setCanEdit(7, 2, false);*/
		
		playGrid.printPlayGrid();
		System.out.println(tally);
		//System.out.println(playGrid.checkSolved(false));

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