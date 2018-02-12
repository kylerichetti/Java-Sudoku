package krSudoku.Models;

import java.util.Random;

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
	
	public void setCanEdit(int row, int col, boolean canEdit) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		blocks[bRow][bCol].setCellCanEdit(cRow, cCol, canEdit);
	}
	
	public int getNum(int row, int col) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		return blocks[bRow][bCol].getCellNum(cRow, cCol);
	}
	
	public boolean getCanEdit(int row, int col) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		return blocks[bRow][bCol].getCellCanEdit(cRow, cCol);
	}
	
	public CellModel getCell(int row, int col) {
		int bRow = row/3;
		int bCol = col/3;
		int cRow = row%3;
		int cCol = col%3;
		return blocks[bRow][bCol].getCell(cRow, cCol);
	}
	
	//Checking victory conditions
	public boolean checkSolved(boolean testBlanks) {
		boolean rowsFlag = true, 
				colsFlag = true, 
				blocksFlag = true,
				blanksFlag = true;
		if(testBlanks) {
			blanksFlag = this.checkBlanks();
		}
		for(int i = 0; i < 9; i++) {
			if(!this.checkRow(i)) {
				rowsFlag = false;
				//System.out.println("Row " + i + " failed");
				break;
			}
			if(!this.checkCol(i)) {
				colsFlag = false;
				//System.out.println("Col " + i + " failed");
				break;
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				if(!this.checkBlock(i, k)) {
					blocksFlag = false;
					//System.out.println("Block [" + i/3 + "][" + i/3 + "] failed");
					break;
				}
			}
		}
		return rowsFlag && colsFlag && blocksFlag && blanksFlag;
	}
	
	//Check row
	private boolean checkRow(int row) {
		boolean[] vals = new boolean[9];
		for(int i = 0; i < 9; i++) {
			int num = this.getNum(row, i);
			if(num != 0) {
				if(!vals[num-1]) {
					//The number hasn't already been found in the row
					vals[num-1] = true;
				}
				else {
					//A duplicate number was found
					return false;
				}
			}
		}
		return true;
	}
	
	//Check column
	private boolean checkCol(int col) {
		boolean[] vals = new boolean[9];
		for(int i = 0; i < 9; i++) {
			int num = this.getNum(i, col);
			if(num != 0) {
				if(!vals[num-1]) {
					//The number hasn't already been found in the row
					vals[num-1] = true;
				}
				else {
					//A duplicate number was found
					return false;
				}
			}
		}
		return true;
	}
	//Check block
	private boolean checkBlock(int row, int col) {
		return blocks[row][col].checkDuplicates();
	}
	
	//Check blanks
	private boolean checkBlanks() {
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				int num = this.getNum(i, k);
				if(num == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	//Method to generate a puzzle solution
	public boolean autoSolve() {
		for(int i = 1; i <= 9; i++) {
			if(autoSolve(0,0,i)) {
				//Solved
				return true;
			}
		}
		return false;
	
	}
	private boolean autoSolve(int row, int col, int num) {
		int startNum = 1;
		if(this.getCanEdit(row, col)) {
			//Can be edited, start plugging away
			//Plug in number
			this.setNum(row, col, num);
			//Check if current number is invalid
			//Check if row is valid
			if(!this.checkRow(row)) {
				return false;
			}
			//Check if column is valid
			if(!this.checkCol(col)) {
				return false;
			}
			//Check if block is valid
			if(!this.checkBlock(row/3, col/3)) {
				return false;
			}
		}
		//If it can't be edited or the current num
		//is valid, jump to the next cell
		int nextRow, nextCol;
		if(row == 8 && col == 8) {
			//Last cell
			//Check if solved
			return this.checkSolved(true);
			
		}
		else if(row == 8) {
			nextCol = col + 1;
			nextRow = 0;
		}
		else {
			nextCol = col;
			nextRow = (row + 1) % 9;
		}
		//Start loop that calls self on next cell
		if(!this.getCanEdit(nextRow, nextCol)) {
			startNum = 9;
		}
		for(int i = startNum; i <= 9; i++) {
			if(this.autoSolve(nextRow, nextCol, i)) {
				return true;
			}
		}
		
		this.setNum(nextRow, nextCol, 0);
		return false;
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
	
	public void generateTestPuzzle() {
		//Seed: 564847, i < 25 is interesting to watch
		//Seed: 31415, i < 26 is interesting, and solves
		
		Random rand = new Random(31415);
		
		for(int i = 0; i < 27; i++) {
			int row = rand.nextInt(9);
			int col = rand.nextInt(9);
			int num = rand.nextInt(8) + 1;
			
			if(this.getCanEdit(row, col)) {
				this.setNum(row, col, num);
				this.setCanEdit(row, col, false);
				
				//Check valid
				if(!this.checkSolved(false)) {
					this.setCanEdit(row, col, true);
					this.setNum(row, col, 0);
					i--;
				}
			}
			else {
				i -= 1;
			}
		}
	}
}

