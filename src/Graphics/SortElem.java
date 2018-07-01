package Graphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SortElem extends Component {
    private float x;
    private float y;
    private float width;
    private float height;
    private Integer value;
    public Color color;

    public SortElem(Integer value, float x, float y, float width, float height) {
        this.value = value;
        setRectangle(x, y, width, height);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getValue() {
        return value;
    }


    public void setRectangle(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }


    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);
        Rectangle2D r2d = new Rectangle2D.Float(x, y - height, width, height);
        g2d.fill(r2d);
        g.setColor(Color.black);
        g2d.draw(r2d);
    }
}
