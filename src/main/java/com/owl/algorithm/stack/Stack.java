package com.owl.algorithm.stack;

import com.owl.algorithm.vector.Vector;

/**
 * 通过继承向量，实现栈
 */
public class Stack<T extends Comparable<T>> extends Vector<T> {
    public Stack() {
        super();
    }

    public Stack(int _capacity) {
        super(_capacity);
    }

    public void push(T data) {
        this.insert(size(), data);
    }

    public T pop() {
        return remove(size() - 1);
    }

    public T top() {
        return get(size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            buffer.append("(").append(get(i)).append(")").append("-");
        }
        return buffer + ">";
    }
}
