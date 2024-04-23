package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item{
    private final String id;
    private final String name;
    private final String image;
    private String description;

    protected Item(ItemBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
