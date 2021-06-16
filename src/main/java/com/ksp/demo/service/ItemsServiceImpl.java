package com.ksp.demo.service;

import com.ksp.demo.model.Item;
import com.ksp.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@EnableJpaRepositories("com.ksp.demo.repository")
@ComponentScan(basePackages = "com.ksp.demo.model")
public class ItemsServiceImpl implements IItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemsServiceImpl(ItemRepository itemRepository){ // is it better to do constructor autowire?
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAllItems()
    {
        return new ArrayList<>(itemRepository.findAll());
    }

    @Override
    public Optional<Item> getItemById(long id) // optional?
    {
        return Optional.of(itemRepository.findById(id).get());
    }

    @Override
    public Item saveItem(Item item)
    {
        //item.setCreatedOn(Timestamp.valueOf(LocalDateTime.now()));
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
        //itemFromDb.setLastUpdated(Timestamp.valueOf(LocalDateTime.now()));
        return itemRepository.save(itemFromDb);
    }
}
