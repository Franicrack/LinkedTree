package com.company;

public interface NAryTree<E> extends Tree<E> {
    E replace(Position<E> p, E e);
    void swapElements(Position<E> p1, Position<E> p2);
    Position<E> add(E element, Position<E> p);
    void remove(Position<E> p);
    void moveSubtree(Position<E> pOrig, Position<E> pDest) throws RuntimeException;
}
