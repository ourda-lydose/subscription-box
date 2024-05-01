package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.repository.ItemManager;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {
    @InjectMocks
    ItemServiceImpl service;
    @Mock
    ItemManager itemManager;
    Item item1;
    Item item2;
    List<Item> items = new ArrayList<>();

    Item findById(String id){
        for(Item item : items){
            if(item.getId().equals(id)){
                return item;
            }
        }
        throw new RuntimeException("Item not found with ID: " + id);
    }

    Item deleteById(String id){
        for(Item item : items){
            if(item.getId().equals(id)){
                items.remove(item);
                return item;
            }
        }
        throw new RuntimeException("Item not found with ID: " + id);
    }

    @BeforeEach
    void setUp(){
        ItemBuilder itemBuilder1 = new ItemBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd6")
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item1 = itemBuilder1.build();
        item1.setDescription("Sunscreen yang licin di kulit");
        item2 = itemBuilder1.build();
        item2.setDescription("Sunscreen yang lembab di kulit");
    }

    @Test
    void testCreateAndFind(){
        doReturn(item1).when(itemManager).save(item1);
        service.create(item1);
        items.add(item1);
        when(itemManager.findAll()).thenReturn(items);
        List<Item> itemIterator = service.findAll();
        assertTrue(itemIterator.contains(item1));
        Item savedItem = itemIterator.getFirst();
        assertEquals(savedItem.getId(),"eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(savedItem.getName(), "Sunscreen cap Bambang");
        assertEquals(savedItem.getImage(), "link gambar");
    }

    @Test
    void testEdit() {
        doReturn(item1).when(itemManager).save(item1);
        service.create(item1);
        items.add(item1);
        when(itemManager.findById(item1.getId())).thenReturn(Optional.ofNullable(findById(item1.getId())));
        service.update(item1.getId(), item2);
        when(itemManager.findAll()).thenReturn(items);
        List<Item> itemIterator = service.findAll();
        Item savedItem = itemIterator.getFirst();
        assertEquals(savedItem.getId(), item2.getId());
        assertEquals(savedItem.getName(), item2.getName());
        assertEquals(savedItem.getImage(), item2.getImage());
        assertEquals(savedItem.getDescription(), item2.getDescription());
    }

    @Test
    void testDelete() {
        // Mock the behavior of itemManager.deleteById()
        doNothing().when(itemManager).deleteById(item1.getId());

        // Call the service method
        service.deleteById(item1.getId());

        // Verify that itemManager.deleteById() was called with the correct ID
        verify(itemManager).deleteById(item1.getId());
    }

}
