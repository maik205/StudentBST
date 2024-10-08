package studentbst;

/**
 *
 * @author ASUS
 * @param <T>
 */
public class Node<T> {

    T info;
    Node<T> left, right, parent;

    Node(T x) {
        this.info = x;
    }

    Node(T x, Node<T> left, Node<T> right, Node<T> parent) {
        this.info = x;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return info.toString();
    }
}
