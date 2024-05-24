package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionbox.repository.BoxManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class BoxServiceImpl implements BoxService {
    @Autowired
    private BoxManager boxManager;

    @Override
    public SubscriptionBox create(BoxBuilder boxBuilder, String description, double price, Set<ItemInBox> itemInBoxList) {
        SubscriptionBox box = boxBuilder.build();
        box.setDescription(description);
        box.setPrice(price);
        box.setItemInBoxList(itemInBoxList);
        boxManager.save(box);
        return box;
    }

//    @Async
//    public SubscriptionBox createAsync(SubscriptionBox box) throws InterruptedException{
//        SubscriptionBox savedBox = boxManager.save(box);
//        return new AsyncResult<>(savedBox);
//    }

    @Override
    public List<SubscriptionBox> findAll() {
        return boxManager.findAll();
    }

    @Override
    public SubscriptionBox findById(String id) {
        Optional<SubscriptionBox> optionalBox = boxManager.findById(id);
        if (optionalBox.isPresent()) {
            return optionalBox.get();
        } else {
            // Handle the case where the item with the given ID is not found
            throw new RuntimeException("Box not found with ID: " + id);
        }
    }

    @Override
    public SubscriptionBox update(String idBox, SubscriptionBox newBox){
        Optional<SubscriptionBox> optionalBox = boxManager.findById(idBox);
        if (optionalBox.isPresent()) {
            SubscriptionBox existingBox= optionalBox.get();
            // Update user fields
            existingBox.setDescription(newBox.getDescription());
            existingBox.setItemInBoxList(newBox.getItemInBoxList());
            existingBox.setPrice(newBox.getPrice());
            // Save and return updated user
            boxManager.save(existingBox);
            return existingBox;
        } else {
            // Handle user not found
            throw new RuntimeException("Box not found with ID: " + idBox);
        }
    }

    @Override
    public void deleteById(String idBox){
        boxManager.deleteById(idBox);
    }
}


