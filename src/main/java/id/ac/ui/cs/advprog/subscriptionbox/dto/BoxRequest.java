package id.ac.ui.cs.advprog.subscriptionbox.dto;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoxRequest {
    private BoxBuilder boxBuilder;
    private String description;
    private double price;
}

