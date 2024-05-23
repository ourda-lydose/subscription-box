package id.ac.ui.cs.advprog.subscriptionbox.service;

import id.ac.ui.cs.advprog.subscriptionbox.model.BoxBuilder;
import id.ac.ui.cs.advprog.subscriptionbox.model.Item;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;

import java.util.List;
public interface BoxService {
    public SubscriptionBox create(BoxBuilder boxBuilder, String description, double price);
    public List<SubscriptionBox> findAll();
    SubscriptionBox findById(String boxId);
    public SubscriptionBox update(String idBox, SubscriptionBox newBox);
    public void deleteById(String idBox);
}