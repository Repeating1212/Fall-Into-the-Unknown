package Level.BaseLevel.View.AttackVisualize;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;

public abstract class AttackVisual implements DisplayableObject {

    public abstract void activate(Position startPos, Position endPos);
    public abstract void update(double deltaTime);
}
