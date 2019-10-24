package com.company;

import com.sun.source.tree.Tree;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class BFSIterator<E> implements Iterator<Position<E>> {
    private Deque<Position<E>> nodeQueue;
    private Tree <E> tree;
    public BFSIterator(Tree <E> tree){
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.nodeQueue.add(this.tree.root());
    }

    public BFSIterator(Tree<E>tree;Position<E>p){
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.nodeQueue.add(p);
    }

    @Override
    public boolean hasNext() {
        return (!nodeQueue.isEmpty());
    }

    @Override
    public Position<E> next() {
        Position<E>aux=this.nodeQueue.pollFirst();
        for (Position<E>node:this.tree.children(aux)){
            this.nodeQueue.addLast(node);
        }
        return aux;
    }
}
