package EditorUI;

public class EquipmentObserver {

    private final Equipment equipment;

    protected  EquipmentObserver (Equipment equipment){
        this.equipment = equipment;
    }

    public void reloadData(){
        equipment.reloadData();
    }
}
