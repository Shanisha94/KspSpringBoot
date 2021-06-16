package com.ksp.demo.controller;

import com.ksp.demo.model.Item;
import com.ksp.demo.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class KspController {
    private IItemService itemsService;

    @Autowired
    public KspController(IItemService itemsService){
        this.itemsService = itemsService;
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems()
    {
        try {
            List<Item> items = itemsService.getAllItems();
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/{itemid}")
    private ResponseEntity<Item> getItem(@PathVariable("itemid") long itemid)
    {
        Optional<Item> item = itemsService.getItemById(itemid);
        if(item.isPresent())
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{itemid}")
    private ResponseEntity<Item> deleteItem(@PathVariable("itemid") long itemid)
    {
        itemsService.delete(itemid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    private ResponseEntity<Item> createItem(@RequestBody Item item)
    {
        try {
            Item newItem = itemsService.saveItem(item);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("item", "/api/item/" + newItem.getId());
            return new ResponseEntity<>(newItem, httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{itemid}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemid") long itemId, @RequestBody Item item) {
        Optional<Item> itemFromDB = itemsService.getItemById(itemId);
        if(itemFromDB.isPresent()) {
            return new ResponseEntity<>(itemsService.update(itemId, item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
