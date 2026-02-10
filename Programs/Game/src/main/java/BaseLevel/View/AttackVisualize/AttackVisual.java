package BaseLevel.View.AttackVisualize;
import BaseLevel.Properties.Position;
import BaseLevel.Objects.Class_Base.DisplayableObject;

public abstract class AttackVisual implements DisplayableObject {

    public abstract void activate(Position startPos, Position endPos);
    public abstract void update(double deltaTime);
}
