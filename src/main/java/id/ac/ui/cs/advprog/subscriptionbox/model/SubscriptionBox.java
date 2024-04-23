package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class SubscriptionBox {
    private final String id;
    private final String name;
    private final String image;
    private String description;
    private double price;
    private ArrayList<ItemInBox> itemInBoxList;

    protected SubscriptionBox(BoxBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
