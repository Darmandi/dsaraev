package ru.job4j.tracker.trackersingleton;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;
import java.util.List;

/**
 * Singleton при помощи приватного статичного финального класса и приватного статичного финального поля в нем
 * @author Дмитрий Сараев (guitarpro4@mail.ru)
 * @version 1
 */
public class TrackerSingleStaticFinalClass {

    private TrackerSingleStaticFinalClass() {
    }

    public static TrackerSingleStaticFinalClass getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingleStaticFinalClass INSTANCE = new TrackerSingleStaticFinalClass();
    }

    //Используем композицию
    Tracker tracker = new Tracker();

    public Item add(Item item) {
        return this.tracker.add(item);
    }

    public boolean replace(String id, Item item) {
        return this.tracker.replace(id, item);
    }

    public boolean delete(String id) {
        return this.tracker.delete(id);
    }

    public Item findByID(String id) {
        return this.tracker.findByID(id);
    }

    public List<Item> getAll() {
        return this.tracker.getAll();
    }

    public List<Item> findByName(String name) {
        return this.tracker.findByName(name);
    }
}
