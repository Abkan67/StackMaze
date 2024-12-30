import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;

public class Corner {
    private int x;
    private int y;
    private Vertex north;
    private Vertex south;
    private Vertex east;
    private Vertex west;

    public Vertex getNorth() {
        return this.north;
    }
    public void setNorth(Vertex north) {
        this.north = north;
    }
    public Vertex getSouth() {
        return this.south;
    }
    public void setSouth(Vertex south) {
        this.south = south;
    }
    public Vertex getEast() {
        return this.east;
    }
    public void setEast(Vertex east) {
        this.east = east;
    }
    public Vertex getWest() {
        return this.west;
    }
    public void setWest(Vertex west) {
        this.west = west;
    }    
    
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    Corner(int x, int y) {
        this.x=x; this.y=y;
    }
    public void draw(Graphics2D g, int minx, int miny, int width, int height, int thickness ) {
        g.setColor(Color.BLACK);
        g.fillRect(minx+width*x, miny+height*y, thickness,thickness);
    }
}
