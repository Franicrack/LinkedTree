package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedTree<E> implements NAryTree<E> {
    private class TreeNode<T> implements Position<T> {
        private T element; // The element stored in the position
        private TreeNode<T> parent; // The parent of the node
        private List<TreeNode<T>> children; // The children of the node
        private LinkedTree<T> myTree; // A reference to the tree where the node belongs

        public TreeNode(LinkedTree<T> t, T e, TreeNode<T> p, List<TreeNode<T>> c) {
            this.element = e;
            this.parent = p;
            this.children = c;
            this.myTree = t;
        }
        public T getElement() {
            return element;
        }

        public final void setElement(T o) {
            element = o;
        }

        public List<TreeNode<T>> getChildren() {
            return children;
        }

        public final void setChildren(List<TreeNode<T>> c) {
            children = c;
        }

        public TreeNode<T> getParent() {
            return parent;
        }

        public final void setParent(TreeNode<T> v) {
            parent = v;
        }

        public LinkedTree<T> getMyTree() {
            return myTree;
        }

        public void setMyTree(LinkedTree<T> myTree) {
            this.myTree = myTree;
        }
    }

    private TreeNode<E> root; // The root of the tree
    private int size; // The number of nodes in the tree

    public LinkedTree() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isInternal(Position<E> v) {
        return !isLeaf(v);
    }

    public boolean isLeaf(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return (node.getChildren() == null) || (node.getChildren().isEmpty());
    }

    public boolean isRoot(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return (node == this.root());
    }

    public Position<E> root() throws RuntimeException {
        if (root == null) {
            throw new RuntimeException("The tree is empty");
        }
        return root;
    }

    public Position<E> parent(Position<E> p) throws RuntimeException {
        TreeNode<E> node = checkPosition(p);
        Position<E> parentPos = node.getParent();
        if (parentPos == null) {
            throw new RuntimeException("The node has not parent");
        }
        return parentPos;
    }

    public Iterable<? extends Position<E>> children(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        return node.getChildren();
    }

    public E replace(Position<E> p, E e) {
        TreeNode<E> node = checkPosition(p);
        E temp = p.getElement();
        node.setElement(e);
        return temp;
    }

    public Position<E> addRoot(E e) throws RuntimeException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = new TreeNode<E>(this, e, null, new ArrayList<>());
        return root;
    }



    public void swapElements(Position<E> p1, Position<E> p2) {
        TreeNode<E> node1 = checkPosition(p1);
        TreeNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement();
        node2.setElement(p1.getElement());
        node1.setElement(temp);
    }

    private TreeNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof TreeNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        TreeNode<E> aux = (TreeNode<E>) p;
        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }

    public Position<E> add(E element, Position<E> p) {
        TreeNode<E> parent = checkPosition(p);
        TreeNode<E> newNode = new TreeNode<E>(this, element, parent, new ArrayList<>());
        List<TreeNode<E>> l = parent.getChildren();
        l.add(newNode);
        size++;
        return newNode;
    }

    public void remove(Position<E> p) {
        TreeNode<E> node = checkPosition(p);
        if (node.getParent() != null) {
            Iterator<Position<E>> it = new BFSIterator<E>(this, p);
            int cont = 0;
            while (it.hasNext()) {
                TreeNode<E> next = checkPosition(it.next());
                next.setMyTree(null);
                cont++;
            }
            size = size - cont;
            TreeNode<E> parent = node.getParent();
            parent.getChildren().remove(node);
        } else {
            this.root = null;
            this.size = 0;
        }
        node.setMyTree(null);
    }

    public Iterator<Position<E>> iterator() {
        return new BFSIterator<>(this);
    }

    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        TreeNode<E>node= checkPosition(pOrig);
        List<Position<E>> aux= new ArrayList<>();
        TreeNode<E> nodeDest= checkPosition(pDest);
        if (node==this.root)
            throw new RuntimeException("Cant move the root");
        if (pOrig==pDest)
            throw new RuntimeException("pOrig = pDest");
        BFSIterator<E> it= new BFSIterator(this,pOrig);
        while(it.hasNext()){
            aux.add(it.next());
        }
        int i;
        boolean equals;
        i=0;
        equals= false;
        while ((i<=aux.size())&& (equals==false)){
            TreeNode<E>aux2= checkPosition(aux.get(i));
            if (nodeDest==aux2)
                equals=true;
        }
        if (equals==true)
            throw new RuntimeException("Dest node is inside of pOrig subtree");
        TreeNode<E> parent= node.getParent();
        node.setParent(nodeDest);
        nodeDest.children.add(node);
        parent.children.remove(node);
    }
}
