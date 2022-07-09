package cart;

import cart.item.Item;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class MapShoppingCart implements ShoppingCart {

    private Map<Item, Integer> items = new HashMap<>();

    @Override
    public Collection<Item> getUniqueItems() {
        return items.keySet();
    }

    @Override
    public void addItem(Item item) {
        if (item != null) {
            Integer occurrences = items.get(item);
            if (occurrences == null) {
                occurrences = 0;
            }
            items.put(item, ++occurrences);
        }
    }

    @Override
    public void removeItem(Item item) {
        if (!(items.containsKey(item))) {
            return;
        }

        Integer occurrences = items.get(item);
        if (occurrences == null) {
            return;
        }

        if (occurrences == 1) {
            items.remove(item);
            return;
        }

        items.put(item, --occurrences);
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }
}
