package ru.job4j.array;

/** Цикл и массив
 * @author Дмитрий Сараев (guitarpro4@mail.ru)
 * @version 1
 */
public class Square {
    /**
     * Создает массив и заполняет его квадратими чисел от 1 до bound
     * @param bound величина массива
     * @return массив
     */
    public int[] calculate(int bound) {
        int[] rsl = new int[bound];
        for (int index = 0; index < bound; index++) {
            rsl[index] = (int) (Math.pow(index + 1, 2));
        }
        return rsl;

    }
}
