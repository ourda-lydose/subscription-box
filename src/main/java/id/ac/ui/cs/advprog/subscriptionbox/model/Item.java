package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class Item {
    private String id;
    private String name;
    private String image;

    private Item(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.image = builder.image;
    }

    public static class Builder {
        private String id;
        private String name;
        private String image;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder image(String description) {
            this.image = description;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
