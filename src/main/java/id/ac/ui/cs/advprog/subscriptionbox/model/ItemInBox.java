package id.ac.ui.cs.advprog.subscriptionbox.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import id.ac.ui.cs.advprog.subscriptionbox.repository.ItemManager;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@Entity
@NoArgsConstructor
@Table(name="item_in_box")
public class ItemInBox {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String idItemInBox;
    private String itemId;
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(name="subscriptionbox_id", nullable=false)
    @JsonBackReference
    private SubscriptionBox subscriptionbox;

    public ItemInBox(String itemId, int quantity) {
        this.itemId = itemId;
        this.quantity = quantity;
    }
}
