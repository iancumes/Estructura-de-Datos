public interface ITree<K, V> {

    void insert(K key, V value);

    V find(K keyToFind);

    int count();

    boolean isEmpty();

    V remove(K key);

    void InOrderWalk(IWalk<V> walk);

    void PreOrderWalk(IWalk<V> walk);

    void PostOrderWalk(IWalk<V> walk);
}