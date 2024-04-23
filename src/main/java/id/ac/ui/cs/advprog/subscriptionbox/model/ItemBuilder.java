package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class ItemBuilder implements Builder{
    private String id;
    private String name;
    private String image;

    @Override
    public ItemBuilder id(String id) {
        this.id = id;
        return this;
    }
    @Override
    public ItemBuilder name(String name) {
        this.name = name;
        return this;
    }
    @Override
    public ItemBuilder image(String image) {
        this.image = image;
        return this;
    }
    @Override
    public Item build() {
        return new Item(this);
    }
}
