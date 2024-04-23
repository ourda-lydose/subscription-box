package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class Item{
    private String id;
    private String name;
    private String image;

    protected Item(ItemBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
