package id.ac.ui.cs.advprog.subscriptionbox.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String image;
    private String description;
    private double price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="subscriptionbox", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ItemInBox> itemInBoxList;

    protected SubscriptionBox(BoxBuilder builder) {
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
