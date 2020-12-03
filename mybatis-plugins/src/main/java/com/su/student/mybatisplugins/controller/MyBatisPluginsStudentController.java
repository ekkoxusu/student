package com.su.student.mybatisplugins.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.su.student.mybatisplugins.model.MybatisPluginsStudent;
import com.su.student.mybatisplugins.service.IMybatisPluginsStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xusu
 * @since 2020/12/2
 */

@RestController
@RequestMapping("/test")
@Api(tags = "学生模块")
public class MyBatisPluginsStudentController {

    @Resource
    private IMybatisPluginsStudentService mybatisPluginsStudentService;

    @GetMapping(value = "page")
    @ApiOperation(value = "学生模块-分页")
    public IPage<MybatisPluginsStudent> page(Integer size,Integer page) {
        return mybatisPluginsStudentService.page(new Page<MybatisPluginsStudent>().setSize(size).setPages(page));
    }

    @PutMapping(value = "update")
    @ApiOperation(value = "学生模块-修改")
    public Boolean update(@RequestBody  MybatisPluginsStudent dto) {
        return mybatisPluginsStudentService.updateById(dto);
    }

    @PostMapping(value = "install")
    @ApiOperation(value = "学生模块-插入")
    public Boolean install(@RequestBody MybatisPluginsStudent dto) {
        return mybatisPluginsStudentService.save(dto);
    }

    @GetMapping(value = "echo/{id}")
    @ApiOperation(value = "学生模块-查询")
    @ApiImplicitParam(name="id",value = "主键",required = true)
    public MybatisPluginsStudent echo(@PathVariable("id")Long id) {
        return mybatisPluginsStudentService.getById(id);
    }

    @DeleteMapping(value = "delete")
    @ApiOperation(value = "学生模块-删除")
    public Boolean delete(@ApiParam(value= "删除的主键们",required = true) @RequestBody List<Long> ids) {
        return mybatisPluginsStudentService.removeByIds(ids);
    }

}
