package id.ac.ui.cs.advprog.subscriptionbox.dto;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.subscriptionbox.dto.ItemRequest;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import org.junit.jupiter.api.Test;

class ItemRequestTest {

    @Test
    void gettersAndSetters() {
        // Create an ItemRequest object
        ItemRequest itemRequest = new ItemRequest();

        // Set values using setters
        ItemBuilder itemBuilder = new ItemBuilder();
        String description = "Test Description";
        itemRequest.setItemBuilder(itemBuilder);
        itemRequest.setDescription(description);

        // Verify values using getters
        assertEquals(itemBuilder, itemRequest.getItemBuilder());
        assertEquals(description, itemRequest.getDescription());
    }

    @Test
    void builderConstructsObjectCorrectly() {
        // Use the builder to construct an ItemRequest object
        ItemBuilder itemBuilder = new ItemBuilder();
        String description = "Test Description";
        ItemRequest itemRequest = ItemRequest.builder()
                .itemBuilder(itemBuilder)
                .description(description)
                .build();

        // Verify that the object is constructed correctly
        assertEquals(itemBuilder, itemRequest.getItemBuilder());
        assertEquals(description, itemRequest.getDescription());
    }
}
