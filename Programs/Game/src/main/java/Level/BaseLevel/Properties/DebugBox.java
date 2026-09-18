package Level.BaseLevel.Properties;

import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;

public class DebugBox implements DisplayableObject {

    private final Rectangle debugView;
    private final int LAYER = 0;

    public DebugBox(Rectangle debugView){
        this.debugView = debugView;
    }

    @Override
    public Node getSprite() {
        return debugView;
    }

    @Override
    public int getLayer() {
        return LAYER;
    }
}
