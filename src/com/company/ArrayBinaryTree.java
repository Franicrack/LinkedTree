package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ArrayBinaryTree<E> implements BinaryTree<E> {
    private class ArrayBinaryNode<E> implements Position<E>{
        private E element;
        private ArrayBinaryNode<E> left,right,parent;

        public ArrayBinaryNode(E element,ArrayBinaryNode left,ArrayBinaryNode right,ArrayBinaryNode parent){
            this.element=element;
            this.left=left;
            this.right=right;
            this.parent=parent;
        }

        public void setRight(ArrayBinaryNode<E> right) {
            this.right = right;
        }

        public void setParent(ArrayBinaryNode<E> parent) {
            this.parent = parent;
        }

        public void setLeft(ArrayBinaryNode<E> left) {
            this.left = left;
        }

        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        public ArrayBinaryNode<E> getLeft() {
            return left;
        }

        public ArrayBinaryNode<E> getParent() {
            return parent;
        }

        public ArrayBinaryNode<E> getRight() {
            return right;
        }
    }

    private int size;
    private ArrayBinaryNode<E> root;

    @Override
    public Position<E> left(Position<E> v) throws RuntimeException {
        ArrayBinaryNode<E>node= checkPosition(v);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> v) throws RuntimeException {
        ArrayBinaryNode<E>node= checkPosition(v);
        return node.getRight();
    }

    @Override
    public boolean hasLeft(Position<E> v) {
        ArrayBinaryNode<E>node= checkPosition(v);
        if (node.getLeft()!=null)
            return true;
        else
            return false;
    }

    @Override
    public boolean hasRight(Position<E> v) {
        ArrayBinaryNode<E>node=checkPosition(v);
        if(node.getRight()!=null)
            return true;
        else
            return false;
    }

    @Override
    public E replace(Position<E> p, E e) {
        ArrayBinaryNode<E>node=checkPosition(p);
        E temp=node.getElement();
        node.setElement(e);
        return temp;
    }

    @Override
    public Position<E> sibling(Position<E> p) throws RuntimeException {
        ArrayBinaryNode<E>node= checkPosition(p);
        ArrayBinaryNode<E> parentPos = node.getParent();
        if (parentPos != null) {
            ArrayBinaryNode<E> sibPos;
            ArrayBinaryNode<E> leftPos = parentPos.getLeft();
            if (leftPos == node) {
                sibPos = parentPos.getRight();
            } else {
                sibPos = parentPos.getLeft();
            }
            if (sibPos != null) {
                return sibPos;
            }
        }
        throw new RuntimeException("No sibling");
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        ArrayBinaryNode<E> node = checkPosition(p);
        Position<E> leftPos = node.getLeft();
        if (leftPos != null) {
            throw new RuntimeException("Node already has a left child");
        }
        ArrayBinaryNode<E> newNode = new ArrayBinaryNode<>(e, null, null, node);
        node.setLeft(newNode);
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        ArrayBinaryNode<E> node = checkPosition(p);
        Position<E> rightPos = node.getRight();
        if (rightPos != null) {
            throw new RuntimeException("Node already has a right child");
        }
        ArrayBinaryNode<E> newNode = new ArrayBinaryNode<>(e, null, null, node);
        node.setRight(newNode);
        return newNode;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        return null;
    }

    @Override
    public void swap(Position<E> p1, Position<E> p2) {
    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        return null;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
    }

    @Override
    public boolean isComplete() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public Position<E> root() throws RuntimeException {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> v) throws RuntimeException {
        ArrayBinaryNode<E> node = checkPosition(v);
        return node.getParent();
    }
    private ArrayBinaryNode<E> checkPosition(Position<E> p) {
        if (p == null || !(p instanceof ArrayBinaryNode)) {
            throw new RuntimeException("The position is invalid");
        }
        return (ArrayBinaryNode<E>) p;
    }
    @Override
    public Iterable<? extends Position<E>> children(Position<E> v) {
        List<Position<E>> children= new ArrayList<>();
        if(hasLeft(v))
            children.add(left(v));
        else if (hasRight(v))
            children.add(right(v));
        return children;
    }

    @Override
    public boolean isInternal(Position<E> v) {
        ArrayBinaryNode<E>node=checkPosition(v);
        if ((node.getLeft()!=null)&&(node.getRight()!=null)){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean isLeaf(Position<E> v) throws RuntimeException {
        return !isInternal(v);
    }

    @Override
    public boolean isRoot(Position<E> v) {
        ArrayBinaryNode<E>node = checkPosition(v);
        if (node.getParent()==null){
            return true;
        }
        else
            return false;
    }

    @Override
    public Position<E> addRoot(E e) throws RuntimeException {
        if (!isEmpty()) {
            throw new RuntimeException("Tree already has a root");
        }
        this.root = new ArrayBinaryNode<>(e, null, null, null);
        return this.root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return null;
    }
}
