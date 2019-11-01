package com.company;



import java.util.ArrayList;

import java.util.Iterator;

import java.util.LinkedList;
import java.util.List;





public class ArrayBinaryTree<E> implements BinaryTree<E> {

    private class ArrayBinaryNode<E> implements Position<E>{
        private E element;
        private Integer pos,left,right,parent;


        public ArrayBinaryNode(E element,Integer pos,Integer left,Integer right,Integer parent){
            this.element=element;
            this.pos=pos;
            this.left=left;
            this.right=right;
            this.parent=parent;
        }



        public void setRight(Integer right) {
            this.right = right;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }

        public void setPos(Integer pos) {
            this.pos = pos;
        }

        public void setLeft(Integer left) {
            this.left = left;
        }

        public void setElement(E element) {
            this.element = element;

        }

        @Override
        public E getElement() {
            return element;
        }

        public Integer getLeft() {
            return left;
        }

        public Integer getParent() {
            return parent;
        }

        public Integer getPos() {
            return pos;
        }

        public Integer getRight() {
            return right;
        }

    }

    private int size;
    private ArrayBinaryNode<E> root;
    private ArrayList<ArrayBinaryNode<E>>nodeArray;


    @Override
    public Position<E> left(Position<E> v) throws RuntimeException {
        ArrayBinaryNode<E>node= checkPosition(v);
        Integer i=node.getLeft();
        if (i==null)
            throw new RuntimeException("No left");
        return this.nodeArray.get(i);
    }

    @Override
    public Position<E> right(Position<E> v) throws RuntimeException {
        ArrayBinaryNode<E>node= checkPosition(v);
        Integer i=node.getRight();
        if(i==null)
            throw new RuntimeException("No right");
        return this.nodeArray.get(i);
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
        Integer parentPos = node.getParent();
        ArrayBinaryNode<E>parentNode=this.nodeArray.get(parentPos);
        if (parentNode != null) {
            Integer sibPos;
            Integer leftPos = parentNode.getLeft();
            if (this.nodeArray.get(leftPos) == node) {
                sibPos = parentNode.getRight();
            } else {
                sibPos = parentNode.getLeft();
            }
            if (sibPos != null) {
                ArrayBinaryNode<E>sibNode=this.nodeArray.get(sibPos);
                return sibNode;
            }
        }
        throw new RuntimeException("No sibling");
    }

    @Override
    public Position<E> insertLeft(Position<E> p, E e) throws RuntimeException {
        ArrayBinaryNode<E> node = checkPosition(p);
        int leftPos = node.getLeft();
        ArrayBinaryNode<E>leftNode=this.nodeArray.get(leftPos);
        if (leftNode != null) {
            throw new RuntimeException("Node already has a left child");
        }
        ArrayBinaryNode<E> newNode = new ArrayBinaryNode<>(e,leftPos, null, null, node.pos);
        node.setLeft(newNode.pos);
        this.size++;
        return newNode;
    }

    @Override
    public Position<E> insertRight(Position<E> p, E e) throws RuntimeException {
        ArrayBinaryNode<E> node = checkPosition(p);
        int rightPos = (node.pos*2)+1;
        ArrayBinaryNode<E>rightNode=this.nodeArray.get(rightPos);
        if (rightNode != null) {
            throw new RuntimeException("Node already has a right child");
        }
        ArrayBinaryNode<E> newNode = new ArrayBinaryNode<>(e,rightPos, null, null, node.pos);
        node.setRight(newNode.pos);
        this.size++;
        return newNode;
    }

    @Override
    public E remove(Position<E> p) throws RuntimeException {
        ArrayBinaryNode<E>node=checkPosition(p);
        Integer rightPos=node.getRight();
        Integer leftPos=node.getLeft();
        if((rightPos!=null)&&(leftPos!=null))
            throw new RuntimeException("Cannot remove node with two children");
        else {
            if (node == root) {
                if ((leftPos != null) && (rightPos == null)) {
                    ArrayBinaryNode<E> newRoot= this.nodeArray.get(leftPos);
                    newRoot.setParent(null);
                }
                else if((leftPos==null)&& (rightPos!=null)){
                    ArrayBinaryNode<E> newRoot=this.nodeArray.get(rightPos);
                    newRoot.setParent(null);
                }
            }
            else {
                Integer parent= node.getParent();
                ArrayBinaryNode<E>parentNode=this.nodeArray.get(parent);
                if ((leftPos != null) && (rightPos == null)) {
                    if (node.pos==parentNode.getLeft()){
                        parentNode.setLeft(leftPos);
                    }
                    else{
                        parentNode.setRight(leftPos);
                    }
                    ArrayBinaryNode<E>leftNode=this.nodeArray.get(leftPos);
                    leftNode.setParent(parent);
                }
                else if((leftPos==null)&& (rightPos!=null)){
                    if (node.pos==parentNode.getLeft()){
                        parentNode.setLeft(rightPos);
                    }
                    else{
                        parentNode.setRight(rightPos);
                    }
                    ArrayBinaryNode<E>rightNode=this.nodeArray.get(rightPos);
                    rightNode.setParent(parent);
                }
             this.size--;
            }
            return p.getElement();
        }
    }



    @Override
    public void swap(Position<E> p1, Position<E> p2) {
        ArrayBinaryNode<E>node1=checkPosition(p1);
        ArrayBinaryNode<E>node2=checkPosition(p2);
        ArrayBinaryNode<E> copynode1= new ArrayBinaryNode<>(node1.element,node1.pos,node1.left,node1.right,node1.parent);
        node1.setPos(node2.pos);
        node1.setLeft(node2.left);
        node1.setRight(node2.right);
        node1.setParent(node2.parent);
        node2.setPos(copynode1.pos);
        node2.setLeft(copynode1.left);
        node2.setRight(copynode1.right);
        node2.setParent(copynode1.parent);
        if (node1.parent != null) {
            ArrayBinaryNode<E> node1Parent=this.nodeArray.get(node1.parent);
            if (node1Parent == node2) {
                node1Parent.left = node1.pos;
            } else {
                node1Parent.right=node1.pos;
            }
        } else {
            this.root = node1;
        }
        if (node2.parent != null) {
            ArrayBinaryNode<E>node2Parent=this.nodeArray.get(node2.parent);
            if (node2Parent == node1) {
                node2Parent.left = node2.pos;
            } else {
                node2Parent.right = node2.pos;
            }
        } else {
            root = node2;
        }
    }

    @Override
    public BinaryTree<E> subTree(Position<E> v) {
        ArrayBinaryNode<E>newRoot=checkPosition(v);
        if(newRoot==this.root)
            this.root=null;
        else{
            ArrayBinaryNode<E>parent=this.nodeArray.get(newRoot.parent);
            if(parent.left.equals(newRoot.pos))
                parent.setLeft(null);
            else
                parent.setRight(null);
        }
        newRoot.parent=null;
        ArrayBinaryTree<E>tree=new ArrayBinaryTree<>();
        tree.root=newRoot;
        return tree;
    }

    @Override
    public void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        ArrayBinaryNode<E>node=checkPosition(p);
        if (this==tree)
            throw new RuntimeException("Cannot attach a tree over himself");
        if(this.hasLeft(p))
            throw new RuntimeException("Node already has a left child");
        if ((tree!=null)&&(!tree.isEmpty())){
            ArrayBinaryNode<E>rootTreeAttached=checkPosition(tree.root());
            Integer leftPos=node.getPos()*2;
            rootTreeAttached.setPos(leftPos);
            node.setLeft(rootTreeAttached.getPos());
            rootTreeAttached.setParent(node.getPos());
            reposition(rootTreeAttached);
        }
    }
    private void reposition(ArrayBinaryNode<E>node){
        List<ArrayBinaryNode<E>>childList=new LinkedList<>();
        Integer leftChildPos=node.getLeft();
        ArrayBinaryNode<E>leftChild=this.nodeArray.get(leftChildPos);
        if(leftChild!=null){
            childList.add(leftChild);
            leftChild.setPos(node.pos*2);
            node.setLeft(leftChild.pos);
        }
        Integer rightChildPos=node.getRight();
        ArrayBinaryNode<E>rightChild=this.nodeArray.get(rightChildPos);
        if (rightChild!=null) {
            childList.add(rightChild);
            rightChild.setPos((node.pos*2)+1);
            node.setRight(rightChild.pos);
        }
        for(ArrayBinaryNode<E>aux:childList){
            Integer lc= aux.getLeft();
            ArrayBinaryNode<E>lcn=this.nodeArray.get(lc);
            if (lcn!=null){
                childList.add(lcn);
                lcn.setPos(aux.pos*2);
                aux.setLeft(lcn.pos);
            }
            Integer rc=aux.getRight();
            ArrayBinaryNode<E>rcn=this.nodeArray.get(rc);
            if (rcn!=null){
                childList.add(rcn);
                rcn.setPos((aux.pos*2)+1);
                aux.setRight(rcn.pos);
            }
        }
    }

    @Override
    public void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException {
        ArrayBinaryNode<E>node=checkPosition(p);
        if (this==tree)
            throw new RuntimeException("Cannot attach a tree over himself");
        if(this.hasRight(p))
            throw new RuntimeException("Node already has a left child");
        if ((tree!=null)&&(!tree.isEmpty())){
            ArrayBinaryNode<E>rootTreeAttached=checkPosition(tree.root());
            Integer rightPos=node.getPos()*2;
            rootTreeAttached.setPos(rightPos);
            node.setRight(rootTreeAttached.getPos());
            rootTreeAttached.setParent(node.getPos());
            reposition(rootTreeAttached);
        }
    }

    @Override
    public boolean isComplete() {
        if (nodeArray.isEmpty())
            return false;
        if (isLeaf(this.root))
            return true;
        boolean complete=true;
        InorderBinaryTreeIterator<E>it= new InorderBinaryTreeIterator<>(this);
        while ((it.hasNext())&&(complete==true)){
            Position<E>next=it.next();
            ArrayBinaryNode<E>nextNode=checkPosition(next);
            if ((isInternal(nextNode)&&(nextNode.left!=null)&&(nextNode.right!=null)))
                complete=true;
            else
                complete=false;
        }
        return complete;
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
        Integer parentPos= node.getParent();
        ArrayBinaryNode<E>parentNode= this.nodeArray.get(parentPos);
        return parentNode;
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
        this.root = new ArrayBinaryNode<>(e, 1, null, null,null);
        this.size=1;
        return this.root;
    }

    @Override
    public Iterator<Position<E>> iterator() {
        return new InorderBinaryTreeIterator<>(this);
    }
}
