package studentbst;

public class StudentBinarySearchTree extends BinarySearchTree<Student> {
    public void updateLeafGPA(Node<Student> root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            root.info.gpa += 1;
            return;
        }
        updateLeafGPA(root.left);
        updateLeafGPA(root.right);
    }
}
