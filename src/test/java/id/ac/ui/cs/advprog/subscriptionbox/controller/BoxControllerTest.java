package id.ac.ui.cs.advprog.subscriptionbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.subscriptionbox.dto.BoxRequest;
import id.ac.ui.cs.advprog.subscriptionbox.model.*;
import id.ac.ui.cs.advprog.subscriptionbox.repository.BoxManager;
import id.ac.ui.cs.advprog.subscriptionbox.service.BoxService;
import id.ac.ui.cs.advprog.subscriptionbox.service.BoxServiceImpl;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BoxController.class)
public class BoxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoxController boxController;

    @MockBean // Mock the BoxService to avoid interacting with real database
    private BoxServiceImpl boxService;

    @MockBean
    private BoxManager boxManager;


    private ObjectMapper objectMapper;

    BoxBuilder boxBuilder;
    SubscriptionBox box;
    SubscriptionBox boxLain;

    Set<ItemInBox> itemInBoxList = new HashSet<>();
    Set<ItemInBox> itemInBoxList2 = new HashSet<>();

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(boxController).build();
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
        ItemInBox itemInBox = new ItemInBox(item.getId(), 2);
        itemInBoxList.add(itemInBox);
        box.setItemInBoxList(itemInBoxList);

        BoxBuilder boxBuilder2 = new BoxBuilder()
                .id("eb558e9f-1c39-460e-8860-71af6af63bd9")
                .name("Paket cap Bambang mini")
                .image("link gambar box lageh");
        boxLain = boxBuilder2.build();
        boxLain.setDescription("Paket ga lengkap");
        boxLain.setPrice(200);
        ItemInBox itemInBox2 = new ItemInBox(item.getId(), 1);
        itemInBoxList2.add(itemInBox2);
        boxLain.setItemInBoxList(itemInBoxList2);
    }

    @Test
    public void testCreateBox() throws Exception {
        BoxRequest boxRequest = new BoxRequest();
        boxRequest.setBoxBuilder(boxBuilder);
        boxRequest.setDescription("Paket lengkap");
        boxRequest.setPrice(200000);
        boxRequest.setItemInBoxList(itemInBoxList);


        when(boxService.create(boxBuilder, "Paket lengkap", 200000, itemInBoxList)).thenReturn(box);

        mockMvc.perform(post("/box")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boxRequest)))
                .andExpect(status().isCreated());
//                .andExpect(content().json(objectMapper.writeValueAsString(boxRequest)));

//        verify(boxService, times(1)).create(boxBuilder, "Paket lengkap", 200000, itemInBoxList);
    }

    @Test
    public void testGetAllBoxes() throws Exception {
        List<SubscriptionBox> allBoxes = new ArrayList<>();
        allBoxes.add(box);

        when(boxService.findAll()).thenReturn(allBoxes);

        mockMvc.perform(get("/box"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(allBoxes)));

        verify(boxService, times(1)).findAll();
    }

    @Test
    public void testGetBoxById_Found() throws Exception {

        when(boxService.findById(box.getId())).thenReturn(box);

        mockMvc.perform(get("/box/" + box.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(box)));

        verify(boxService, times(1)).findById(box.getId());
    }

    @Test
    public void testGetBoxById_NotFound() throws Exception {

        when(boxService.findById("lala")).thenReturn(null);

        mockMvc.perform(get("/box/" + "lala"))
                .andExpect(status().isNotFound());

        verify(boxService, times(1)).findById("lala");
    }

    @Test
    public void testGetItemsInBox_Found() throws Exception {

        // Mock boxService.findById to return the box with items
        when(boxService.findById(box.getId())).thenReturn(box);

        mockMvc.perform(get("/box/" + box.getId() + "/items"))
                .andExpect(status().isOk())  // Expect OK status for items found
                .andExpect(jsonPath("$[0]").exists()); // Verify at least one item in response
    }

    @Test
    public void testGetItemsInBox_NotFound() throws Exception {

        // Mock boxService.findById to return the box without items
        when(boxService.findById(box.getId())).thenReturn(box);

        box.setItemInBoxList(null);

        mockMvc.perform(get("/box/" + box.getId() + "/items"))
                .andExpect(status().isOk()); // Expect OK status even if no items
    }

//    @Test
//    public void testUpdateBox_Success() throws Exception {
//        // Mock findById to return existing item
////        when(boxManager.findById(box.getId())).thenReturn(box);
////        when(boxManager.findById(boxLain.getId())).thenReturn(boxLain);
////        when(boxService.findById(box.getId())).thenReturn(box);
////        when(boxService.findById(boxLain.getId())).thenReturn(boxLain);
//        when(boxService.update(boxLain.getId(), box)).thenReturn(box);
//
//        // Update the item
//        mockMvc.perform(put("/box/" + boxLain.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(box)))
//                .andExpect(status().isOk())
//                .andExpect(content().json(objectMapper.writeValueAsString(boxLain)));
//
//        verify(boxService, times(1)).update(boxLain.getId(), box);
//    }

    @Test
    public void testUpdateBox_NotFound() throws Exception {
        when(boxManager.findById("lalallalalala")).thenReturn(null);

        mockMvc.perform(put("/box/" + "lalallalalala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(box)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteBox_Success() throws Exception {

        mockMvc.perform(delete("/box/" + box.getId()))
                .andExpect(status().isNoContent()); // Expect NO_CONTENT

        verify(boxService, times(1)).deleteById(box.getId());
    }
}
