import java.util.ArrayList;

public class SaveInArrayListWalk<V> implements IWalk<V> {

    private ArrayList<V> listado;

    public SaveInArrayListWalk() {
        listado = new ArrayList<V>();
    }

    @Override
    public void doWalk(V actualValue) {
        listado.add(actualValue);
    }

    public ArrayList<V> getListado() {
        return listado;
    }
}