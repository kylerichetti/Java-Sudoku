package krSudoku;

public class Cell {
	private byte num;
	
	public Cell(byte num) {
		this.setNum(num);
	}
	
	public byte getNum() {
		return this.num;
	}
	public void setNum(byte num) {
		if(num >= 0 && num <=9) {
			this.num = num;
		}
	}
}
