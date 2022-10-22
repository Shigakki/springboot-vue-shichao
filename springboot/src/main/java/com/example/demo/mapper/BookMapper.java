package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Book;

//这里的BaseMapper是针对MyBatisPlus中的东西，是一个集成化的对数据库操作的映射接口
public interface BookMapper extends BaseMapper<Book> {
}
