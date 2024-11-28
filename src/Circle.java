import java.awt.*;
import java.util.Map;

public class Circle extends AbsShape {
    private static int circleCounter = 0;

    public Circle (Point position, double radius, Color color, Color fillColor) {
        super(position,color,fillColor);
        this.properties.put("radius",radius);
        this.name = "Circle" + ++circleCounter;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        if (properties.containsKey("radius")) {
            this.properties.put("radius", properties.get("radius")); // Update radius
        }
    }

    @Override
    public void draw(Graphics canvas){
        int radius = properties.get("radius").intValue();
        int x = position.x; // x-coordinate of the top-left corner
        int y = position.y;  // y-coordinate of the top-left corner

        // Draw filled circle
        canvas.setColor(fillColor);
        canvas.fillOval(x, y, radius * 2, radius * 2);

        // Draw circle border
        canvas.setColor(color);
        canvas.drawOval(x, y, radius * 2, radius * 2);
    }
}
