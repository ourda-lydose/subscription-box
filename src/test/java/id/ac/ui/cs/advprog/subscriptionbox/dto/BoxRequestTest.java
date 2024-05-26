package id.ac.ui.cs.advprog.subscriptionbox.dto;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.subscriptionbox.dto.BoxRequest;
import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

class BoxRequestTest {

    @Test
    void gettersAndSetters() {
        // Create a BoxRequest object
        BoxRequest boxRequest = new BoxRequest();

        // Set values using setters
        BoxBuilder boxBuilder = new BoxBuilder();
        String description = "Test Description";
        double price = 10.5;
        Set<ItemInBox> itemInBoxList = new HashSet<>(); // Add some items if needed
        boxRequest.setBoxBuilder(boxBuilder);
        boxRequest.setDescription(description);
        boxRequest.setPrice(price);
        boxRequest.setItemInBoxList(itemInBoxList);

        // Verify values using getters
        assertEquals(boxBuilder, boxRequest.getBoxBuilder());
        assertEquals(description, boxRequest.getDescription());
        assertEquals(price, boxRequest.getPrice());
        assertEquals(itemInBoxList, boxRequest.getItemInBoxList());
    }

    @Test
    void builderConstructsObjectCorrectly() {
        // Use the builder to construct a BoxRequest object
        BoxBuilder boxBuilder = new BoxBuilder();
        String description = "Test Description";
        double price = 10.5;
        Set<ItemInBox> itemInBoxList = new HashSet<>(); // Add some items if needed
        BoxRequest boxRequest = BoxRequest.builder()
                .boxBuilder(boxBuilder)
                .description(description)
                .price(price)
                .itemInBoxList(itemInBoxList)
                .build();

        // Verify that the object is constructed correctly
        assertEquals(boxBuilder, boxRequest.getBoxBuilder());
        assertEquals(description, boxRequest.getDescription());
        assertEquals(price, boxRequest.getPrice());
        assertEquals(itemInBoxList, boxRequest.getItemInBoxList());
    }
}
