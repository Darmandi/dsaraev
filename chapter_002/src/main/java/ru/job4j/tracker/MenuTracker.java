package ru.job4j.tracker;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Dmotrii Saraev
 * @version 1
 */


public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private List<UserAction> actions = new ArrayList<>();

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }
    public int getActionsLength() {
        return this.actions.size();
    }

    public void fillActions() {
        this.actions.add(new AddItem(0, "Add new Item"));
        this.actions.add(new ShowItems(1, "Show all items"));
        this.actions.add(new MenuTracker.EditItem(2, "Edit item"));
        this.actions.add(new MenuTracker.DeleteItem(3, "Delete item"));
        this.actions.add(new FindItemById(4, "Find item by ID"));
        this.actions.add(new FindItemsByName(5, "Find item by name"));
    }


    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }


    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {
        public AddItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("New Item with Id : " + item.getID());
        }
    }

    private class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ All items --------------");
            List<Item> items = tracker.getAll();
            if (items.size() > 0) {
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            } else {
                System.out.println("No items found");
            }
        }
    }

    public static class EditItem extends BaseAction {
        public EditItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Edit item --------------");
            String id = input.ask("Enter old item's ID:");
            String name = input.ask("Enter new item's ID:");
            String desc = input.ask("Enter description:");
            Item item = new Item(name, desc);
            boolean result = tracker.replace(id, item);
            if (result) {
                System.out.println("Item edited. Item's ID: " + item.getID());
            } else {
                System.out.println("No items found");
            }
        }
    }

    public static class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Delete item --------------");
            String id = input.ask("Enter item's ID:");
            boolean result = tracker.delete(id);
            if (result) {
                System.out.println("Item with ID " + id + " was deleted");
            } else {
                System.out.println("No items found");
            }
        }
    }
}

class FindItemById extends BaseAction {
    public FindItemById(int key, String name) {
            super(key, name);
        }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find item by ID--------------");
        String id = input.ask("Enter item's ID:");
        Item itemTemp = tracker.findByID(id);
        if (itemTemp != null) {
            System.out.println(itemTemp.toString());
        } else {
            System.out.println("No items found");
        }
    }
}

class FindItemsByName extends BaseAction {
    public FindItemsByName(int key, String name) {
        super(key, name);
    }
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("------------ Find item by name--------------");
        String name = input.ask("Enter item's name:");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        } else {
            System.out.println("No items found");
        }
    }
}