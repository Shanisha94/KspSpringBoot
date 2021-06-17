package com.ksp.demo.service;

import com.ksp.demo.model.Item;
import org.springframework.stereotype.Component;

@Component
public class InventoryChecker implements IInventoryChecker {

    @Override
    public boolean isInStock(Item item, int amount) {
        return item.getInventory() > amount;
    }

    @Override
    public Item buyItem(Item item, int amount) {
        item.setInventory(item.getInventory() - amount);
        return item;
    }
}
