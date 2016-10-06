package com.javarush.test.level36.lesson08.bonus01;

public class RedBlackTree {
    protected Node current; // текущий
    private Node parent;  // родитель
    private Node grand; // наибольший
    private Node great;
    private Node header; // минимальный

    private static final Node EMPTY = new Node(0);

    static {
        EMPTY.left = EMPTY;
        EMPTY.right = EMPTY;
    }

    public RedBlackTree() {
        header = new Node(Integer.MIN_VALUE);
        header.left = EMPTY;
        header.right = EMPTY;
///111111111111111111111
//        header = new RedBlackNode(negInf);
//        header.left = nullNode;
//        header.right = nullNode;
    }

    public boolean isEmpty() {
      //  return header.left == EMPTY;
          return header.right == EMPTY;
    }

    public void clear() {
        header.right = EMPTY;
    }

    public void insert(int item) {
        current = grand = parent = header;
        EMPTY.element = item;
        while (current.element != item) {
            great = grand;
            grand = parent;
            parent = current;
//            current = item > current.element ? current.right : current.left;
//
//            if (current.left.color == Color.RED && current.right.color == Color.BLACK) {
//                reorient(item);
//            }

                current = item < current.element ? current.left : current.right;
                // Check if two red children and fix if so
                if (current.left.color == Color.RED && current.right.color == Color.RED)
                    reorient(item);


        }

        if (current != EMPTY) {
            return;
        }

        current = new Node(item, EMPTY, EMPTY);

        if (item < parent.element) {
            parent.left = current;
        } else {
            parent.right = current;
        }

        reorient(item);

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//            current = parent = grand = header;
//            nullNode.element = item;
//            while (current.element != item)
//            {
//                great = grand;
//                grand = parent;
//                parent = current;
//                current = item < current.element ? current.left : current.right;
//                // Check if two red children and fix if so
//                if (current.left.color == RED && current.right.color == RED)
//                    handleReorient( item );
//            }
//            // Insertion fails if already present
//            if (current != nullNode)
//                return;
//            current = new RedBlackNode(item, nullNode, nullNode);
//            // Attach to parent
//            if (item < parent.element)
//                parent.left = current;
//            else
//                parent.right = current;
//            handleReorient( item );
    }

    protected void reorient(int item) {
//        current.color = Color.RED;
//        current.left.color = Color.BLACK;
//        current.right.color = Color.BLACK;
//
//        if (parent.color == Color.RED) {
//            //grand.color = Color.RED;
//
//            if (item < grand.element != item < parent.element) {
//                parent = rotate(item, grand);
//            }
//            current = rotate(item, great);
//            current.color = Color.BLACK;
//        }
//
//       // header.right.color = Color.BLACK;

        // Do the color flip
        current.color = Color.RED;
        current.left.color = Color.BLACK;
        current.right.color = Color.BLACK;

        if (parent.color == Color.RED)
        {
            // Have to rotate
            grand.color = Color.RED;
            if (item < grand.element != item < parent.element)
                parent = rotate( item, grand );  // Start dbl rotate
            current = rotate(item, great );
            current.color = Color.BLACK;
        }
        // Make root black
        header.right.color = Color.BLACK;
    }

    private Node rotate(int item, Node parent) {


        if (item < parent.element) {
            Node node = parent.left;
            Node resultNode = item < node.element ? rotateWithLeftNode(node) : rotateWithRightNode(node);
            parent.left = resultNode;
            return parent.left;
        } else {
            Node node = parent.right;
            Node resultNode = item < node.element ? rotateWithLeftNode(node) : rotateWithRightNode(node);
            parent.right = resultNode;
            return parent.right;
        }

//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        {
//            if(item < parent.element)
//                return parent.left = item < parent.left.element ? rotateWithLeftChild(parent.left) : rotateWithRightChild(parent.left) ;
//            else
//                return parent.right = item < parent.right.element ? rotateWithLeftChild(parent.right) : rotateWithRightChild(parent.right);
//        }
    }

    private Node rotateWithLeftNode(Node element) {
        Node left = element.left;
        element.left = left.right;
        left.right = element;
        return left;
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//        {
//            RedBlackNode k1 = k2.left;
//            k2.left = k1.right;
//            k1.right = k2;
//            return k1;
//        }
    }

    private Node rotateWithRightNode(Node element) {
        Node left = element.right;
        element.right = left.left;
        left.left = element;
        return left;

//        Node left = element.left;
//        element.left = left.right;
//        left.right = element;
//        return left;

//!!!!!!!!!!!!!!!!!!!!!!!
//        {
//            RedBlackNode k2 = k1.right;
//            k1.right = k2.left;
//            k2.left = k1;
//            return k2;
//        }
    }

    public static enum Color {
        BLACK,
        RED
    }

    public static class Node {
        private int element;
        private Node left;
        private Node right;
        private Color color;

        public Node(int element) {
            this(element, null, null);
        }

        public Node(int element, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.color = Color.BLACK;

        }

        public Color getColor()
        {
            return color;
        }

        public int getElement()
        {
            return element;
        }
    }
}
