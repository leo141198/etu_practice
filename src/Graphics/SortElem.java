package Graphics;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SortElem extends Component {
    private float x;
    private float y;
    private float width;
    private float height;
    private Integer value;

    public SortElem(Integer value, float x, float y, float width, float height) {
        this.value = value;
        setRectangle(x, y, width, height);
    }

    public SortElem(SortElem other) {
        this.value = other.value;
        setRectangle(other.x, other.y, other.width, other.height);
    }

    public Integer getValue() {
        return value;
    }

    public Point2D.Float getCoordinates() {
        return new Point2D.Float(x, y);
    }

    public void setCoordinates(Point2D.Float coordinates) {
        this.x = coordinates.x;
        this.y = coordinates.y;
    }

    public void setRectangle(float x, float y, float width, float height) {
        setCoordinates(new Point2D.Float(x, y));
        this.width = width;
        this.height = height;
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(141, 151, 255));
        Rectangle2D r2d = new Rectangle2D.Float(x, y - height, width, height);
        g2d.fill(r2d);
        g.setColor(Color.black);
        g2d.draw(r2d);
    }
}
