package id.ac.ui.cs.advprog.subscriptionbox.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class SubscriptionBoxTest {
    BoxBuilder boxBuilder;
    SubscriptionBox box;
    @BeforeEach
    void setUp(){
        BoxBuilder boxBuilder = new BoxBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd8")
                .name("Paket cap Bambang")
                .image("link gambar box");
        box = boxBuilder.build();
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
}