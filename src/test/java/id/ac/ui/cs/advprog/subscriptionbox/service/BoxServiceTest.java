package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.*;
import id.ac.ui.cs.advprog.subscriptionbox.repository.BoxManager;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoxServiceTest {
    @InjectMocks
    BoxServiceImpl service;
    @Mock
    BoxManager boxManager;
    SubscriptionBox box1;
    SubscriptionBox box2;
    List<SubscriptionBox> boxes = new ArrayList<>();
    Item item1;
    Item item2;
    Set<ItemInBox> itemInBoxList = new HashSet<>();
    BoxBuilder boxBuilder = new BoxBuilder();

    SubscriptionBox findById(String id){
        for(SubscriptionBox box : boxes){
            if(box.getId().equals(id)){
                return box;
            }
        }
        throw new RuntimeException("Box not found with ID: " + id);
    }

    SubscriptionBox deleteById(String id){
        for(SubscriptionBox box : boxes){
            if(box.getId().equals(id)){
                boxes.remove(box);
                return box;
            }
        }
        throw new RuntimeException("Box not found with ID: " + id);
    }

    @BeforeEach
    void setUp(){
        ItemBuilder itemBuilder1 = new ItemBuilder()
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        item1 = itemBuilder1.build();
        item1.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        item1.setDescription("Sunscreen yang licin di kulit");

        ItemBuilder itemBuilder2 = new ItemBuilder()
                .name("Sunscreen cap Bambang uhuy")
                .image("link gambar lageh");
        item2 = itemBuilder2.build();
        item2.setId("eb558e9f-1c39-460e-8860-71af6af63bd7");
        item2.setDescription("Sunscreen yang lembab di kulit");

        BoxBuilder boxBuilder = new BoxBuilder()
                .name("Paket cap Bambang")
                .image("link gambar box");
        box1 = boxBuilder.build();
        box1.setId("eb558e9f-1c39-460e-8860-71af6af63bd8");
        box1.setDescription("Paket lengkap");
        box1.setPrice(200000);
        ItemInBox itemInBox1 = new ItemInBox(item1.getId(), 2);
        ItemInBox itemInBox2 = new ItemInBox(item2.getId(), 3);
        itemInBoxList.add(itemInBox1);
        itemInBoxList.add(itemInBox2);
        box1.setItemInBoxList(itemInBoxList);

        box2 = boxBuilder.build();
        box2.setId("eb558e9f-1c39-460e-8860-71af6af63bd9");
        box2.setDescription("Paket kurang lengkap");
        box2.setPrice(200000);
        itemInBoxList.add(itemInBox1);
        box2.setItemInBoxList(itemInBoxList);
    }

    @Test
    void testCreateAndFind(){
        doReturn(box1).when(boxManager).save(any(SubscriptionBox.class));
        service.create(boxBuilder, "Paket lengkap", 200000, itemInBoxList);
        boxes.add(box1);
        when(boxManager.findAll()).thenReturn(boxes);
        List<SubscriptionBox> boxIterator = service.findAll();
        assertTrue(boxIterator.contains(box1));
        SubscriptionBox savedBox = boxIterator.getFirst();
//        assertEquals(savedBox.getId(),"eb558e9f-1c39-460e-8860-71af6af63bd8");
        assertEquals(savedBox.getName(), "Paket cap Bambang");
        assertEquals(savedBox.getImage(), "link gambar box");
    }

    @Test
    void testEdit() {
        doReturn(box1).when(boxManager).save(any(SubscriptionBox.class));
        service.create(boxBuilder, "Paket lengkap", 200000, itemInBoxList);
        boxes.add(box1);
        when(boxManager.findById(box1.getId())).thenReturn(Optional.ofNullable(findById(box1.getId())));
        service.update(box1.getId(), box2);
        when(boxManager.findAll()).thenReturn(boxes);
        List<SubscriptionBox> boxIterator = service.findAll();
        SubscriptionBox savedBox = boxIterator.getFirst();
        assertEquals(savedBox.getName(), box2.getName());
        assertEquals(savedBox.getImage(), box2.getImage());
    }

    @Test
    void testDelete() {
        // Mock the behavior of boxManager.deleteById()
        doNothing().when(boxManager).deleteById(box1.getId());

        // Call the service method
        service.deleteById(box1.getId());

        // Verify that boxManager.deleteById() was called with the correct ID
        verify(boxManager).deleteById(box1.getId());
    }

}
