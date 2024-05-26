package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
import id.ac.ui.cs.advprog.subscriptionbox.repository.BoxManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class BoxServiceImpl implements BoxService {
    @Autowired
    private BoxManager boxManager;

    @Async
    @Override
    public CompletableFuture<SubscriptionBox> create(BoxBuilder boxBuilder, String description, double price, Set<ItemInBox> itemInBoxList) {
        SubscriptionBox box = boxBuilder.build();
        box.setDescription(description);
        box.setPrice(price);

        Set<ItemInBox> itemInBoxSet = new HashSet<ItemInBox>();
        for (ItemInBox item : itemInBoxList) {
            // new Blog
            ItemInBox itemInBox = new ItemInBox(item.getItemId(), item.getQuantity());
            // set owner to Blog
            itemInBox.setSubscriptionbox(box);
            // add blog to list
            itemInBoxSet.add(itemInBox);
        }

        box.setItemInBoxList(itemInBoxSet);

        boxManager.save(box);
        return CompletableFuture.completedFuture(box);
    }


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
    public SubscriptionBox update(String idBox, SubscriptionBox newBox) {
        Optional<SubscriptionBox> optionalBox = boxManager.findById(idBox);
        if (optionalBox.isPresent()) {
            SubscriptionBox existingBox = optionalBox.get();

            // Update fields of the existing box
            existingBox.setDescription(newBox.getDescription());
            existingBox.setPrice(newBox.getPrice());

            // Remove all existing items
            existingBox.getItemInBoxList().clear();
            boxManager.save(existingBox);

            // Set new items in the box
            Set<ItemInBox> newItems = newBox.getItemInBoxList();
            for (ItemInBox newItem : newItems) {
                newItem.setSubscriptionbox(existingBox); // Link the item to the existing box
                existingBox.getItemInBoxList().add(newItem);
            }

            // Save the updated box
            SubscriptionBox updatedBox = boxManager.save(existingBox);
            return updatedBox;
        } else {
            // Handle box not found
            throw new RuntimeException("Box not found with ID: " + idBox);
        }
    }


    @Override
    public void deleteById(String idBox){
        boxManager.deleteById(idBox);
    }
}


