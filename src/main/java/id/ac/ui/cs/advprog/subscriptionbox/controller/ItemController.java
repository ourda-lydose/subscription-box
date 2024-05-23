//package id.ac.ui.cs.advprog.subscriptionbox.controller;
//
//import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
//import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//@Controller
//@RequestMapping("/item")
//public class ItemController{
//    @Autowired
//    private ItemService itemservice;
//
//    @GetMapping("/createItem")
//    public String createItemPage(Model model) {
//        Item item = new Item();
//        model.addAttribute("item", item);
//        return "createItem";
//    }
//
//    @PostMapping("/createItem")
//    public String createItemPost(@ModelAttribute Item item, Model model){
//        itemservice.create(item);
//        return "redirect:listItem";
//    }
//
//    @GetMapping("/listItem")
//    public String itemListPage(Model model){
//        List<Item> allItems = itemservice.findAll();
//        model.addAttribute("items", allItems);
//        return "itemList";
//    }
//
//    @GetMapping("/editItem/{itemId}")
//    public String editItemPage(@PathVariable String itemId, Model model) {
//        Item item = itemservice.findById(itemId);
//        model.addAttribute("item", item);
//        return "edititem";
//    }
//
//    @PostMapping("/editItem")
//    public String editItemPost(@ModelAttribute Item item, Model model){
//        System.out.println(item.getId());
//        itemservice.update(item.getId(), item);
//
//        return "redirect:listItem";
//    }
//
//    @PostMapping("/deleteItem")
//    public String deleteitem(@RequestParam("itemId") String itemId){
//        itemservice.deleteById(itemId);
//        return "redirect:listItem";
//    }
//}

package id.ac.ui.cs.advprog.subscriptionbox.controller;

import id.ac.ui.cs.advprog.subscriptionbox.dto.ItemRequest;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Use @RestController for automatic response body handling
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping // Use POST for creating new items
    public ResponseEntity<Item> createItem(@RequestBody ItemRequest itemRequest) {
        ItemBuilder itemBuilder = itemRequest.getItemBuilder();
        String description = itemRequest.getDescription();
        Item createdItem = itemService.create(itemBuilder, description);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED); // CREATED status code
    }

    @GetMapping // Use GET for retrieving items
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> allItems = itemService.findAll();
        return new ResponseEntity<>(allItems, HttpStatus.OK); // OK status code
    }

    @GetMapping("/{itemId}") // Use path variable for specific item retrieval
    public ResponseEntity<Item> getItemById(@PathVariable String itemId) {
        Item item = itemService.findById(itemId);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Item not found
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PutMapping("/{itemId}") // Use PUT for updating existing items
    public ResponseEntity<Item> updateItem(@PathVariable String itemId, @RequestBody Item item) {
        Item updatedItem = itemService.update(itemId, item);
        if (updatedItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}") // Use DELETE for removing items
    public ResponseEntity<Void> deleteItem(@PathVariable String itemId) {
        itemService.deleteById(itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content after deletion
    }
}


