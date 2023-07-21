package taller3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderProcessorTest {
    private Menu menu;
    private Order order;

    @BeforeEach
    public void setUp() {
        menu = new Menu();
        menu.addItem("Pizza", 10.0);
        menu.addItem("Hamburguesa", 8.0);
        menu.addItem("Ensalada", 6.0);
        menu.addItem("Pastas", 12.0);

        order = new Order();
    }

    @Test
    public void testProcessOrder_NoItems() {
        double totalCost = OrderProcessor.processOrder(order, menu);
        assertEquals(0.0, totalCost, 0.001);
    }

    @Test
    public void testProcessOrder_ExceedsMaxItems() {
        order.addItem("Pizza", 101);
        double totalCost = OrderProcessor.processOrder(order, menu);
        assertEquals(-1.0, totalCost, 0.001);
    }

    @Test
    public void testProcessOrder_NoDiscount() {
        order.addItem("Pizza", 2);
        order.addItem("Hamburguesa", 3);
        double totalCost = OrderProcessor.processOrder(order, menu);
        assertEquals(52.0, totalCost, 0.001);
    }

    @Test
    public void testProcessOrder_Discount1() {
        order.addItem("Pizza", 6);
        double totalCost = OrderProcessor.processOrder(order, menu);
        assertEquals(54.0, totalCost, 0.001);
    }

    @Test
    public void testProcessOrder_Discount2() {
        order.addItem("Pizza", 11);
        double totalCost = OrderProcessor.processOrder(order, menu);
        assertEquals(88.0, totalCost, 0.001);
    }
}
