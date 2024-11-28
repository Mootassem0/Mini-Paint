import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private JPanel MainWindowP;
    private JButton circleButton;
    private JButton lineSegmentButton;
    private JButton squareButton;
    private JButton rectangleButton;
    private JComboBox<Shape> shapesComboBox;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JLabel selectShapeLabel;
    private PaintCanvas PaintCanvas;  // Custom JPanel for drawing

    private DrawingEngine engine;


    public MainWindow() {
        setTitle("Vector Drawing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        engine = new Engine();

        // Initialize main layout panel and set up controls
        MainWindowP = new JPanel(new BorderLayout());
        setContentPane(MainWindowP);


        // Control panel with buttons and shape dropdown
        JPanel shapePanel = new JPanel(new GridLayout(1, 5, 5, 5));
        circleButton = new JButton("Circle");
        lineSegmentButton = new JButton("Line Segment");
        squareButton = new JButton("Square");
        rectangleButton = new JButton("Rectangle");

        shapePanel.add(new JLabel("")); // Leaving first column empty
        shapePanel.add(circleButton);
        shapePanel.add(lineSegmentButton);
        shapePanel.add(squareButton);
        shapePanel.add(rectangleButton);

//        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        JPanel controlPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding values
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        shapesComboBox = new JComboBox<>();
        colorizeButton = new JButton("Colorize");
        deleteButton = new JButton("Delete");

        controlPanel.add(new JLabel("Select Shape:"), gbc);
        gbc.gridy++;
        controlPanel.add(shapesComboBox, gbc);
        gbc.gridy++;

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonsPanel.add(colorizeButton);
        buttonsPanel.add(deleteButton);

        controlPanel.add(buttonsPanel, gbc);

        // Button actions
        circleButton.addActionListener(e -> createShape("Circle"));
        lineSegmentButton.addActionListener(e -> createShape("LineSegment"));
        squareButton.addActionListener(e -> createShape("Square"));
        rectangleButton.addActionListener(e -> createShape("Rectangle"));
        colorizeButton.addActionListener(e -> colorizeShape());
        deleteButton.addActionListener(e -> deleteShape());

        // Custom PaintCanvas panel
        PaintCanvas = new PaintCanvas(engine);
        PaintCanvas.setBackground(Color.WHITE);

        // Add control panel to the left and PaintCanvas to the center
        MainWindowP.add(shapePanel, BorderLayout.NORTH);
        MainWindowP.add(controlPanel, BorderLayout.WEST);
        MainWindowP.add(PaintCanvas, BorderLayout.CENTER);

        setVisible(true);
    }

    private void createShape(String shapeType) {
        try {
            // Get position with validation
            Integer xPosition = getValidInteger("Enter X-position of the top-left corner:");
            if (xPosition == null) return; // Exit if canceled
            Integer yPosition = getValidInteger("Enter Y-position of the top-left corner:");
            if (yPosition == null) return; // Exit if canceled
            Point position = new Point(xPosition, yPosition);

            Color color = Color.BLACK;  // Default border color
            Color fillColor = Color.WHITE; // Default fill color

            Shape shape;

            switch (shapeType) {
                case "Circle":
                    Double radius = getValidDouble("Enter radius:");
                    if (radius == null) return; // Exit if canceled
                    shape = new Circle(position, radius, color, fillColor);
                    break;

                case "Rectangle":
                    Double width = getValidDouble("Enter width:");
                    if (width == null) return; // Exit if canceled
                    Double height = getValidDouble("Enter height:");
                    if (height == null) return; // Exit if canceled
                    shape = new Rectangle(position, height, width, color, fillColor);
                    break;

                case "Square":
                    Double side = getValidDouble("Enter side length:");
                    if (side == null) return; // Exit if canceled
                    shape = new Square(position, side, color, fillColor);
                    break;

                case "LineSegment":
                    Integer endX = getValidInteger("Enter X end position:");
                    if (endX == null) return; // Exit if canceled
                    Integer endY = getValidInteger("Enter Y end position:");
                    if (endY == null) return; // Exit if canceled
                    Point end = new Point(endX, endY);
                    shape = new LineSegment(position, end, color);
                    break;

                default:
                    throw new IllegalArgumentException("Unknown shape type");
            }

            engine.addShapes(shape);
            shapesComboBox.addItem(shape);
            PaintCanvas.repaint();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numerical value.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Helper method for integer input validation
    private Integer getValidInteger(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            if (input == null) return null; // Exit if canceled
            try {
                return Integer.parseInt(input); // Return valid integer input
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Helper method for double input validation
    private Double getValidDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(this, message);
            if (input == null) return null; // Exit if canceled
            try {
                return Double.parseDouble(input); // Return valid double input
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid decimal number.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void colorizeShape() {
        Shape selectedShape = (Shape) shapesComboBox.getSelectedItem(); // Get selected shape directly
        if (selectedShape == null) return;

        // Prompt user to choose color
        Color color = JColorChooser.showDialog(this, "Choose a color", Color.BLACK);
        if (color != null) {
            String[] options = {"Outline", "Fill"};
            int choice = JOptionPane.showOptionDialog(this, "Select color type:", "Change Shape Color",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) { // Outline
                selectedShape.setColor(color);
            } else if (choice == 1) { // Fill
                selectedShape.setFillColor(color);
            }
            PaintCanvas.repaint(); // Refresh the canvas to show changes
        }
    }

    private void deleteShape() {
        Shape selectedShape = (Shape) shapesComboBox.getSelectedItem(); // Get selected shape directly
        if (selectedShape == null) return;

        engine.removeShapes(selectedShape); // Remove the shape from the engine
        shapesComboBox.removeItem(selectedShape); // Remove it from the combo box
        PaintCanvas.repaint(); // Refresh the canvas
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
