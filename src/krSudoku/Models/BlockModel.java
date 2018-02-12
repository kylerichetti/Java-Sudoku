package krSudoku.Models;

public class BlockModel {
	private CellModel[][] cells;
	
	public BlockModel() {
		cells = new CellModel[3][3];
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				cells[i][k] = new CellModel(0);
			}
		}
	}
	
	//Check that there are no duplicate numbers
	public boolean checkDuplicates() {
		boolean[] vals = new boolean[9];
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				int num = this.getCellNum(i, k);
				if(num != 0) {
					if(!vals[num-1]) {
						//The number hasn't already been found
						vals[num-1] = true;
					}
					else {
						//A duplicate number was found
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//Getters and Setters
	public int getCellNum(int row, int col) {
		return cells[row][col].getNum();
	}
	
	public boolean getCellCanEdit(int row, int col) {
		return cells[row][col].getCanEdit();
	}
	
	public boolean setCellNum(int row, int col, int num) {
		return cells[row][col].setNum(num);
	}
	
	public void setCellCanEdit(int row, int col, boolean canEdit) {
		cells[row][col].setCanEdit(canEdit);
	}
	
	public CellModel getCell(int row, int col) {
		return cells[row][col];
	}
	
	public void printBlock() {
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				System.out.println("cell[" + i + "][" + k + "]:" + cells[i][k].getNum());
			}
		}
	}
	
}
