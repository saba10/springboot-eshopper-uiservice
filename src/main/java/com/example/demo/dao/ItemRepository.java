package com.example.demo.dao;

import com.example.demo.bean.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long > {
}
