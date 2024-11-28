import java.awt.*;
import java.util.Map;

public class LineSegment extends AbsShape {
    private static int lineCounter = 0;

    public LineSegment (Point start, Point end, Color color) {
        super(start, color, color); // Fill color is same as line color
        this.properties.put("endX", (double) end.getX());
        this.properties.put("endY", (double) end.getY());
        this.name = "LineSegment" + ++lineCounter;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        if (properties.containsKey("endX") && properties.containsKey("endY")) {
            this.properties.put("endX", properties.get("endX")); // Update x-coordinate of end point
            this.properties.put("endY", properties.get("endY")); // Update y-coordinate of end point
        }
    }

    @Override
    public void draw(Graphics canvas) {
        int endX = properties.get("endX").intValue();
        int endY = properties.get("endY").intValue();
        int startX = position.x;
        int startY = position.y;

        canvas.setColor(color);
        canvas.drawLine(startX, startY, endX, endY);
    }
}
