package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class Item{
    private String id;
    private String name;
    private String image;
    private String description;

    protected Item(ItemBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
