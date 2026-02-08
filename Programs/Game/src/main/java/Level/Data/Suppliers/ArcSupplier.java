package Level.Data.Suppliers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

public class ArcSupplier {

    public static Arc getArc(double radius, double angle){
        Arc arc = new Arc();
        arc.setRadiusX(radius);
        arc.setRadiusY(radius);
        arc.setLength(angle); // Spread angle (e.g., 90 degrees for cone)
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 0, 0, 0.3)); // Semi-transparent red
        arc.setStroke(Color.RED);
        arc.setStrokeWidth(2);
        arc.setVisible(false);
        return arc;
    }
}
