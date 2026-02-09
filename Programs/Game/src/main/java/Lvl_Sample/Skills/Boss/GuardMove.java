package Lvl_Sample.Skills.Boss;

import Lvl_Sample.Data.Properties.Property;

public class GuardMove {

    public static void directTowardPlayer(Property player, Property guard){
        double offset = guard.getSpeed();
        if (Math.abs(player.getX() - guard.getX()) > offset){
            if (player.getX() > guard.getX()){
                guard.moveLeft(false);
                guard.moveRight(true);
            }
            else if (player.getX() < guard.getX()){
                guard.moveLeft(true);
                guard.moveRight(false);
            }
        } else{
            guard.moveLeft(false);
            guard.moveRight(false);
        }
        if (Math.abs(player.getY() - guard.getY()) > offset){
            if (player.getY() < guard.getY()){
                guard.moveDown(false);
                guard.moveUp(true);
            }
            else if (player.getY() > guard.getY()){
                guard.moveDown(true);
                guard.moveUp(false);
            }
        } else{
            guard.moveDown(false);
            guard.moveUp(false);
        }
    }
}
