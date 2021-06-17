package com.ksp.demo.service;

import com.ksp.demo.model.Item;

public interface IInventoryChecker {

    boolean isInStock(Item item, int amount);

    Item buyItem(Item item, int amount);
}
