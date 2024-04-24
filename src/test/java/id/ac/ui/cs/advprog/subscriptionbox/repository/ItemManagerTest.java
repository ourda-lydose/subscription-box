package id.ac.ui.cs.advprog.subscriptionbox.repository;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemManagerTest {
    @Mock
    ItemManager itemManager;
    Item item1;
    Item item2;

    @BeforeEach
    void setUp(){
        ItemBuilder itemBuilder1 = new ItemBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd6")
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item1 = itemBuilder1.build();
        item1.setDescription("Sunscreen yang licin di kulit");

        ItemBuilder itemBuilder2 = new ItemBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd7")
                .name("Sunscreen cap Bambang uhuy")
                .image("link gambar lageh");
        item2 = itemBuilder2.build();
        item2.setDescription("Sunscreen yang lembab di kulit");
    }

    @Test
    void testAddItem(){
        when(itemManager.save(item1)).thenReturn(item1);

        Item addedItem = itemManager.save(item1);

        verify(itemManager, times(1)).save(item1);

        assertEquals(item1, addedItem);
    }

    @Test
    public void testFindItemById() {
        when(itemManager.findById(item1.getId())).thenReturn(Optional.of(item1));

        Optional<Item> retrievedItem = itemManager.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        verify(itemManager, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        // Assert the expected result
        assertTrue(retrievedItem.isPresent());
        assertEquals(item1, retrievedItem.get());
    }

    @Test
    void testUpdate() {
        Item updatedItem = item2;

        when(itemManager.save(updatedItem)).thenReturn(updatedItem);

        Item updated = itemManager.save(updatedItem);

        verify(itemManager, times(1)).save(updatedItem);

        assertEquals(updatedItem, updated);
    }


    @Test
    void testDelete() {
        doNothing().when(itemManager).deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        itemManager.deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        verify(itemManager, times(1)).deleteById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

}