import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsShape implements Shape {
    protected Point position;
    protected Map<String, Double> properties;
    protected Color color;
    protected Color fillColor;
    protected String name;

    public AbsShape(Point position, Color color, Color fillColor ) {
        this.position = position;
        this.properties = new HashMap<>();
        this.color = color;
        this.fillColor = fillColor;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public abstract void setProperties(Map<String, Double> properties);

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract void draw(Graphics canvas);
}
