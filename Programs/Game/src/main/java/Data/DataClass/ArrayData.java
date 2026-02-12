package Data.DataClass;

import java.util.ArrayList;

public class ArrayData<T> {

    private final ArrayList<T> arrayList;

    public ArrayData(){
        arrayList = new ArrayList<>();
    }

    public void add(T... items) {
        addToList(items, arrayList);
    }

    public void add(ArrayList<T> items) {
        addToList(items, arrayList);
    }

    public final void remove(T... items) {
        for (T item : items) {
            arrayList.remove(item);
        }
    }

    public void remove(ArrayList<T> items){
        arrayList.removeAll(items);
    }

    public ArrayList<T> get(){
        return arrayList;
    }

    public int length(){
        return arrayList.size();
    }


    // Private method

    private <T> void addToList(ArrayList<T> toAdd, ArrayList<T> beAdded) {
        if (toAdd == null || beAdded == null) return;

        for (T element : toAdd) {
            if (!beAdded.contains(element)) {
                beAdded.add(element);
            }
        }
    }

    private void addToList(T[] toAdd, ArrayList<T> beAdded) {
        if (toAdd == null || beAdded == null) return;

        for (T element : toAdd) {
            if (element == null) continue;
            if (!beAdded.contains(element)) {
                beAdded.add(element);
            }
        }
    }
}
