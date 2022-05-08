package com.example.demo.controller;

import com.example.demo.bean.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;


//@RestController
@Controller
@RequestMapping(path = "/api/v1")
public class demoController {
    @GetMapping(path="/hello-world")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping(path="/")
    public String homePage(){
        return "index";
    }

    @GetMapping(path="/test1")
    public String test1() { return "test1"; }

    @GetMapping(path="/test2")
    public String test2(ModelMap model) {
        List<Item> items= new ArrayList<>();
        Item item1 = new Item(1L,"samsung","$20","mobiles");
        Item item2 = new Item(2L,"nokia","$30","mobiles");
        items.add(item1);
        items.add(item2);
        model.addAttribute("items", items);
        return "test2";
    }

    /*Below method of initializing object using new should be avoided and Dependency injection should be used
    private final ItemService itemService;
    public demoController(){
        this.itemService = new ItemService();
    }*/

    @Autowired
    ItemService itemService;

    @GetMapping(path="/test3")
    public String test3(ModelMap model) {
        List<Item> items = itemService.getItemsManual();
        model.addAttribute("items", items);
        return "test3";
    }

    @GetMapping(path="/getItemById/{id}")
    public String getItemById(@PathVariable Long id, ModelMap model) {
        Item item = itemService.getItemById(id);
        List<Item> items = new ArrayList<>();
        items.add(item);
        model.addAttribute("items", items);
        return "Home";
    }

    @GetMapping(path="/getItems")
    public String getItems(ModelMap model) {
        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        return "Home";
    }

    @GetMapping(path="/getItemsAfterLogin")
    public String getItemsAfterLogin(ModelMap model) {
        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        return "HomeAfterLogin";
    }

    @GetMapping(path="/deleteById/{id}")
    public String deleteById(@PathVariable Long id, ModelMap model) {
        String status = itemService.deleteById(id);
        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        return "Home";
    }


    @GetMapping(path="/items/getItemById/{id}")
    public String itemsGetItemById(@PathVariable Long id, ModelMap model) {
        List<Item> items = itemService.itemsGetItemById(id);
        model.addAttribute("items", items);
        return "Home";
    }

    @GetMapping(path="/items/getItems")
    public String itemsGetItems(ModelMap model) {
        List<Item> items = itemService.itemsGetItems();
        model.addAttribute("items", items);
        return "Home";
    }

    @GetMapping(path="/items/getItemsAfterLogin")
    public String itemsGetItemsAfterLogin(ModelMap model) {
        List<Item> items = itemService.itemsGetItems();
        model.addAttribute("items", items);
        return "HomeAfterLogin";
    }

    @GetMapping(path="/items/getItemsAfterUserLogin")
    public String itemsGetItemsAfterUserLogin(ModelMap model) {
        List<Item> items = itemService.itemsGetItems();
        model.addAttribute("items", items);
        return "HomeAfterUserLogin";
    }

    @GetMapping(path="/items/deleteById/{id}")
    public String itemsDeleteById(@PathVariable Long id, ModelMap model){
        List<Item> items = itemService.itemsDeleteById(id);
        model.addAttribute("items", items);
        return "Home";
    }

    @GetMapping (path="/getFormToAddProduct")
    public String getFormToAddProduct(){
        return "getFormToAddProduct";
    }

    @PostMapping(path="/addProductAfterAdminLogin")
    public String addProductAfterAdminLogin(@ModelAttribute("product") Item item, ModelMap model){
        System.out.println(item);
        List<Item> items = itemService.itemsCraeteItem(item);
        model.addAttribute("items", items);
        return "HomeAfterLogin";
    }
}
