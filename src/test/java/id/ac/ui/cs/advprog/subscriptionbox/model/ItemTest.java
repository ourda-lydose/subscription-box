package id.ac.ui.cs.advprog.subscriptionbox.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ItemTest {
    Item.Builder itemBuilder;
    Item item;
    @BeforeEach
    void setUp(){
        Item.Builder itemBuilder = new Item.Builder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd6")
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item = itemBuilder.build();
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
    void testGetItemDesc(){
        assertEquals("link gambar", item.getImage());
    }
}