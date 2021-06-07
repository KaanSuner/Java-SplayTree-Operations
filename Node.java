/**
 * Node Class*
 * @author Kaan
 */

 public class Node {

    Node left, right, parent;
    int element;

    /**
     * Constructor *
     */
    public Node() {

        this(0, null, null, null);
    }

    /**
     * Constructor *
     */
    public Node(int E) {

        this(E, null, null, null);
    }

    /**
     * Constructor *
     */
    public Node(int E, Node L, Node R, Node PR) {

        this.left = L;
        this.right = R;
        this.parent = PR;
        this.element = E;
    }
}
