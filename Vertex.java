import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Vertex {
    private Square square;
    private boolean intact = true;
    public Vertex(Square square) {
        this.square= square;
    }
    public Square getSquare() {
        return this.square;
    }
    public void shatter(){this.intact=false;}
    public void draw(Graphics2D g, int x, int y, int x2, int y2) {
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        if(this.intact) {
            g.drawLine(x, y, x2, y2);
        }

    }
}
