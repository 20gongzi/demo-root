package com.cg.demo;

import com.cg.demo.pojo.Item;
import com.cg.demo.repository.ItemRepository;
import java.util.Arrays;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticsearchMain.class)
public class SpringBootMainTest {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Autowired
  private ItemRepository itemRepository;

  /**
   * 创建索引库,创建类型,创建映射
   * elasticsearch 6.0.0版本后，单个索引库只支持单个类型
   */
  @Test
  public void createIndex() {
    // 创建索引库
    elasticsearchTemplate.createIndex(Item.class);
    // 创建字段映射
    elasticsearchTemplate.putMapping(Item.class);
  }

  /**
   * 删除索引库
   */
  @Test
  public void deleteIndex() {
    elasticsearchTemplate.deleteIndex(Item.class);
  }

  /**
   * 新增文档
   */
  @Test
  public void save() {
    Item item = new Item(1L, "小米手机", "手机", "小米", 3499.00, "http://image.leyou.com/13123.jpg");
    itemRepository.save(item);
  }

  /**
   * 批量新增文档
   */
  @Test
  public void saveList() {
    List<Item> list = new ArrayList<>();
    list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
    list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
    list.add(new Item(4L, "华为META20", "手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
    list.add(new Item(5L, "华为PRO30", "手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
    // 接收对象集合，实现批量新增
    Iterable<Item> items = itemRepository.saveAll(list);
    items.forEach(x-> System.out.println(x));
  }

  /**
   * 查询所有
   */
  @Test
  public void findll() {
//    Iterable<Item> all = itemRepository.findAll();
//    all.forEach(item -> System.out.println(item));
    Page<Item> page = itemRepository.findAll(PageRequest.of(0, 3));
    page.getContent().stream().forEach(x-> System.out.println(x));
  }

  /**
   * 基本查询
   *
   */
  @Test
  public void findByBrand() {
    List<Item> list = itemRepository.findByBrand("华为");
    list.forEach(x-> System.out.println(x));
  }

  /**
   * 基本查询
   */
  @Test
  public void testQuery() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    queryBuilder.withQuery(QueryBuilders.matchQuery("title", "华为"));
    // 执行查询
    Iterable<Item> items = itemRepository.search(queryBuilder.build());
    items.forEach(System.out::println);
  }

  /**
   * 自定义查询
   */
  @Test
  public void testQuery1() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    // 添加基本的分词查询
    queryBuilder.withQuery(QueryBuilders.matchQuery("title", "手机"));
    // 执行搜索，获取结果
    Page<Item> items = itemRepository.search(queryBuilder.build());

    // 打印总条数
    System.out.println(items.getTotalElements());
    // 打印总页数
    System.out.println(items.getTotalPages());
    items.forEach(System.out::println);
  }

  /**
   * 分页查询
   */
  @Test
  public void testPageQuery() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    // 添加基本的分词查询
    queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
    // 设置分页参数
    queryBuilder.withPageable(PageRequest.of(0, 3));

    // 执行搜索，获取结果
    Page<Item> items = itemRepository.search(queryBuilder.build());
    // 打印总条数
    System.out.println(items.getTotalElements());
    // 打印总页数
    System.out.println(items.getTotalPages());
    // 每页大小
    System.out.println(items.getSize());
    // 当前页
    System.out.println(items.getNumber());
    items.forEach(System.out::println);
  }

  /**
   * 查询排序
   */
  @Test
  public void testSort() {
    // 构建查询条件
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

    // 添加基本的分词查询
    queryBuilder.withQuery(QueryBuilders.termQuery("category", "手机"));
    // 排序
    queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

    // 执行搜索，获取结果
    Page<Item> items = itemRepository.search(queryBuilder.build());
    // 打印总条数
    System.out.println(items.getTotalElements());
    items.forEach(System.out::println);
  }

  /**
   * 聚合查询
   */
  @Test
  public void testAgg() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 不查询任何结果
    queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
    // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
    queryBuilder.addAggregation(AggregationBuilders.terms("brands").field("brand"));
    // 2、查询,需要把结果强转为AggregatedPage类型
    AggregatedPage<Item> aggPage = (AggregatedPage<Item>) itemRepository
        .search(queryBuilder.build());
    // 3、解析
    // 3.1、从结果中取出名为brands的那个聚合，
    // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
    StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
    // 3.2、获取桶
    List<StringTerms.Bucket> buckets = agg.getBuckets();
    // 3.3、遍历
    for (StringTerms.Bucket bucket : buckets) {
      // 3.4、获取桶中的key，即品牌名称
      System.out.println(bucket.getKeyAsString());
      // 3.5、获取桶中的文档数量
      System.out.println(bucket.getDocCount());
    }
  }

  /**
   * 嵌套聚合查询
   */
  @Test
  public void testSubAgg() {
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 不查询任何结果
    queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{""}, null));
    // 1、添加一个新的聚合，聚合类型为terms，聚合名称为brands，聚合字段为brand
    queryBuilder.addAggregation(
        AggregationBuilders.terms("brands").field("brand")
            .subAggregation(AggregationBuilders.avg("priceAvg").field("price"))
        // 在品牌聚合桶内进行嵌套聚合，求平均值
    );
    // 2、查询,需要把结果强转为AggregatedPage类型
    AggregatedPage<Item> aggPage = (AggregatedPage<Item>) this.itemRepository
        .search(queryBuilder.build());
    // 3、解析
    // 3.1、从结果中取出名为brands的那个聚合，
    // 因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
    StringTerms agg = (StringTerms) aggPage.getAggregation("brands");
    // 3.2、获取桶
    List<StringTerms.Bucket> buckets = agg.getBuckets();
    // 3.3、遍历
    for (StringTerms.Bucket bucket : buckets) {
      // 3.4、获取桶中的key，即品牌名称  3.5、获取桶中的文档数量
      System.out.println(bucket.getKeyAsString() + "，共" + bucket.getDocCount() + "台");
      // 3.6.获取子聚合结果
      InternalAvg avg = (InternalAvg) bucket.getAggregations().asMap().get("priceAvg");
      System.out.println("平均售价：" + avg.getValue());
    }
  }
}