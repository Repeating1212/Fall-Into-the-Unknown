package Lvl_Sample.Managers;

import BaseLevel.Objects.Class_Base.Boss;
import Lvl_Sample.Enemy.Guard;
import BaseLevel.Manager.LevelSupplier;

public class LvlSupplier_Sample extends LevelSupplier {

    public Boss getBoss(Observer observer){
        return new Guard(observer);
    }
}
