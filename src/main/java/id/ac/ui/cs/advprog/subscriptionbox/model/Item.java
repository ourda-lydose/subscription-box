package id.ac.ui.cs.advprog.subscriptionbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter @Setter
@Data @Entity
@NoArgsConstructor
@Table(name="_item")
public class Item{
    @Id
    private String id;
    private String name;
    private String image;
    private String description;

    protected Item(ItemBuilder builder) {
        this.id = builder.getId();
        this.name = builder.getName();
        this.image = builder.getImage();
    }
}
