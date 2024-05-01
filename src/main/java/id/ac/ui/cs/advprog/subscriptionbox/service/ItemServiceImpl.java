package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.repository.ItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemManager itemManager;

    @Override
    public Item create(Item item) {
        return itemManager.save(item);
    }

    @Override
    public List<Item> findAll() {
        return itemManager.findAll();
    }

    @Override
    public Item findById(String id) {
        Optional<Item> optionalItem = itemManager.findById(id);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            // Handle the case where the item with the given ID is not found
            throw new RuntimeException("Item not found with ID: " + id);
        }
    }

    @Override
    public Item update(String idItem, Item newItem){
        Optional<Item> optionalItem = itemManager.findById(idItem);
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();
            // Update user fields
            existingItem.setDescription(newItem.getDescription());
            // Save and return updated user
            itemManager.save(existingItem);
            return existingItem;
        } else {
            // Handle user not found
            throw new RuntimeException("Item not found with ID: " + idItem);
        }
    }

    @Override
    public void deleteById(String idItem){
        itemManager.deleteById(idItem);
    }
}

