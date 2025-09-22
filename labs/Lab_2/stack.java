/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cosc2p03_lab2;

import java.util.EmptyStackException;
import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author gx23gq
 */
public class stack<A> {

    List<A> list = new LinkedList<>();

    public void push(A element) {
        list.add(element);
    }

    public A pop() throws EmptyStackException {
        try {
            return (A) list.remove(list.size() - 1);
        } catch (EmptyStackException e) {
            System.out.println("stack empty");
        }
        return null;
    }

    public A top() throws EmptyStackException{
        try {
            return (A) list.get(list.size() - 1);
        } catch (EmptyStackException e) {
            System.out.println("stack empty");
        }
        return null;
    }

    public boolean isEmpty() {
        if (list.size() <= 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return list.size();
    }

}
