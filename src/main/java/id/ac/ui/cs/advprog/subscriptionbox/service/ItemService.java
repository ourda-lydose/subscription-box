package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import java.util.List;
public interface ItemService {
    public Item create(Item item);
    public List<Item> findAll();
    Item findById(String itemId);
    public Item update(String idItem, Item newItem);
    public void deleteById(String idItem);
}

