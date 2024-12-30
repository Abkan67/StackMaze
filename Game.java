import javax.swing.JOptionPane;

public class Game {
	public static long mazeDelay = 100;
	private Display display; static Game currentGame;private Animation animator; private Maze maze;
	private int width; private int height;
	private int totalWidth = 1400;
	private int totalHeight = 650;
	Game() {
		currentGame = this;
		this.display = new Display(this, totalWidth,totalHeight);
		setupGame();
	}
	public void setupGame(){
		width = Integer.parseInt(JOptionPane.showInputDialog(display.getFrame(), "Select Width"));
		height = Integer.parseInt(JOptionPane.showInputDialog(display.getFrame(), "Select Height"));
		
		maze = new Maze(50,50,width,height, totalWidth-50,totalHeight-50);
		maze.start();

		this.animator = new Animation(this);
		animator.start();
	}
	public void tick(){
		this.display.repaint();
	}
	public void closeEverything(){
		this.animator.stop();
		this.maze.stop();
	}
	public void restart() {
		closeEverything();
		setupGame();
	}

	public Display getDisplay() {return display;}
	public Animation getAnimator() {return animator;}
	public Maze getMaze() {return maze;}

	public static void main(String[] args) {
		new Game();
	}

}