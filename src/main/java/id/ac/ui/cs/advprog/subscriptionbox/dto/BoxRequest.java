package id.ac.ui.cs.advprog.subscriptionbox.dto;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import java.util.Set;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxRequest {
    private BoxBuilder boxBuilder;
    private String description;
    private double price;
    private Set<ItemInBox> itemInBoxList;
}

