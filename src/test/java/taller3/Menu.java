package taller3;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private Map<String, MenuItem> menuItems;

    public Menu() {
        menuItems = new HashMap<>();
    }

    public void addItem(String name, double price) {
        menuItems.put(name, new MenuItem(name, price));
    }

    public boolean containsItem(String name) {
        return menuItems.containsKey(name);
    }

    public MenuItem getItem(String name) {
        return menuItems.get(name);
    }
}
