package krSudoku.Models;

public class CellModel {
	private int num;
	
	public CellModel(int num) {
		this.setNum(num);
	}
	
	public int getNum() {
		return this.num;
	}
	
	public boolean setNum(int num) {
		if(num >= 0 && num <=9) {
			this.num = num;
			return true;
		}
		return false;
	}
}
