package id.ac.ui.cs.advprog.subscriptionbox.controller;

import com.fasterxml.jackson.databind.ObjectMapper; // Needed for JSON parsing
import id.ac.ui.cs.advprog.subscriptionbox.dto.BoxRequest;
import id.ac.ui.cs.advprog.subscriptionbox.dto.ItemRequest;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.repository.ItemManager;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class) // Annotate with WebMvcTest for web layer testing
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemController itemController;

    @MockBean // Mock the ItemService to avoid interacting with real database
    private ItemServiceImpl itemService;

    @MockBean
    private ItemManager itemManager;

    private ObjectMapper objectMapper;

    ItemBuilder itemBuilder1;
    Item item1;
    Item item2;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
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
    public void testCreateItem() throws Exception {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setItemBuilder(itemBuilder1);
        itemRequest.setDescription("Sunscreen yang licin di kulit");

        when(itemService.create(itemBuilder1, "Sunscreen yang licin di kulit")).thenReturn(item1);

//        when(itemService.create(itemBuilder1, "Sunscreen yang licin di kulit")).thenReturn(item1);


        mockMvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemRequest)))
                .andExpect(status().isCreated()) // Expect CREATED status code
                .andExpect(content().json(objectMapper.writeValueAsString(item1)));

        verify(itemService, times(1)).create(itemBuilder1, "Sunscreen yang licin di kulit");
    }

    @Test
    public void testGetAllItems() throws Exception {
        List<Item> allItems = new ArrayList<>();
        allItems.add(item1);
        allItems.add(item2);

        when(itemService.findAll()).thenReturn(allItems);

        mockMvc.perform(get("/item"))
                .andExpect(status().isOk()) // Expect OK status code
                .andExpect(content().json(objectMapper.writeValueAsString(allItems)));

        verify(itemService, times(1)).findAll();
    }

    @Test
    public void testGetItemById_Found() throws Exception {

        when(itemService.findById(item1.getId())).thenReturn(item1);

        mockMvc.perform(get("/item/" + item1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(item1)));

        verify(itemService, times(1)).findById(item1.getId());
    }

    @Test
    public void testGetItemById_NotFound() throws Exception {
        String itemId = "456";

        when(itemService.findById(itemId)).thenReturn(null);

        mockMvc.perform(get("/item/" + itemId))
                .andExpect(status().isNotFound()); // Expect NOT_FOUND status code

        verify(itemService, times(1)).findById(itemId);
    }

    @Test
    public void testUpdateItem_Success() throws Exception {

        // Mock findById to return existing item
        when(itemService.findById(item1.getId())).thenReturn(item1);
        when(itemService.findById(item2.getId())).thenReturn(item2);
        when(itemService.update(item1.getId(), item2)).thenReturn(item2);

        // Update the item
        mockMvc.perform(put("/item/" + "eb558e9f-1c39-460e-8860-71af6af63bd6")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item2)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(item2)));

        verify(itemService, times(1)).update(item1.getId(), item2);
    }

    @Test
    public void testUpdateItem_NotFound() throws Exception {
        when(itemManager.findById("lalallalalala")).thenReturn(null);

        mockMvc.perform(put("/item/" + "lalallalalala")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteItem_Success() throws Exception {

        mockMvc.perform(delete("/item/" + item1.getId()))
                .andExpect(status().isNoContent()); // Expect NO_CONTENT

        verify(itemService, times(1)).deleteById(item1.getId());
    }


}
