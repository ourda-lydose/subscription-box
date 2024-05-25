package id.ac.ui.cs.advprog.subscriptionbox.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
public class BoxBuilder implements Builder{
    private String name;
    private String image;

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
