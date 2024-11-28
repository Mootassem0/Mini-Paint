import javax.swing.*;
import java.awt.*;

public class PaintCanvas extends JPanel {
    private DrawingEngine engine;

    public PaintCanvas(DrawingEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        engine.refresh(g); // Draw all shapes in the engine
    }
}