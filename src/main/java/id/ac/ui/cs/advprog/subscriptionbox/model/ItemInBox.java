package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemInBox {
    Item item;
    int quantity;

    public ItemInBox(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}
