package com.cg.demo.mapper;

import com.cg.demo.pojo.UserPOJO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends Mapper {


  @Insert("INSERT INTO `demo`.`tab_user`(`user_id`, `user_name`, `user_age`) VALUES (#{userId}, #{userName}, #{userAge})")
  public int insert(UserPOJO bo);

  @Select("SELECT user_id, user_name, user_age FROM tab_user WHERE user_id = #{id}")
  @Results(id = "baseResult", value = {
      @Result(column = "user_id", property = "userId"),
      @Result(column = "user_name", property = "userName"),
      @Result(column = "user_age", property = "userAge")
  })
  public UserPOJO select(long id);
}
