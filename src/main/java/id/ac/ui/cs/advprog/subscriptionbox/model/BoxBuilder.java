package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class BoxBuilder implements Builder{
    private String id;
    private String name;
    private String image;

    @Override
    public BoxBuilder id(String id) {
        this.id = id;
        return this;
    }
    @Override
    public BoxBuilder name(String name) {
        this.name = name;
        return this;
    }
    @Override
    public BoxBuilder image(String image) {
        this.image = image;
        return this;
    }
    @Override
    public SubscriptionBox build() {
        return new SubscriptionBox(this);
    }
}
