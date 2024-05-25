package id.ac.ui.cs.advprog.subscriptionbox.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
class SubscriptionBoxTest {
    BoxBuilder boxBuilder;
    SubscriptionBox box;
    Set<ItemInBox> itemInBoxList = new HashSet<>();
    String box_id;
    @BeforeEach
    void setUp(){
        BoxBuilder boxBuilder = new BoxBuilder()
                .name("Paket cap Bambang")
                .image("link gambar box");
        box = boxBuilder.build();
        box_id = box.getId();
        box.setDescription("Paket lengkap");
        box.setPrice(200000);
        ItemBuilder itemBuilder = new ItemBuilder()
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        Item item = itemBuilder.build();
        ItemInBox itemInBox = new ItemInBox(item.getId(), 2);
        itemInBoxList.add(itemInBox);
        box.setItemInBoxList(itemInBoxList);
    }

    @Test
    void testGetBoxId(){
        assertEquals(box_id, box.getId());
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