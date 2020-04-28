package com.wangyuhang.wechat_order.mapper;

import com.wangyuhang.wechat_order.bean.ProductCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductCategoryMapper {

    @Select("select * from product_category")
    public List<ProductCategory> findAllProductCategory();

    @Select("select * from product_category where  category_id = #{id}")
    public ProductCategory findProductCategoryById(Integer id);

    @Select({"<script> ",
            "SELECT * FROM product_category ",
            "WHERE category_type IN ",
            "<foreach collection = 'types' separator = ',' open = '(' close = ')' item = 'category'>  ",
            "#{category} ",
            "</foreach> ",
            "</script>"})
    public List<ProductCategory> findProductCategoriesByTypes(@Param("types") List<Integer> types);

    @Options(useGeneratedKeys = true,keyProperty = "categoryId")
    @Insert("insert into product_category(category_name, category_type) values(#{categoryName}, #{categoryType})")
    public int insertNewProductCategory(ProductCategory productCategory);

    @Delete("delete from product_category where category_id = #{id}")
    public void deleteProductCategoryById(Integer id);

    @Update("update product_category set category_name =  #{categoryName},  category_type =  #{categoryType} where category_id = #{categoryId}")
    public Integer updateProductCategory(ProductCategory productCategory);
}
