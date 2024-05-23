package id.ac.ui.cs.advprog.subscriptionbox.dto;

import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    private ItemBuilder itemBuilder;
    private String description;
}
