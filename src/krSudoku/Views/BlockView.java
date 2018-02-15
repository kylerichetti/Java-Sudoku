package krSudoku.Views;

import krSudoku.Models.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;

public class BlockView extends JPanel{
	private BlockModel block;
	private CellView[][] cells;
	
	public BlockView(BlockModel block) {
		this.block = block;
		this.cells = new CellView[3][3];
		
		this.prepareGui();
	}
	
	private void prepareGui() {
		this.setSize(250,250);
		GridBagLayout layout = new GridBagLayout();
		//this.setLayout(new GridBagLayout());
		this.setLayout(layout);
		this.setBackground(Color.black);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(1,1,1,1);
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				gbc.gridx = k;
				gbc.gridy = i;
				
				cells[i][k] = new CellView(this.block.getCell(i, k));
				
				this.add(cells[i][k], gbc);
			}
		}
	}
}
