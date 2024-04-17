package id.ac.ui.cs.advprog.subscriptionbox.model;

import lombok.Getter;

@Getter
public class Item {
    private String id;
    private String name;
    private String description;

    private Item(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
    }

    public static class Builder {
        private String id;
        private String name;
        private String description;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder desc(String description) {
            this.description = description;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }
}
