import java.util.Vector;

public class StackWithVector<T> implements IStack<T> {

    private Vector<T> internalVector;

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
    public void push(T value) {
        internalVector.add(value);
    }

    @Override
    public T pop() {
        T tempValue = null;
        if (!internalVector.isEmpty()) {
            tempValue = internalVector.remove(internalVector.size() - 1);
        }
        return tempValue;
    }

    @Override
    public T peek() {
        T tempValue = null;
        if (!internalVector.isEmpty()) {
            tempValue = internalVector.get(internalVector.size() - 1);
        }
        return tempValue;
    }
}
