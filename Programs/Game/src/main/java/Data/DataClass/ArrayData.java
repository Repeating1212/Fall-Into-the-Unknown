package Data.DataClass;

import java.util.ArrayList;
import java.util.function.Predicate;

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

    public boolean isEmpty(){
        return arrayList.isEmpty();
    }

    public void clear(){
        arrayList.clear();
    }

    public boolean removeIf(Predicate<T> filter) {
        return arrayList.removeIf(filter);
    }

    public ArrayData<T> copyOf(){
        ArrayData<T> returnArray = new ArrayData<>();
        returnArray.add((ArrayList<T>) arrayList.clone());
        return returnArray;
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
