package com.owl.algorithm.utils;

public class AlgorithmUtils {
    /**
     * 计算Fibonacci数第n项,递归实现
     * fib(-1)=-`1,fib(0)
     */
    public long fib1(int n) {
        if (n < 0) return -1;
        else if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        else return fib1(n - 1) + fib1(n - 2);
    }

    /**
     * 非递归
     */
    public long fib2(int n) {
        if (n < 0) return -1;
        else if (n == 0) return 0;
        else if (n == 1 || n == 2) return 1;
        else {
            int a = 0, b = 1, c = 1;
            for (int i = 3; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return c;
        }
    }
}
