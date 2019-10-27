package com.company;

    public interface Tree<E> extends Iterable<Position<E>> {
        int size();
        boolean isEmpty();
        Position<E> root() throws RuntimeException;
        Position<E> parent(Position<E> v) throws RuntimeException;
        Iterable<? extends Position<E>> children(Position<E> v);
        boolean isInternal(Position<E> v);
        boolean isLeaf(Position<E> v) throws RuntimeException;
        boolean isRoot(Position<E> v);
        Position<E> addRoot(E e) throws RuntimeException;
    }
