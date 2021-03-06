package ru.job4j.tracker;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * Класс для ввода пользователя через консоль
 * @author Дмитрий Сараев (guitarpro4@mail.ru)
 * @version 1
 */
public class StartUI {
    /**
     * Константы меню
     */
    private static final String ADD = "0";
    private static final String ALL = "1";
    private static final String EDIT = "2";
    private static final String DELETE = "3";
    private static final String FINDID = "4";
    private static final String FINDNAME = "5";
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;
    //Функцирнальный интерфейс
    private final Consumer<String> output;
    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     * @param output функция вывода данных.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, output);
        int[] range = new int[10];
        menu.fillActions();
        for (int i = 0; i < menu.getActionsLength(); i++) {
            range[i] = i;
        }
        do {
            menu.show();
            menu.select(input.ask("select:", range));
        } while (!"y".equals(this.input.ask("Exit?(y): ")));
    }
    /**public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню: ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (ALL.equals(answer)) {
                this.showItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FINDID.equals(answer)) {
                this.findItemID();
            } else if (FINDNAME.equals(answer)) {
                this.findItemName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        output.accept("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки:");
        String desc = this.input.ask("Введите описание заявки:");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        output.accept("Новая заявка с ID: " + item.getID());
    }

    /**
     * Метод реализует отображение всех заявок в хранилище.
     */
    private void showItems() {
        output.accept("------------ Все заявки --------------");
        List<Item> items = this.tracker.getAll();
        if (items.size() > 0) {
            for (Item item : items) {
                output.accept(item.toString());
            }
        } else {
            output.accept("Заявки не найдена");
        }
    }

    /**
     * Метод реализует изменение заявки.
     */
    private void editItem() {
        output.accept("------------ Изменение заявки --------------");
        String id = this.input.ask("Введите ID старой заявки:");
        String name = this.input.ask("Введите имя новой заявки:");
        String desc = this.input.ask("Введите описание новой заявки:");
        Item item = new Item(name, desc);
        boolean result = this.tracker.replace(id, item);
        if (result) {
            output.accept("Заявка отредактирована. ID заявки: " + item.getID());
        } else {
            output.accept("Заявка не найдена");
        }
    }



    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите ID заявки:");
        boolean result = this.tracker.delete(id);
        if (result) {
            output.accept("Заявка с ID " + id + " удалена");
        } else {
            output.accept("Заявка не найдена");
        }
    }

    /**
     * Метод реализует поиск заявки по ID.
     */
    private void findItemID() {
        output.accept("------------ Поиск заявки по ID--------------");
        String id = this.input.ask("Введите ID заявки:");
        Item itemTemp = this.tracker.findByID(id);
        if (itemTemp != null) {
            output.accept(itemTemp.toString());
        } else {
            output.accept("Заявка не найдена");
        }
    }

    /**
     * Метод реализует поиск заявки по имени.
     */
    private void findItemName() {
        output.accept("------------ Поиск заявки по имени--------------");
        String name = this.input.ask("Введите имя заявки:");
        List<Item> items = this.tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                output.accept(item.toString());
            }
        } else {
            output.accept("Заявки не найдена");
        }
    }

    private void showMenu() {
        output.accept(System.lineSeparator() + "Меню.");
        output.accept("0. Add new Item" + System.lineSeparator()
                + "1. Show all items" + System.lineSeparator()
                + "2. Edit item" + System.lineSeparator()
                + "3. Delete item" + System.lineSeparator()
                + "4. Find item by Id" + System.lineSeparator()
                + "5. Find items by name" + System.lineSeparator()
                + "6. Exit Program" + System.lineSeparator());
    }

    /**
     * Запускт программы
     * вывод на консоль с помощью ссылки на метод System.out::println
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
