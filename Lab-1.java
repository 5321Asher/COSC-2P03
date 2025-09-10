/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ashervirgona.mavenproject1;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Asher Virgona 8032492 gx23gq
 */
public class Mavenproject1 {

    static List globalList = new LinkedList();

    public static void main(String[] args) {
        //System.out.println(fibonacci(test));

        System.out.println(toBase(4, 7));
    }

    public static int fibonacci(int n) {
        int result;
        int fibOne = 0;
        int fibTwo = 1;
        int temp = 0;

        for (int i = 0; i < n; i++) {
            temp = fibOne + fibTwo;
            fibTwo = fibOne;
            fibOne = temp;
        }

        result = temp;
        return result;
    }

    public static List<Integer> toBase(int base, int n) {
        List list = new LinkedList();
        int mod = n % base;
        int divided = n / base;

        if (mod < base) {
            for (int i = 0; i < divided; i++) {
                list.add(0, 0);
            }
            toBase(base, divided);
            globalList.add(list);
        } else {
            list.add(0, mod);
        }

        return list;
    }

    //public static List<Integer> primeFactors(int n) {
    //}
}
