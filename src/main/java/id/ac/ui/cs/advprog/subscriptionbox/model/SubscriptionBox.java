package id.ac.ui.cs.advprog.subscriptionbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name="subscriptionbox")
public class SubscriptionBox {
    @Id
    private String id;
    private String name;
    private String image;
    private String description;
    private double price;

    @OneToMany(mappedBy="subscriptionbox")
    private Set<ItemInBox> itemInBoxList;

    protected SubscriptionBox(BoxBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
