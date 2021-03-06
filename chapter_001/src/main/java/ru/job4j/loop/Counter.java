package ru.job4j.loop;

/**
 * Цикл с if
 * @author Дмитрий Сараев (guitarpro4@mail.ru)
 * @version 1
 */
public class Counter {

    /**
     * Вычисляет сумму четных чисел в заданном диапазое
     * @param begin начало диапазона
     * @param end конец диапазона
     * @return сумма
     */
    public int sumOfEven(int begin, int end) {
        int temp = 0;
        for (int i = begin; i <= end; i++) {
            if (i % 2 == 0) {
                temp = temp + i;
            }
        }
        return temp;
    }
}
