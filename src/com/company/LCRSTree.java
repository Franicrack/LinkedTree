package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LCRSTree<E> implements NAryTree<E> {
    private class LCRSNode<E> implements Position<E>{
        private E element;
        private LCRSNode<E> parent;
        private LCRSNode<E> left,right;
        private LCRSTree<E> myTree;

        public LCRSNode(LCRSTree<E>myTree,E element,LCRSNode<E>parent, LCRSNode<E>left, LCRSNode<E>right){
            this.myTree = myTree;
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public LCRSNode<E> getLeft() {
            return left;
        }

        public LCRSNode<E> getParent() {
            return parent;
        }

        public E getElement() {
            return element;
        }

        public LCRSNode<E> getRight() {
            return right;
        }

        public LCRSTree<E> getMyTree() {
            return myTree;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public void setLeft(LCRSNode<E> left) {
            this.left = left;
        }

        public void setMyTree(LCRSTree<E> myTree) {
            this.myTree = myTree;
        }

        public void setParent(LCRSNode<E> parent) {
            this.parent = parent;
        }

        public void setRight(LCRSNode<E> right) {
            this.right = right;
        }

    }

    private int size;
    private LCRSNode<E> root;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return this.root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        LCRSNode<E>node=checkPosition(v);
        Position<E>parentPos=node.getParent();
        if (parentPos==null)
            throw new RuntimeException("The node has no parent");
        return parentPos;
    }
    private LCRSNode<E> checkPosition(Position<E> p) throws IllegalStateException {
        if (p == null || !(p instanceof LCRSNode)) {
            throw new IllegalStateException("The position is invalid");
        }
        LCRSNode<E> aux = (LCRSNode<E>) p;
        if (aux.getMyTree() != this) {
            throw new IllegalStateException("The node is not from this tree");
        }
        return aux;
    }
    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        LCRSNode<E> node = checkPosition(v);
        List<Position<E>> children= new ArrayList<>();
        if (node.getLeft()!=null){
            children.add(node.left);
            LCRSNode<E>aux=node.left;
            while(aux.right!=null){
                children.add(aux.right);
                aux=aux.right;
            }
        }
        return children;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        LCRSNode<E> node= checkPosition(v);
        return node.getLeft()==null;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return (!isInternal(v));
    }

    @Override
    public boolean isRoot(Position<E> v) {
        return (parent(v)==null);
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if (!isEmpty()) {
            throw new IllegalStateException("Tree already has a root");
        }
        size = 1;
        root = new LCRSNode<E>(this,e,null,null,null);
        return root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public E replace(Position<E> p, E e) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void swapElements(Position<E> p1, Position<E> p2) {
        LCRSNode<E> node1 = checkPosition(p1);
        LCRSNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement();
        node2.setElement(p1.getElement());
        node1.setElement(temp);
    }

    @Override
    public Position<E> add(E element, Position<E> p) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void remove(Position<E> p) {
        throw new RuntimeException("Not yet implemented");
    }

    @Override
    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {
        throw new RuntimeException("Not yet implemented");
    }
}
