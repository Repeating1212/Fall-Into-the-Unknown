package Level.View.AttackVisualize;
import Level.Data.Properties.Position;
import Level.Objects.Base_Class.DisplayableObject;

public abstract class AttackVisual implements DisplayableObject {

    public abstract void activate(Position startPos, Position endPos);
    public abstract void update(double deltaTime);
}
