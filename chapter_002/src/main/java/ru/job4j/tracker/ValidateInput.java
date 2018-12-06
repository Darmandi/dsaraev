package ru.job4j.tracker;


/**
 * @author Dmitrii Saraev
 * @version 1
 */

public class ValidateInput implements Input {
    private final Input input;
    public ValidateInput(final Input input) {
        this.input = input;
    }
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }
    public int ask(String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Select key from menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Enter validate data again");
            }
        } while (invalid);
        return value;
    }
}