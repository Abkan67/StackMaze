import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Display extends JComponent{
	
	private Game game; private JFrame frame;
	Display(Game game, int width, int height) {
		this.game=game;
		this.frame = new JFrame();
		this.frame.setSize(width,height);
		this.frame.setTitle("Maze Function Collapsed");
		this.frame.setDefaultCloseOperation(3);
		this.frame.add(this);
		this.frame.setVisible(true);
	}
	
	public JFrame getFrame(){return this.frame;}
	public void paintComponent(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		if(Game.currentGame.getMaze() !=null)
			Game.currentGame.getMaze().draw(g);
	}


}
