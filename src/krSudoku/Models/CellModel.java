package krSudoku.Models;

import java.util.Observable;

public class CellModel extends Observable{
	private int num;
	private boolean canEdit;
	
	public CellModel(int num) {
		this.setNum(num);
		this.canEdit = true;
	}
	
	public boolean getCanEdit() {
		return this.canEdit;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public void setCanEdit(boolean canEdit) {
		this.canEdit = canEdit;
	}
	
	public boolean setNum(int num) {
		if(!this.canEdit) {
			return false;
		}
		if(num >= 0 && num <=9) {
			this.num = num;
			setChanged();
			notifyObservers();
			return true;
		}
		this.num = 0;
		setChanged();
		notifyObservers();
		return false;
	}
}
