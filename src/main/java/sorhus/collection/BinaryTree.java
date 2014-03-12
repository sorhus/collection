package sorhus.collection;

/**
 * @author: anton.sorhus@gmail.com
 */
public class BinaryTree<T> {

    private BinaryTree<T> left, right;
    private T data;

    public BinaryTree(T data) {
        this.data = data;
    }

    public void setLeft(BinaryTree<T> left) {
        this.left = left;
    }

    public void setRight(BinaryTree<T> right) {
        this.right = right;
    }

    public void print() {
        left.print();
        System.out.println(data.toString());
        right.print();
    }
}
