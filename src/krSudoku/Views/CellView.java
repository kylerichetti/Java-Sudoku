package krSudoku.Views;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;

import krSudoku.Models.CellModel;

public class CellView extends JPanel{
	private CellModel cellData;
	private JLabel value;
	
	public CellView(CellModel cellData) {
		this.cellData = cellData;
		this.cellData.addObserver(new ModelObserver());
		this.init();
	}
	
	public void init() {
		this.setLayout(new FlowLayout());
		this.setSize(75,75);
		this.setBackground(Color.CYAN);
		
		String cellValue;
		if(cellData.getNum() != 0) {
			cellValue = "" + cellData.getNum();
		}
		else {
			cellValue = " ";
		}
		value = new JLabel(cellValue);
		value.setFont(new Font("Serif", Font.BOLD, 22));
		this.add(value);
	}
	
	public void update() {
		
	}
	
	public void render() {
		
	}
	
	private class ModelObserver implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			if(cellData.getNum() != 0) {
				value.setText("" + cellData.getNum());
			}
			else {
				value.setText(" ");
			}
			value.repaint();
		}
	}
	
}
