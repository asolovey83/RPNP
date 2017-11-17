package PolishNotation;

import java.util.*;

/**
 * Created by asolo on 11/9/2017.
 */
public class PolishNotation {


    public static void main(String[] args) {

        double result;

        System.out.println("Enter a polish notation string:");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        try {
            result = parse(str);
            System.out.println(result);
        } catch (RPNParserException e)
        {
            e.printStackTrace();
        } catch (ArithmeticException e)
        {
            e.printStackTrace();
        }
        finally {
            System.out.println("Выход из программы");
        }

    }

    public static double parse(String inputString) {

        Deque<Double> deque = new ArrayDeque<>();
        System.out.println(inputString);
        System.out.println();
        LinkedList<String> items = new LinkedList<String>(Arrays.asList(inputString.split(" ")));

        for (int i = 0; i < items.size(); i ++)
        {
            System.out.println(items.get(i));
        }

        for (int i = 0; i < items.size(); i++)
        {
            if (isNumeric(items.get(i)) && i != items.size() - 1)
            {
                double number = Double.parseDouble(items.get(i));
                deque.add(number);
            } else if (items.get(i).equals("+"))
            {
                    double second = deque.pollLast();
                    double first = deque.pollLast();
                    deque.push(first + second);

            } else if (items.get(i).equals("-"))
            {
                    double second = deque.pollLast();
                    double first = deque.pollLast();
                    deque.push(first - second);

            } else if (items.get(i).equals("*"))
            {
                    double second = deque.pollLast();
                    double first = deque.pollLast();
                    deque.push(first*second);

            } else if (items.get(i).equals("/"))
            {
                    double second = deque.pollLast();
                    double first = deque.pollLast();

                    if (second == 0)
                    {
                        throw new ArithmeticException();
                    } else

                    deque.push(first / second);

            } else {
                throw new RPNParserException("Not correct string");
            }
        }

       return deque.pop();
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Double number = Double.parseDouble(str);
        } catch (NumberFormatException exp)
        {
            return false;
        }

        return true;
    }
}
