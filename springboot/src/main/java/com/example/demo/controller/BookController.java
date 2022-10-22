package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/book")
public class BookController {

    @Resource
    BookMapper bookMapper;

    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        bookMapper.insert(book);
        return Result.success();
    }

    // 更新操作
    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        bookMapper.updateById(book);
        return Result.success();
    }

    // 删除操作
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bookMapper.deleteById(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        // 分页模糊查询like即Book::getNickName, search意为getNickname与search相同；Wrappers相当于Where
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(StrUtil.isNotBlank(search)){
            //这里是对search的应用，getName是Mapper自动加载的东西，是根据entity+Mapper+controller实现的
            wrapper.like(Book::getName, search);
        }
        Page<Book> BookPage = bookMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BookPage);
    }
}
