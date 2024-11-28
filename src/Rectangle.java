import java.awt.*;
import java.util.Map;

public class Rectangle extends AbsShape {
    private static int rectangleCounter = 0;

    public Rectangle (Point position, double height, double width, Color color, Color fillColor) {
        super(position,color,fillColor);
        this.properties.put("height",height);
        this.properties.put("width",width);
        this.name = "Rectangle" + ++rectangleCounter;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        if (properties.containsKey("height")) {
            this.properties.put("height", properties.get("height")); // Update height
        }
        if (properties.containsKey("width")) {
            this.properties.put("width", properties.get("width")); // Update width
        }
    }

    @Override
    public void draw(Graphics canvas) {
        int width = properties.get("width").intValue();
        int height = properties.get("height").intValue();
        int x = position.x; // x-coordinate of the top-left corner
        int y = position.y; // y-coordinate of the top-left corner

        // Draw filled rectangle
        canvas.setColor(fillColor);
        canvas.fillRect(x, y, width, height); // Fill the rectangle

        // Draw rectangle border
        canvas.setColor(color);
        canvas.drawRect(x, y, width, height); // Draw the outline
    }
}
