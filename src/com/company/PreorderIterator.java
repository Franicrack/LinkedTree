package com.company;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class PreorderIterator<E> implements Iterator<Position<E>> {
    private Deque<Position<E>> nodeQueue;
    private Tree<E> tree;
    private Predicate<Position<E>>predicate;
    public PreorderIterator(Tree<E> tree) {

        this.tree=tree;
        this.predicate=null;
        this.nodeQueue= new LinkedList<>();
        if (this.tree.root()==null)
            this.nodeQueue.add(this.tree.root());
    }

    public PreorderIterator(Tree<E> tree, Position<E> start) {
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.predicate = null;
        this.nodeQueue.add(start);
    }



    public PreorderIterator(Tree<E> tree, Position<E> start, Predicate<Position<E>> predicate) {
        this.tree=tree;
        this.nodeQueue= new LinkedList<>();
        this.predicate = predicate;
        if(predicate.test(start))
            this.nodeQueue.add(start);
    }

    @Override
    public boolean hasNext() {
        return (!nodeQueue.isEmpty());

    }

    @Override
    public Position<E>next(){
        Position<E> nodeRet;
        if (this.predicate!=null)
            nodeRet=nextWith();
        else
            nodeRet=nextWithout();
        return nodeRet;
    }

    public Position<E> nextWith() {
        Position<E>aux=this.nodeQueue.remove();
        Deque<Position<E>> auxDeque = new LinkedList<>();
        for (Position<E> node : this.tree.children(aux)) {
            auxDeque.addFirst(node);
        }
        for (Position<E>nodeAux:auxDeque)
            this.nodeQueue.addFirst(nodeAux);
        if (predicate.test(aux))
            aux = aux;
        else
            aux = next();
        return  aux;
    }

    public Position<E> nextWithout(){
        Position<E>aux=this.nodeQueue.remove();
        Deque<Position<E>> auxDeque = new LinkedList<>();
        for (Position<E> node : this.tree.children(aux)) {
            auxDeque.addFirst(node);
        }
        for (Position<E>nodeAux:auxDeque)
            this.nodeQueue.addFirst(nodeAux);
        return aux;
    }
}

