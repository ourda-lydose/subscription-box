package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class SubscriptionBox {
    private String id;
    private String name;
    private String image;

    protected SubscriptionBox(BoxBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
