package com.ksp.demo.service;

import com.ksp.demo.model.Item;
import java.util.List;
import java.util.Optional;

public interface IItemService {
    List<Item> getAllItems();

    Optional<Item> getItemById(long id);

    Item saveItem(Item item);

    void delete(long id);

    Item update(long itemId, Item item);
}
