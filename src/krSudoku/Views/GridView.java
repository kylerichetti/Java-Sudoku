package krSudoku.Views;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import krSudoku.Models.*;

public class GridView extends JFrame {
	private GridModel gameData;
	private JPanel panel;
	private GridBagLayout layout;
	private BlockView[][] blocks;
	
	public GridView(String name, GridModel gameData) {
		super(name);
		
		this.gameData = gameData;
		this.blocks = new BlockView[3][3];
		
		this.setResizable(false);
		this.prepareGui();
	}
	
	private void prepareGui() {
		this.setSize(930,930);
		this.setLayout(new FlowLayout());
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		layout = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setLayout(layout);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(2,2,2,2);
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		
		for(int i = 0; i < 3; i++) {
			for(int k = 0; k < 3; k++) {
				gbc.gridx = k;
				gbc.gridy = i;
				
				blocks[i][k] = new BlockView(gameData.getBlock(i, k));
				
				panel.add(blocks[i][k], gbc);
			}
		}
		
		this.add(panel);
		this.setVisible(true);
	}
}
