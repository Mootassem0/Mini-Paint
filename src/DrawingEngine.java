public interface DrawingEngine {
    public void addShapes(Shape shape);
    public void removeShapes(Shape shape);

    public Shape[] getShapes();
    public void refresh(java.awt.Graphics canvas);
}
