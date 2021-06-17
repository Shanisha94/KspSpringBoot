package com.ksp.demo.service;

import com.ksp.demo.exception.OutOfStokException;
import com.ksp.demo.model.Item;
import com.ksp.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableJpaRepositories("com.ksp.demo.repository")
@ComponentScan(basePackages = "com.ksp.demo.model")
public class ItemsServiceImpl implements IItemService {
    private ItemRepository itemRepository;
    private IInventoryChecker inventoryChecker;

    @Autowired
    public ItemsServiceImpl(ItemRepository itemRepository, IInventoryChecker inventoryChecker){
        this.itemRepository = itemRepository;
        this.inventoryChecker = inventoryChecker;
    }

    @Override
    public List<Item> getAllItems()
    {
        return new ArrayList<>(itemRepository.findAll());
    }

    @Override
    public Optional<Item> getItemById(long id)
    {
        return Optional.of(itemRepository.findById(id).get());
    }

    @Override
    public Item saveItem(Item item)
    {
        return itemRepository.save(item);
    }

    @Override
    public void delete(long id)
    {
        itemRepository.deleteById(id);
    }

    @Override
    public Item update(long itemId, Item item) {
        Item itemFromDb = itemRepository.findById(itemId).get();
        itemFromDb.setName(item.getName());
        itemFromDb.setDescription(item.getDescription());
        itemFromDb.setInventory(item.getInventory());
        itemFromDb.setPrice(item.getPrice());
        return itemRepository.save(itemFromDb);
    }

    @Override
    public Item buyItem(long itemId, int amount) throws OutOfStokException {
        Item itemFromDB = itemRepository.findById(itemId).get();
        if(inventoryChecker.isInStock(itemFromDB, amount)){
            Item newItem = inventoryChecker.buyItem(itemFromDB, amount);
            return saveItem(newItem);
        } else
            throw new OutOfStokException("Item is out of stock", itemFromDB.getInventory(), amount);
    }
}
