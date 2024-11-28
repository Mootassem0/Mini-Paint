import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Engine implements DrawingEngine {

    private List<Shape> shapes;

    public Engine() {
        this.shapes = new ArrayList<>();
    }

    @Override
    public void addShapes (Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShapes (Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]); // Return an array of shapes
    }

    @Override
    public void refresh(Graphics canvas){
        for (Shape shape:shapes) {
            shape.draw(canvas);
        }
    }
}
