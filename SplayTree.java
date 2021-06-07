
import java.io.*;
import java.util.*;


/**
 *
 * @author Kaan Suner 
 * @version 1.8.0_111
 */
public class SplayTree{

    public Node root;

    /**
     * Constructor *
     */
    public SplayTree() {
        root = null;
    }

    /**
     * function to insert element
     */
    public void insert(int ele) {

        Node first = root;
        Node second = null;
        while (first != null) {

            second = first;
            if (ele > second.element) {
                first = first.right;
            } else {
                first = first.left;

            }
        }
        first = new Node();
        first.element = ele;
        first.parent = second;
        if (second == null) {
            root = first;
        } else if (ele > second.element) {
            second.right = first;
        } else {
            second.left = first;
        }
        Splay(first);
      
    }

    /**
     * rotate *
     */
    public void makeLeftChildParent(Node c, Node p) {

        if ((c == null) || (p == null) || (p.left != c) || (c.parent != p)) {
            throw new RuntimeException("WRONG");
        }

        if (p.parent != null) {

            if (p == p.parent.left) {
                p.parent.left = c;
            } else {
                p.parent.right = c;
            }
        }
        if (c.right != null) {
            c.right.parent = p;
        }

        c.parent = p.parent;
        p.parent = c;
        p.left = c.right;
        c.right = p;
    }

    /**
     * rotate *
     */
    public void makeRightChildParent(Node c, Node p) {

        if ((c == null) || (p == null) || (p.right != c) || (c.parent != p)) {
            throw new RuntimeException("WRONG");
        }
        if (p.parent != null) {
            if (p == p.parent.left) {
                p.parent.left = c;
            } else {
                p.parent.right = c;
            }
        }
        if (c.left != null) {
            c.left.parent = p;
        }
        c.parent = p.parent;
        p.parent = c;
        p.right = c.left;
        c.left = p;
    }

    /**
     * function splay *
     */
    public void Splay(Node x) {
        while (x.parent != null) {

            Node Parent = x.parent;
            Node GrandParent = Parent.parent;
            if (GrandParent == null) {
                if (x == Parent.left) {
                    makeLeftChildParent(x, Parent);
                } else {
                    makeRightChildParent(x, Parent);
                }
            } else {

                if (x == Parent.left) {

                    if (Parent == GrandParent.left) {

                        makeLeftChildParent(Parent, GrandParent);
                        makeLeftChildParent(x, Parent);
                    } else {

                        makeLeftChildParent(x, x.parent);
                        makeRightChildParent(x, x.parent);
                    }
                } else {

                    if (Parent == GrandParent.left) {

                        makeRightChildParent(x, x.parent);
                        makeLeftChildParent(x, x.parent);
                    } else {

                        makeRightChildParent(Parent, GrandParent);
                        makeRightChildParent(x, Parent);
                    }
                }
            }
        }
        root = x;
    }

    /**
     * function to remove element *
     */
    public void remove(int ele) {
        if (search(ele)) {
            Node node = findNode(ele);
            remove(node);
        }
    }

    /**
     * function to remove node *
     */
    public void remove(Node node) {
        this.Splay(node);

        SplayTree leftsub = new SplayTree();
        leftsub.root = this.root.left;
        if (leftsub.root != null) {
            leftsub.root.parent = null;
        }

        SplayTree rightSubtree = new SplayTree();
        rightSubtree.root = this.root.right;
        if (rightSubtree.root != null) {
            rightSubtree.root.parent = null;
        }

        if (leftsub.root != null) {
            Node m = leftsub.maximum(leftsub.root);
            leftsub.Splay(m);
            leftsub.root.right = rightSubtree.root;
            this.root = leftsub.root;
        } else {
            this.root = rightSubtree.root;
        }
    }

    public Node maximum(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    /**
     * Functions to search for an element *
     */
    public boolean search(int val) {

        return findNode(val) != null;
    }

    public Node findNode(int ele) {

        Node PrevNode = null;
        Node first = root;
        while (first != null) {

            PrevNode = first;
            if (ele > first.element) {
                first = first.right;
            } else if (ele < first.element) {
                first = first.left;
            } else if (ele == first.element) {
                Splay(first);
                return first;
            }

        }
        if (PrevNode != null) {

            Splay(PrevNode);
            return null;
        }
        return null;
    }
/**
 * function to find height
 * @param root root of tree
 * @return recursion
 */
    int height(Node root) {
        if (root == null) {
            return 0;
        } else {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);

            /* use the larger one */
            if (lheight > rheight) {
                return (lheight + 1);
            } else {
                return (rheight + 1);
            }
        }

    }
/**
 * print funtion to tree's last situation
 * @param printout 
 */
    public void print(PrintWriter printout) {

        if (root == null) {
            return;
        }

        Queue<Node> NodeQueue = new LinkedList<Node>();
        NodeQueue.add(root);
        boolean AllLeavesNull = false;

        while (NodeQueue.size() > 0 && !AllLeavesNull) {
            AllLeavesNull = true;
            Queue<Node> LeafQueue = new LinkedList<Node>();

            for (Node node : NodeQueue) {

                if (node == null) {
                    printout.print("- ");
                } else {
                    printout.print(node.element + " ");
                }

                if (node == null) {
                    LeafQueue.add(null);
                    LeafQueue.add(null);
                } else {
                    LeafQueue.add(node.left);
                    LeafQueue.add(node.right);
                    if (node.left != null || node.right != null) {
                        AllLeavesNull = false;
                    }
                }
            }
            NodeQueue = LeafQueue;
        }
    }

}
