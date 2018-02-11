package krSudoku.Models;

public class GridModel {
	private BlockModel[][] blocks;
	
	public GridModel() {
		blocks = new BlockModel[3][3];
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k ++) {
				blocks[i][k] = new BlockModel();
			}
		}
	}
	
	//Getters and Setters
	public boolean setNum(int row, int col, int num) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		return blocks[bRow][bCol].setCellNum(cRow, cCol, num);
	}
	
	public int getNum(int row, int col) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		return blocks[bRow][bCol].getCellNum(cRow, cCol);
	}
	
	//Checking victory conditions
	public boolean checkSolved() {
		boolean rowsFlag = true, 
				colsFlag = true, 
				blocksFlag = true;
		for(int i = 0; i < 9; i++) {
			if(!this.checkRow(i)) {
				rowsFlag = false;
				System.out.println("Row " + i + " failed");
				break;
			}
			if(!this.checkCol(i)) {
				colsFlag = false;
				System.out.println("Col " + i + " failed");
				break;
			}
			if(!this.checkBlock(i/3, i%3)) {
				blocksFlag = false;
				System.out.println("Block [" + i/3 + "][" + i%3 + "] failed");
				break;
			}
		}
		return rowsFlag && colsFlag && blocksFlag;
	}
	
	//Check row
	private boolean checkRow(int row) {
		boolean[] vals = new boolean[9];
		for(int i = 0; i < 9; i++) {
			int num = this.getNum(row, i);
			if(num != 0 && !vals[num-1]) {
				//The number hasn't already been found in the row
				vals[num-1] = true;
			}
			else {
				//A duplicate number was found
				//or the cell was empty
				return false;
			}
		}
		return true;
	}
	
	//Check column
	private boolean checkCol(int col) {
		boolean[] vals = new boolean[9];
		for(int i = 0; i < 9; i++) {
			int num = this.getNum(i, col);
			if(num != 0 && !vals[num-1]) {
				//The number hasn't already been found in the row
				vals[num-1] = true;
			}
			else {
				//A duplicate number was found
				//or the cell was empty
				return false;
			}
		}
		return true;
	}
	//Check block
	private boolean checkBlock(int row, int col) {
		return blocks[row][col].checkDuplicates();
	}
	
	//Print to the console for debugging
	public void printGridData() {
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				System.out.println("block[" + i + "][" + k + "]");
				blocks[i][k].printBlock();
				System.out.println("");
			}
		}
	}
	
	public void printPlayGrid() {
		for(int i = 0; i < 9; i++) {
			System.out.println("-------------------");
			for(int k = 0; k < 9; k++) {
				System.out.print("|" + this.getNum(i, k));
			}
			System.out.println("|");
		}
		System.out.println("-------------------");
	}
}

