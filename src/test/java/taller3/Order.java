package taller3;

import java.util.HashMap;
import java.util.Map;

public class Order {
    private Map<String, Integer> items;

    public Order() {
        items = new HashMap<>();
    }

    public void addItem(String name, int quantity) {
        items.put(name, items.getOrDefault(name, 0) + quantity);
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public int getTotalItems() {
        return items.values().stream().mapToInt(Integer::intValue).sum();
    }
}
