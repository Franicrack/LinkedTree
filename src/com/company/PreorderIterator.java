package com.company;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class PreorderIterator<E> implements Iterator<Position<E>> {
    private Deque<Position<E>> nodeQueue;
    private Tree<E> tree;

    public PreorderIterator(Tree<E> tree) {
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.nodeQueue.add(this.tree.root());
    }

    public PreorderIterator(Tree<E> tree, Position<E> start) {
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.nodeQueue.add(start);
    }

    public PreorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        if(predicate.test(start))
            this.nodeQueue.add(start);
    }

    @Override
    public boolean hasNext() {
        return (!nodeQueue.isEmpty());
    }

    @Override
    public Position<E> next() {
        Position<E>aux=this.nodeQueue.pollFirst();
        
        return aux;
    }
}
