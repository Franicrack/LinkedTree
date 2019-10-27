package com.company;

public interface BinaryTree<E> extends Tree<E>, Iterable<Position<E>> {
    Position<E> left(Position<E> p) throws RuntimeException;
    Position<E> right(Position<E> p) throws RuntimeException;
    boolean hasLeft(Position<E> p);
    boolean hasRight(Position<E> p);
    E replace(Position<E> p, E e);
    Position<E> sibling(Position<E> p) throws RuntimeException;
    Position<E> insertLeft(Position<E> p, E e) throws RuntimeException;
    Position<E> insertRight(Position<E> p, E e) throws RuntimeException;
    E remove(Position<E> p) throws RuntimeException;
    void swap(Position<E> p1, Position<E> p2);
    BinaryTree<E> subTree(Position<E> v);
    void attachLeft(Position<E> p, BinaryTree<E> tree) throws RuntimeException;
    void attachRight(Position<E> p, BinaryTree<E> tree) throws RuntimeException;
    boolean isComplete();
}
