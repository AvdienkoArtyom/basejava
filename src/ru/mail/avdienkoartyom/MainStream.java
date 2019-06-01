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
        System.out.println("Удаляем все четные");
        oddOrEven(list1).stream().forEach(System.out::print);
        System.out.println();
        System.out.println("Удаляем все нечетные");
        oddOrEven(list2).stream().forEach(System.out::print);
    }

    private static int minValue(int[] values) {
        List<Integer> list = Arrays.stream(values).boxed().distinct().sorted().collect(Collectors.toList());
        int count = 0;
        for (int i = 0, j = list.size() - 1; i < list.size(); i++, j--) {
            count = count + (list.get(i) * (int) Math.pow(10, j));
        }
        return count;
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        long optionalInt = integers.stream().mapToInt((s) -> s).sum();
        List<Integer> list;
        if (optionalInt % 2 == 0) {
            list = integers.stream().filter(i -> (i % 2 == 0)).collect(Collectors.toList());
        } else {
            list = integers.stream().filter(i -> (i % 2 != 0)).collect(Collectors.toList());
        }
        return list;
    }
}
