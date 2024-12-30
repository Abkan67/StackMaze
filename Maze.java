import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Maze implements Runnable{
    private int size = 50;
    private int width; private int height; private int x; private int y;
    private ArrayList<Square> boxes = new ArrayList<Square>();
    private Cursor point;
    public Maze(int x, int y, int width, int height, int totalWidth, int totalHeight) {
        this.x=x; this.y=y;
        this.size = Math.min((int) (totalHeight/height), (int)(totalWidth/width));
        this.width=width;this.height=height;
        addSquares();
        this.point = new Cursor((int)(Math.random()*width), (int)(Math.random()*height));
    }
    private void addSquares() {
        for(int y= 0; y<height; y++) {
            for(int x = 0; x<width; x++) {
                this.boxes.add(new Square(x,y, y!=height-1, x!=width-1));
            }
        }
    }
    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(10));
        g.drawRect(x, y, width*size, height*size);
        for(Square square: boxes) {
            square.draw(g, x, y, size, size);
        }
        if(running){
            g.setColor(Color.blue);
            g.fillArc(point.x()*size+size/4+x, point.y()*size+size/4+y, size/2, size/2, 0, 360);
        }
//        g.setColor(g.getBackground());
//        g.fillRect(x-5, y, 5, size);
//        g.fillRect(x+width*size, y+height*size-size, 15, size);
    }
    public Square getSquare(int x, int y) {
        return this.boxes.get(x+y*width);
    }
    public Square getSquare() {return getSquare(point.x(),point.y());}

    private ArrayList<Square> stack = new ArrayList<Square>();
    public void update() {
        getSquare(point.x(), point.y()).visit();
        movePoint();
    }
    private void movePoint(){
        ArrayList<Integer> validPoints = new ArrayList<Integer>();
        if(point.x()!=0 && !getSquare(point.x()-1, point.y()).isVisited()){validPoints.add(3);}
        if(point.x()!=width-1  &&  !getSquare(point.x()+1, point.y()).isVisited()) {validPoints.add(1);}
        if(point.y()!=0  && !getSquare(point.x(), point.y()-1).isVisited()) {validPoints.add(0);}
        if(point.y()!=height-1  && !getSquare(point.x(), point.y()+1).isVisited()) {validPoints.add(2);}
        if(validPoints.size()>0){
            switch (validPoints.get((int)(Math.random()*validPoints.size()))) {
                case 0: moveNorth(); return; 
                case 1: moveEast(); return; 
                case 2: moveSouth(); return; 
                case 3: moveWest(); return; 
            }
        } else {
            backtrack();
        }
        if(checkIfDone()){stop();};
    }
    public boolean checkIfDone() {
        for(Square box: boxes){if(!box.isVisited()){return false;}}
        return true;
    }
    public void backtrack() {
        point.setX(stack.get(stack.size()-1).getX());
        point.setY(stack.get(stack.size()-1).getY());
        stack.remove(stack.size()-1);
    }
    private void set() {
        stack.add(getSquare(point.x(), point.y()));
    }
    public void moveNorth() {set(); point.setY(point.y()-1);  getSquare().getSouth().shatter();}
    public void moveEast() {set();  getSquare().getEast().shatter();  point.setX(point.x()+1);  }
    public void moveSouth() {set();  getSquare().getSouth().shatter();  point.setY(point.y()+1);}
    public void moveWest() {set();point.setX(point.x()-1);  getSquare().getEast().shatter();}

    private boolean running = false; private Thread thread;
    public synchronized void start() {this.running = true; this.thread = new Thread(this);this.thread.start();}
    public synchronized void stop() {this.running = false; }
    public void run() {
        while(running){this.update(); thisSleep();}
    }
    private void thisSleep() {try {Thread.sleep(Game.mazeDelay);} catch (InterruptedException e) { e.printStackTrace(); }}
}
