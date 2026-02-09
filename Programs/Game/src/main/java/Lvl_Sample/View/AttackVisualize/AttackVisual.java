package Lvl_Sample.View.AttackVisualize;
import Lvl_Sample.Data.Properties.Position;
import Lvl_Sample.Objects.Base_Class.DisplayableObject;

public abstract class AttackVisual implements DisplayableObject {

    public abstract void activate(Position startPos, Position endPos);
    public abstract void update(double deltaTime);
}
