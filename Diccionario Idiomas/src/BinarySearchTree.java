import java.util.Comparator;

public class BinarySearchTree<K, V> implements ITree<K, V> {

    private int count;
    private TreeNode<K, V> root;
    private Comparator<K> keyComparator;

    public BinarySearchTree(Comparator<K> keyComparator) {
        this.root = null;
        this.count = 0;
        this.keyComparator = keyComparator;
    }

    @Override
    public void insert(K key, V value) {
        TreeNode<K, V> newNode = new TreeNode<>(key, value);
        if (isEmpty()) { // Insert in the root
            this.root = newNode;
            this.count++;
        } else { // Insert in a leaf
            internalInsert(this.root, newNode);
        }
    }

    private void internalInsert(TreeNode<K, V> parent, TreeNode<K, V> node) {
        if (parent != null) {
            int result = this.keyComparator.compare(node.getKey(), parent.getKey());
            if (result > 0) {
                if (parent.getRight() == null) {
                    parent.setRight(node);
                    node.setParent(parent);
                    this.count++;
                } else {
                    internalInsert(parent.getRight(), node);
                }
            } else if (result < 0) {
                if (parent.getLeft() == null) {
                    parent.setLeft(node);
                    node.setParent(parent);
                    this.count++;
                } else {
                    internalInsert(parent.getLeft(), node);
                }
            }
        }
    }

    @Override
    public V find(K keyToFind) {
        if (!isEmpty()) {
            return internalSearch(root, keyToFind);
        } else {
            return null;
        }
    }

    private V internalSearch(TreeNode<K, V> actualNode, K keyToFind) {
        if (actualNode != null) {
            int result = this.keyComparator.compare(keyToFind, actualNode.getKey());
            if (result == 0) {
                return actualNode.getValue();
            } else if (result > 0) {
                return internalSearch(actualNode.getRight(), keyToFind);
            } else if (result < 0) {
                return internalSearch(actualNode.getLeft(), keyToFind);
            }
        }
        return null;
    }

    @Override
    public int count() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public V remove(K key) {
        if (!isEmpty()) {
            // Implementación de eliminación aquí
        }
        return null;
    }

    @Override
    public void InOrderWalk(IWalk<V> walk) {
        InternalInOrderWalk(root, walk);
    }

    private void InternalInOrderWalk(TreeNode<K, V> actualNode, IWalk<V> walk) {
        if (actualNode != null) {
            InternalInOrderWalk(actualNode.getLeft(), walk);
            walk.doWalk(actualNode.getValue());
            InternalInOrderWalk(actualNode.getRight(), walk);
        }
    }

    @Override
    public void PreOrderWalk(IWalk<V> walk) {
        InternalPreOrderWalk(root, walk);
    }

    private void InternalPreOrderWalk(TreeNode<K, V> actualNode, IWalk<V> walk) {
        if (actualNode != null) {
            walk.doWalk(actualNode.getValue());
            InternalPreOrderWalk(actualNode.getLeft(), walk);
            InternalPreOrderWalk(actualNode.getRight(), walk);
        }
    }

    @Override
    public void PostOrderWalk(IWalk<V> walk) {
        InternalPostOrderWalk(root, walk);
    }

    private void InternalPostOrderWalk(TreeNode<K, V> actualNode, IWalk<V> walk) {
        if (actualNode != null) {
            InternalPostOrderWalk(actualNode.getLeft(), walk);
            InternalPostOrderWalk(actualNode.getRight(), walk);
            walk.doWalk(actualNode.getValue());
        }
    }
}
