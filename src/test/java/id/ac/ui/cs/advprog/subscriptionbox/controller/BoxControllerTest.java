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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
                .name("Paket cap Bambang")
                .image("link gambar box");
        box = boxBuilder.build();
        box.setId("eb558e9f-1c39-460e-8860-71af6af63bd8");
        box.setDescription("Paket lengkap");
        box.setPrice(200000);
        ItemBuilder itemBuilder = new ItemBuilder()
                .name("Sunscreen cap Bambang")
                .image("link gambar");
        Item item = itemBuilder.build();
        item.setId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        ItemInBox itemInBox = new ItemInBox(item.getId(), 2);
        itemInBoxList.add(itemInBox);
        box.setItemInBoxList(itemInBoxList);

        BoxBuilder boxBuilder2 = new BoxBuilder()
                .name("Paket cap Bambang mini")
                .image("link gambar box lageh");
        boxLain = boxBuilder2.build();
        boxLain.setId("eb558e9f-1c39-460e-8860-71af6af63bd9");
        boxLain.setDescription("Paket ga lengkap");
        boxLain.setPrice(200);
        ItemInBox itemInBox2 = new ItemInBox(item.getId(), 1);
        itemInBoxList2.add(itemInBox2);
        boxLain.setItemInBoxList(itemInBoxList2);
    }

    @Test
    void createBox_ReturnsCreatedBox() {
        BoxRequest boxRequest = new BoxRequest();
        boxRequest.setBoxBuilder(boxBuilder);
        boxRequest.setDescription("Paket lengkap");
        boxRequest.setPrice(200000);
        boxRequest.setItemInBoxList(itemInBoxList);
        CompletableFuture<SubscriptionBox> future = CompletableFuture.completedFuture(box);

        when(boxService.create(any(), anyString(), anyDouble(), anySet())).thenAnswer(invocation -> future);

        ResponseEntity<?> responseEntity = boxController.createBox(boxRequest).join();

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(future, responseEntity.getBody());
    }

    @Test
    void createBox_ReturnsInternalServerError() {
        BoxRequest boxRequest = new BoxRequest(/* provide necessary parameters */);
        CompletableFuture<SubscriptionBox> future = CompletableFuture.completedFuture(null);

        when(boxService.create(any(), anyString(), anyDouble(), anySet())).thenAnswer(invocation -> future);

        ResponseEntity<?> responseEntity = boxController.createBox(boxRequest).join();

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Failed to create subscription box", responseEntity.getBody());
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

    @Test
    void updateBox_Successfull() {
        // Mocking the behavior of boxService.update to return the updated box
        when(boxService.update(eq(box.getId()), any(SubscriptionBox.class))).thenReturn(boxLain);

        ResponseEntity<SubscriptionBox> responseEntity = boxController.updateBox(box.getId(), boxLain);

        // Verify that the response status is OK and the returned box is the updated one
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(boxLain, responseEntity.getBody());
    }

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
