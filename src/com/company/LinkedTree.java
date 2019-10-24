package com.company;

import com.sun.source.tree.Tree;
import com.sun.source.tree.TreeVisitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedTree <E> implements NAryTree<E> {


    private class LinkedTreeNode<E>implements Position<E>{
        private E element;
        private LinkedTreeNode<E>parent;
        private List<LinkedTreeNode<E>> children;
        private LinkedTree<E> myTree;

        public void setElement(E element) {
            this.element = element;
        }

        public E getElement() {
            return element;
        }

        public void setChildren(List<LinkedTreeNode<E>> children) {
            this.children = children;
        }

        public void setMyTree(LinkedTree<E> myTree) {
            this.myTree = myTree;
        }

        public void setParent(LinkedTreeNode<E> parent) {
            this.parent = parent;
        }

        public LinkedTreeNode<E> getParent(){
            return this.parent;
        }
        public List<LinkedTreeNode<E>> getChildren() {
            return children;
        }

        public LinkedTree<E> getMyTree() {
            return myTree;
        }

        public LinkedTreeNode(Position<E>p, E element, LinkedTree<E>myTree, List<LinkedTreeNode<E>>children){
            this.parent=(LinkedTreeNode)p;
            this.element=element;
            this.myTree=myTree;
            this.children=children;
        }
    }
    private LinkedTreeNode<E> root;
    private int size;
    public LinkedTree(){
        this.size=0;
        this.root=null;
    }

    public int getSize() {
        return size;
    }

    public LinkedTreeNode<E> getRoot() {
        return root;
    }

    public void setRoot(LinkedTreeNode<E> root) {
        this.root = root;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isEmpty (){
        return this.size==0;
    }

    public boolean isInternal(Position<E>p){
        return !isLeaf(p);
    }

    public boolean isLeaf(Position<E>p){
        LinkedTreeNode<E>node=checkPosition(p);
        return (node.getChildren()==null)||(node.getChildren().isEmpty());
    }

    public boolean isRoot(Position<E>p){
        LinkedTreeNode<E>node=checkPosition(p);
        return (node==this.root);
    }

    public Position<E> root() throws RuntimeException{
        if(root==null)
            throw new RuntimeException("The tree is Empty");
        return root;
    }

    public Position<E> parent(Position<E>p) throws RuntimeException{
        LinkedTreeNode<E>node=checkPosition(p);
        Position<E>parent=node.getParent();
        if (parent==null)
            throw new RuntimeException("No tiene padres");
        return parent;
    }

    public Iterable<? extends Position<E>> children (Position<E>p){
        LinkedTreeNode<E>node=checkPosition(p);
        return node.getChildren();
    }

    public E replace(Position<E>p,E elem){
        LinkedTreeNode<E>node=checkPosition(p);
        E temp=p.getElement;
        node.setElement(elem);
        return temp;
    }

    public Position<E> addRoot (E elem) throws RuntimeException{
        if (!isEmpty())
            throw new RuntimeException("The tree has already a root");
        size=1;
        root= new LinkedTreeNode<E>(null,elem,this, new ArrayList<>());
        return root;
    }

    public void swapElements(Position<E>p1,Position<E>p2){
        LinkedTreeNode<E> node1 = checkPosition(p1);
        LinkedTreeNode<E> node2 = checkPosition(p2);
        E temp = p2.getElement;
        node2.setElement(p1.getElement);
        node1.setElement(temp);
    }

    private LinkedTreeNode<E> checkPosition(Position<E> p) throws RuntimeException {
        if (p == null || !(p instanceof LinkedTreeNode)) {
            throw new RuntimeException("The position is invalid");
        }
        LinkedTreeNode<E> aux = (LinkedTreeNode<E>) p;
        if (aux.getMyTree() != this) {
            throw new RuntimeException("The node is not from this tree");
        }
        return aux;
    }

    public Position<E> add(E element, Position<E> p) {
        LinkedTreeNode<E> parent = checkPosition(p);
        LinkedTreeNode<E> newNode = new LinkedTreeNode<E>(parent,element,this,new ArrayList<>());
        List<LinkedTreeNode<E>> l = parent.getChildren();
        l.add(newNode);
        size++;
        return newNode;
    }

    public Iterator<Position<E>> iterator() {
        Iterator<Position<E>> it = new BFSIterator<>(this);
        return it;
    }

    public void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException {

    }
}
