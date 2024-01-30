import java.util.Vector;

public class StackWithVector<T> implements IStack<String> {

    private Vector<String> internalVector;

    public StackWithVector() {
        internalVector = new Vector<>();
    }

    @Override
    public int count() {
        return internalVector.size();
    }

    @Override
    public boolean isEmpty() {
        return internalVector.isEmpty();
    }

    @Override
    public void push(String value) {
        internalVector.add(value);
    }

    @Override
    public String pop() {
        String tempValue = null;
        if (!internalVector.isEmpty()) {
            tempValue = internalVector.remove(internalVector.size() - 1);
        }
        return tempValue;
    }

    @Override
    public String peek() {
        String tempValue = null;
        if (!internalVector.isEmpty()) {
            tempValue = internalVector.get(internalVector.size() - 1);
        }
        return tempValue;
    }
}
