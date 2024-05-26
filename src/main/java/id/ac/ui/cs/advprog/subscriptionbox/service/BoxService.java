package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.ItemInBox;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface BoxService {
    public CompletableFuture<SubscriptionBox> create(BoxBuilder boxBuilder, String description, double price, Set<ItemInBox> itemInBoxList);
    public List<SubscriptionBox> findAll();
    SubscriptionBox findById(String boxId);
    public SubscriptionBox update(String idBox, SubscriptionBox newBox);
    public void deleteById(String idBox);
}