package id.ac.ui.cs.advprog.subscriptionbox.model;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@Data @Entity
@NoArgsConstructor
@Table(name="item")
public class Item{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String image;
    private String description;

    protected Item(ItemBuilder builder) {
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
