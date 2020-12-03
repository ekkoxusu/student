package com.su.student.mybatisplugins.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.su.student.mybatisplugins.mapper.MybatisPluginsStudentMapper;
import com.su.student.mybatisplugins.model.MybatisPluginsStudent;
import com.su.student.mybatisplugins.service.IMybatisPluginsStudentService;
import org.springframework.stereotype.Service;

/**
 * @author xusu
 * @since 2020/12/2
 */
@Service
public class MybatisPluginsStudentServiceImpl extends ServiceImpl<MybatisPluginsStudentMapper,MybatisPluginsStudent> implements IMybatisPluginsStudentService {
}
