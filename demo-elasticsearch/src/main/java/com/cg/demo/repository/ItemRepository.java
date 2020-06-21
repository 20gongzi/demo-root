package com.cg.demo.repository;

import com.cg.demo.pojo.Item;
import java.util.List;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

  List<Item> findByBrand(String brand);
}
