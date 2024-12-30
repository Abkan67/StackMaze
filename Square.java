import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Square {
    private Vertex east;
    private Vertex south;
    private int x;
    private int y;
    private boolean visited = false;
    public int getY() {
        return this.y;
    }
    public boolean isVisited() {return visited;}
    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }
    public Vertex getSouth(){return south;}
    public Vertex getEast(){return east;}

    Square(int x, int y, boolean hasSouth, boolean hasEast){
        this.x=x;this.y=y;
        if(hasSouth)
            this.south = new Vertex(this);
        if(hasEast)
            this.east = new Vertex(this);
    }
    private static int COLORRED = 30; private static int COLORBLUE = 30; private static int COLORGREEN = 30;
    private Color c2 = Color.black;
    private void setColor() {
        int colorToIncrease = (int)(Math.random()*3);
        if(colorToIncrease==0){COLORBLUE--;COLORGREEN--;COLORRED+=2;}
        if(colorToIncrease==1){COLORGREEN--;COLORRED--;COLORBLUE+=2;}
        if(colorToIncrease==2){COLORRED--;COLORBLUE--;COLORGREEN+=2;}
        COLORRED=Math.max(0,Math.min(255,COLORRED));
        COLORGREEN = Math.max(0,Math.min(255,COLORGREEN));
        COLORBLUE = Math.max(0,Math.min(255,COLORBLUE));
        c2 = new Color(COLORRED,COLORGREEN,COLORBLUE);
    }
    public void visit() {
        if(!visited||true){setColor();}this.visited=true;
    }
    public void unVisit() {
        this.visited = false; c2 = Color.black;
    }
    public void draw(Graphics2D g, int minx, int miny, int xsize, int ysize) {

        g.setStroke(new BasicStroke(1));
        g.setColor(Color.white);
        if(visited){
            g.setColor(new Color(190,180,130, 100));
            g.setColor(c2);
        }
        g.fillRect(minx+xsize*x, miny+ysize*y, xsize, ysize);
        if(south!=null)
            south.draw(g, minx+xsize*x, miny+ysize*y+ysize, minx+xsize*x+xsize, miny+ysize*y+ysize);
        if(east!=null)
            east.draw(g, minx+xsize*x+xsize, miny+ysize*y, minx+xsize*x+xsize, miny+ysize*y+ysize);
    }

}
