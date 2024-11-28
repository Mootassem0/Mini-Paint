import java.awt.*;
import java.util.Map;

public class Square extends AbsShape {
    private static int squareCounter = 0;

    public Square(Point position, double sideLength, Color color, Color fillColor) {
        super(position, color, fillColor);
        this.properties.put("sideLength", sideLength);
        this.name = "Square" + ++squareCounter;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        if (properties.containsKey("sideLength")) {
            this.properties.put("sideLength", properties.get("sideLength")); // Update sideLength
        }
    }

    @Override
    public void draw(Graphics canvas) {
        int sideLength = properties.get("sideLength").intValue();
        int x = position.x; // x-coordinate of the top-left corner
        int y = position.y; // y-coordinate of the top-left corner

        // Draw filled square
        canvas.setColor(fillColor);
        canvas.fillRect(x, y, sideLength, sideLength);

        // Draw square border
        canvas.setColor(color);
        canvas.drawRect(x, y, sideLength, sideLength);
    }
}
