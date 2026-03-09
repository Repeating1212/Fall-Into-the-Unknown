package Game_UI.EquipmentScene;

public class EquipmentObserver {

    private final EquipmentController equipmentController;

    protected  EquipmentObserver (EquipmentController equipmentController){
        this.equipmentController = equipmentController;
    }

    public void reloadData(){
        equipmentController.reloadData();
    }
}
