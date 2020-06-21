package com.cg.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "item", type = "item", shards = 5, replicas = 1)
public class Item {

  @Id
  Long id;
  @Field(type = FieldType.text, analyzer = "ik_max_word")
  String title;
  @Field(type = FieldType.keyword)
  String category;
  @Field(type = FieldType.keyword)
  String brand;
  @Field(type = FieldType.Double)
  Double price;
  @Field(type = FieldType.keyword, index = false)
  String images;
}