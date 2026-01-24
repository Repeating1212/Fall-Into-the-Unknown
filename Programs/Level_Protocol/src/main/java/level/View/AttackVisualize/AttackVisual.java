package level.View.AttackVisualize;
import level.Data.Properties.Position;
import level.Objects.Base_Class.DisplayableObject;

public abstract class AttackVisual implements DisplayableObject {

    public abstract void activate(Position startPos, Position endPos);
    public abstract void update(double deltaTime);
}
