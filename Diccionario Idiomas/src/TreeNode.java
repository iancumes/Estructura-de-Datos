public class TreeNode<K, V> {

    private K key;
    private V value;
    private TreeNode<K, V> left;
    private TreeNode<K, V> right;
    private TreeNode<K, V> parent;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        parent = null;
        left = null;
        right = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public TreeNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K, V> left) {
        this.left = left;
    }

    public TreeNode<K, V> getRight() {
        return right;
    }

    public void setRight(TreeNode<K, V> right) {
        this.right = right;
    }

    public TreeNode<K, V> getParent() {
        return parent;
    }

    public void setParent(TreeNode<K, V> parent) {
        this.parent = parent;
    }

    
}