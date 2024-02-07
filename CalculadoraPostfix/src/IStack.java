public interface IStack<T>{

    /**
     * Devuelve la cantidad de elementos
     * @return un entero que devuelve la cantidad de elementos
     */
    int count();

    boolean isEmpty();

    void push(T value);

    T pop();

    T peek();

}
