package Lvl_Sample.Skills.Attacks;//package level.Workers.Behaviour.Attacks;
//
//import level.Storage.Data.HitBox;
//import level.Storage.Data.Position;
//import level.Workers.GameObject.ConeAttackVisual;
//
//
//public class CircleAttackArea implements AttackArea {
//    private double centerX;
//    private double centerY;
//    private double radius;
//    private ConeAttackVisual coneAttackVisual;
//
//    public CircleAttackArea(double radius) {
//        this.radius = radius;
////        createDebugShape();
//    }
//
//    @Override
//    public void updatePosition(double centerX, double centerY, Vector2D direction) {
//        this.centerX = centerX;
//        this.centerY = centerY;
////        updateDebugShape();
//    }
//
//    @Override
//    public boolean isPointInArea(double pointX, double pointY) {
//        double dx = pointX - centerX;
//        double dy = pointY - centerY;
//        return (dx * dx + dy * dy) <= (radius * radius);
//    }
//
//    @Override
//    public boolean isHitBoxInArea(HitBox hitBox, Position position) {
//        // Find closest point on hitbox to circle center
//        double closestX = clamp(centerX, position.getX(), position.getX() + hitBox.getWidth());
//        double closestY = clamp(centerY, position.getY(), position.getY() + hitBox.getHeight());
//
//        double dx = centerX - closestX;
//        double dy = centerY - closestY;
//
//        return (dx * dx + dy * dy) <= (radius * radius);
//    }
//
//    private double clamp(double value, double min, double max) {
//        return Math.max(min, Math.min(max, value));
//    }
//
////    private void createDebugShape() {
////        debugShape = new Circle(radius);
////        debugShape.setFill(Color.rgb(255, 0, 0, 0.2));
////        debugShape.setStroke(Color.RED);
////        debugShape.setStrokeWidth(1);
////    }
////
////    private void updateDebugShape() {
////        debugShape.setCenterX(centerX);
////        debugShape.setCenterY(centerY);
////    }
//
//    @Override
//    public ConeAttackVisual getAttackVisual(){
//        return coneAttackVisual;
//    }
//}