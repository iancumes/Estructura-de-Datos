interface IHeap<P, V> {
    void Insert(P priority, V value);
    V remove();
    boolean isEmpty();
    int count();
    V get();
}
