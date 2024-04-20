package com.hakimen.wandrous.common.utils.data;

import java.util.ArrayList;
import java.util.List;

public class Node<T> implements Cloneable{
    T data;
    List<Node<T>> children;

    public Node(T data, List<Node<T>> children) {
        this.data = data;
        this.children = children;
    }

    public Node(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }


    public void addChild(Node<T> node){
        children.add(node);
    }

    public void removeChild(Node<T> node){
        children.remove(node);
    }

    public T getData() {
        return data;
    }

    public Node<T> setData(T data) {
        this.data = data;
        return this;
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public Node<T> setChildren(List<Node<T>> children) {
        this.children = children;
        return this;
    }

    public static void print(int depth, Node<?> node) {
        System.out.println("\t".repeat(depth) + node.data);
        node.children.forEach((e) -> print(depth + 1, e));
    }

    public Node<T> clone(){
        try {
            return (Node<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
