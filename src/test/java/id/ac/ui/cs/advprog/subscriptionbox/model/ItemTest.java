package id.ac.ui.cs.advprog.subscriptionbox.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ItemTest {
    ItemBuilder itemBuilder;
    Item item;
    @BeforeEach
    void setUp(){
        ItemBuilder itemBuilder = new ItemBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd6")
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item = itemBuilder.build();
        item.setDescription("Sunscreen yang licin di kulit");
    }

    @Test
    void testGetItemId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", item.getId());
    }

    @Test
    void testGetItemName(){
        assertEquals("Sunscreen cap Bambang", item.getName());
    }

    @Test
    void testGetItemImage(){
        assertEquals("link gambar", item.getImage());
    }

    @Test
    void testSetItemDescription(){ assertEquals("Sunscreen yang licin di kulit", item.getDescription()); }
}