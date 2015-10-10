package com.example.selenium.smoketest.calculator;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Reverse Polish Notation (RPN) Calculator is an implementation of a stack
 * calculator.
 *
 * https://en.wikipedia.org/wiki/Reverse_Polish_notation
 *
 * Example: 1 2 + 4 x 5 + 3 -
 *
 * Evaluates as: (((((1+2))*4)+5)-3) = 14
 *
 * @author @techjedi
 * @author @nevenc
 *
 */
public class RpnCalculator {

    private final Deque<Number> stack = new LinkedList<Number>();
    private static final List<String> OPS = Arrays.asList("-", "+", "*", "/");

    public void push(Object arg) {
        if (OPS.contains(arg)) {
            Number y = stack.removeLast();
            Number x = stack.isEmpty() ? 0 : stack.removeLast();
            Double val = null;
            if (arg.equals("-")) {
                val = x.doubleValue() - y.doubleValue();
            } else if (arg.equals("+")) {
                val = x.doubleValue() + y.doubleValue();
            } else if (arg.equals("*")) {
                val = x.doubleValue() * y.doubleValue();
            } else if (arg.equals("/")) {
                val = x.doubleValue() / y.doubleValue();
            }
            push(val);
        } else {
            stack.add((Number) arg);
        }
    }

    public void PI() {
        push(Math.PI);
    }

    public Number value() {
        return stack.getLast();
    }

}
