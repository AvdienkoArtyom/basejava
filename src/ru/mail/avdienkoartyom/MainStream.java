package ru.mail.avdienkoartyom;

import java.util.*;
import java.util.stream.Collectors;


public class MainStream {
    public static void main(String[] args) {
        int[] ints = {1, 2, 3, 8, 5, 8, 2, 2, 4, 5};
        List<Integer> list1 = Arrays.asList(1, 2, 3, 8, 5, 8, 2, 2, 4, 5, 1);
        List<Integer> list2 = Arrays.asList(1, 2, 3, 8, 5, 8, 2, 2, 4, 5);
        System.out.println("Выбираем уникальные числа и возвращаем минимально возможное число, составленное из этих уникальных цифр");
        System.out.println(minValue(ints));
        System.out.println("Удаляем все нечетные");
        oddOrEven(list1).stream().forEach(System.out::print);
        System.out.println();
        System.out.println("Удаляем все четные");
        oddOrEven(list2).stream().forEach(System.out::print);
    }

    private static int minValue(int[] values) {
        OptionalInt optionalInt = Arrays.stream(values).distinct().sorted().reduce((left, right) -> left*10 + right);
        return optionalInt.getAsInt();
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        long optionalInt = integers.stream().mapToInt((s) -> s).sum();
        return integers.stream().filter(i -> (optionalInt % 2 != i % 2)).collect(Collectors.toList());
    }
}
