package id.ac.ui.cs.advprog.subscriptionbox.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class SubscriptionBoxTest {
    BoxBuilder boxBuilder;
    SubscriptionBox box;
    ArrayList<ItemInBox> itemInBoxList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        BoxBuilder boxBuilder = new BoxBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd8")
                .name("Paket cap Bambang")
                .image("link gambar box");
        box = boxBuilder.build();
        box.setDescription("Paket lengkap");
        box.setPrice(200000);
        ItemBuilder itemBuilder = new ItemBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd6")
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        Item item = itemBuilder.build();
        ItemInBox itemInBox = new ItemInBox(item, 2);
        itemInBoxList.add(itemInBox);
        box.setItemInBoxList(itemInBoxList);
    }

    @Test
    void testGetBoxId(){
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd8", box.getId());
    }

    @Test
    void testGetBoxName(){
        assertEquals("Paket cap Bambang", box.getName());
    }

    @Test
    void testGetBoxImage(){
        assertEquals("link gambar box", box.getImage());
    }

    @Test
    void testGetBoxPrice(){
        assertEquals(200000, box.getPrice());
    }

    @Test
    void testGetItemInBoxList(){
        assertSame(itemInBoxList, box.getItemInBoxList());
    }

    @Test
    void testGetDescription(){
        assertEquals("Paket lengkap", box.getDescription());
    }
}