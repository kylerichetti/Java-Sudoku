package krSudoku.Views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import krSudoku.Models.*;

public class GridView extends JFrame {
	private GridModel gameData;
	private JPanel panel;
	private GridBagLayout layout;
	private CellView[][] cells;
	
	public GridView(String name, GridModel gameData) {
		super(name);
		
		this.gameData = gameData;
		this.cells = new CellView[9][9];
		
		this.setResizable(false);
		this.prepareGui();
	}
	
	private void prepareGui() {
		this.setSize(700,500);
		this.setLayout(new FlowLayout());
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		panel = new JPanel();
		layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(layout);
		gbc.fill = GridBagConstraints.BOTH;
		//gbc.ipady = 20;
		gbc.insets = new Insets(2,2,2,2);
		
		for(int i = 0; i < 9; i++) {
			for(int k = 0; k < 9; k++) {
				gbc.gridx = k;
				gbc.gridy = i;
				
				cells[i][k] = new CellView(gameData.getCell(i, k));
				
				panel.add(cells[i][k], gbc);
			}
		}
		
		this.add(panel);
		this.setVisible(true);
	}
}
