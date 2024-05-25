package id.ac.ui.cs.advprog.subscriptionbox.repository;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoxManagerTest {
    @Mock
    BoxManager boxManager;
    Item item1;
    Item item2;
    SubscriptionBox box1;
    SubscriptionBox box2;
    Set<ItemInBox> itemInBoxList = new HashSet<>();

    @BeforeEach
    void setUp(){
        ItemBuilder itemBuilder1 = new ItemBuilder()
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item1 = itemBuilder1.build();
        item1.setDescription("Sunscreen yang licin di kulit");

        ItemBuilder itemBuilder2 = new ItemBuilder()
                .name("Sunscreen cap Bambang uhuy")
                .image("link gambar lageh");
        item2 = itemBuilder2.build();
        item2.setDescription("Sunscreen yang lembab di kulit");

        BoxBuilder boxBuilder = new BoxBuilder()
                .name("Paket cap Bambang")
                .image("link gambar box");
        box1 = boxBuilder.build();
        box1.setDescription("Paket lengkap");
        box1.setPrice(200000);
        ItemInBox itemInBox1 = new ItemInBox(item1.getId(), 2);
        ItemInBox itemInBox2 = new ItemInBox(item2.getId(), 3);
        itemInBoxList.add(itemInBox1);
        itemInBoxList.add(itemInBox2);
        box1.setItemInBoxList(itemInBoxList);

        box2 = boxBuilder.build();
        box2.setDescription("Paket kurang lengkap");
        box2.setPrice(200000);
        itemInBoxList.add(itemInBox1);
        box2.setItemInBoxList(itemInBoxList);
    }

    @Test
    void testAddBox(){
        when(boxManager.save(box1)).thenReturn(box1);

        SubscriptionBox addedBox = boxManager.save(box1);

        verify(boxManager, times(1)).save(box1);

        assertEquals(box1, addedBox);
    }

    @Test
    public void testFindBoxById() {
        when(boxManager.findById(box1.getId())).thenReturn(Optional.of(box1));

        Optional<SubscriptionBox> retrievedBox = boxManager.findById(box1.getId());

        verify(boxManager, times(1)).findById(box1.getId());

        // Assert the expected result
        assertTrue(retrievedBox.isPresent());
        assertEquals(box1, retrievedBox.get());
    }

    @Test
    void testUpdate() {
        SubscriptionBox updatedBox = box2;

        when(boxManager.save(updatedBox)).thenReturn(updatedBox);

        SubscriptionBox updated = boxManager.save(updatedBox);

        verify(boxManager, times(1)).save(updatedBox);

        assertEquals(updatedBox, updated);
    }


    @Test
    void testDelete() {
        doNothing().when(boxManager).deleteById(box1.getId());

        boxManager.deleteById(box1.getId());

        verify(boxManager, times(1)).deleteById(box1.getId());
    }

}