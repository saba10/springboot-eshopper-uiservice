package com.example.demo.service;

import com.example.demo.bean.Item;
import com.example.demo.dao.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//@Component  -  similar to @Service but use @service for readability
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItemsManual(){
        List<Item> items= new ArrayList<>();
        Item item1 = new Item(1L,"samsung","$20","mobiles");
        Item item2 = new Item(2L,"nokia","$30","mobiles");
        items.add(item1);
        items.add(item2);
        return items;
    }

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> createItems(List<Item> items){
        return itemRepository.saveAll(items);
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).orElse(null);
    }

    public List<Item> getItems(){
        return itemRepository.findAll();
    }

    public String deleteById(Long id){
        itemRepository.deleteById(id);
        return "deleted";
    }

    @Autowired
    RestTemplate restTemplate;

    public List<Item> itemsGetItemById(Long id){
        ResponseEntity<Item[]> itemEntity = restTemplate.getForEntity("http://localhost:9080/api/v1/items/getItemById/{id}", Item[].class, id );
        List<Item> items=new ArrayList<Item>();

        for(int i=0; i<itemEntity.getBody().length ; i++){
            items.add(itemEntity.getBody()[i]);
        }
        return items;
    }

    public List<Item> itemsGetItems(){
        ResponseEntity<Item[]> itemEntity = restTemplate.getForEntity("http://localhost:9080/api/v1/items/getItems", Item[].class );
        List<Item> items=new ArrayList<Item>();

        for(int i=0; i<itemEntity.getBody().length; i++){
            items.add(itemEntity.getBody()[i]);
        }
        return items;
    }

    public List<Item> itemsDeleteById(Long id){
        ResponseEntity<Item[]> itemEntity = restTemplate.getForEntity("http://localhost:9080/api/v1/items/deleteById/{id}", Item[].class, id);
        List<Item> items=new ArrayList<Item>();

        for(int i=0; i<itemEntity.getBody().length ; i++){
            items.add(itemEntity.getBody()[i]);
        }
        return items;
    }

    public List<Item> itemsCraeteItem(Item item){
        ResponseEntity<Item> itemEntity = restTemplate.postForEntity("http://localhost:9080/api/v1/items/createItem", item, Item.class );
        List<Item> items=new ArrayList<>();
        items.add(itemEntity.getBody());
        return items;
    }

}
