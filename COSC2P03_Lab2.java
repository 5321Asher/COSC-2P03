/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.cosc2p03_lab2;

import java.util.Scanner;
import com.mycompany.cosc2p03_lab2.stack;

/**
 *
 * @author Asher Virgona - 8032492 - gx23gq
 */
public class COSC2P03_Lab2 {

    public static void main(String[] args) {
        stack stack = new stack<Character>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("please input your string:");
        String input = scanner.nextLine();

        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] == '(' || inputArray[i] == '{' || inputArray[i] == '[') {
                stack.push(inputArray[i]);
            } else if (inputArray[i] == ')' || inputArray[i] == '}' || inputArray[i] == ']') {
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            System.out.println("true");
        } else {
            System.out.println("false");

        }
    }

}
