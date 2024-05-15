//package id.ac.ui.cs.advprog.subscriptionbox.controller;
//
//import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
//import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
//import id.ac.ui.cs.advprog.subscriptionbox.service.BoxService;
//import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/box")
//public class BoxController{
//    @Autowired
//    private BoxService boxService;
//    private ItemService itemService;
//
//    @GetMapping("/createBox")
//    public String createBoxPage(Model model) {
//        SubscriptionBox box = new SubscriptionBox();
//        model.addAttribute("box", box);
//        model.addAttribute("itemManager", itemService.findAll());
//        return "createBox";
//    }
//
//    @PostMapping("/createBox")
//    public String createBoxPost(@ModelAttribute SubscriptionBox box, Model model){
//        boxService.create(box);
//        return "redirect:listBox";
//    }
//
//    @GetMapping("/listBox")
//    public String boxListPage(Model model){
//        List<SubscriptionBox> allBoxes = boxService.findAll();
//        model.addAttribute("boxManager", allBoxes);
//        return "boxList";
//    }
//
//    @GetMapping("/Box/{boxId}")
//    public String itemInBoxPage(@PathVariable String boxId, Model model){
//        SubscriptionBox box = boxService.findById(boxId);
//        Set<ItemInBox> itemInBox = box.getItemInBoxList();
//        model.addAttribute("itemInBox", itemInBox);
//        return "itemInBox";
//    }
//
//    @GetMapping("/editBox/{boxId}")
//    public String editBoxPage(@PathVariable String boxId, Model model) {
//        SubscriptionBox box = boxService.findById(boxId);
//        model.addAttribute("box", box);
//        model.addAttribute("itemManager", itemService.findAll());
//        return "editbox";
//    }
//
//    @PostMapping("/editBox")
//    public String editBoxPost(@ModelAttribute SubscriptionBox box, Model model){
//        System.out.println(box.getId());
//        boxService.update(box.getId(), box);
//
//        return "redirect:listBox";
//    }
//
//    @PostMapping("/deleteBox")
//    public String deletebox(@RequestParam("boxId") String boxId){
//        boxService.deleteById(boxId);
//        return "redirect:listBox";
//    }
//}

package id.ac.ui.cs.advprog.subscriptionbox.controller;

import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionbox.service.BoxService;
import id.ac.ui.cs.advprog.subscriptionbox.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/box") // Use /api/box for a clear API endpoint
public class BoxController {

    @Autowired
    private BoxService boxService;

    @PostMapping // Use POST for creating new subscription boxes
    public ResponseEntity<SubscriptionBox> createBox(@RequestBody SubscriptionBox box) {
        SubscriptionBox createdBox = boxService.create(box);
        return new ResponseEntity<>(createdBox, HttpStatus.CREATED); // CREATED status code
    }

    @GetMapping // Use GET for retrieving all subscription boxes
    public ResponseEntity<List<SubscriptionBox>> getAllBoxes() {
        List<SubscriptionBox> allBoxes = boxService.findAll();
        return new ResponseEntity<>(allBoxes, HttpStatus.OK); // OK status code
    }

    @GetMapping("/{boxId}") // Use path variable for specific subscription box retrieval
    public ResponseEntity<SubscriptionBox> getBoxById(@PathVariable String boxId) {
        SubscriptionBox box = boxService.findById(boxId);
        if (box == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Box not found
        }
        return new ResponseEntity<>(box, HttpStatus.OK);
    }

    @GetMapping("/{boxId}/items") // Use separate endpoint to retrieve items in a box
    public ResponseEntity<Set<ItemInBox>> getItemsInBox(@PathVariable String boxId) {
        SubscriptionBox box = boxService.findById(boxId);
        if (box == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Box not found
        }
        Set<ItemInBox> itemsInBox = box.getItemInBoxList();
        return new ResponseEntity<>(itemsInBox, HttpStatus.OK);
    }

    @PutMapping("/{boxId}") // Use PUT for updating existing subscription boxes
    public ResponseEntity<SubscriptionBox> updateBox(@PathVariable String boxId, @RequestBody SubscriptionBox box) {
        SubscriptionBox updatedBox = boxService.update(boxId, box);
        if (updatedBox == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Box not found
        }
        return new ResponseEntity<>(updatedBox, HttpStatus.OK);
    }

    @DeleteMapping("/{boxId}") // Use DELETE for removing subscription boxes
    public ResponseEntity<Void> deleteBox(@PathVariable String boxId) {
        boxService.deleteById(boxId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No content after deletion
    }
}

