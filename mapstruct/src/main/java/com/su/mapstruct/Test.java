package com.su.mapstruct;

import cn.hutool.core.bean.BeanUtil;
import com.su.mapstruct.convert.UserConvert;
import com.su.mapstruct.model.dto.UserDTO;
import com.su.mapstruct.model.eo.UserEO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author xusu
 * @since 2021/4/28
 */
public class Test {
    public static void main(String[] args){
        test1();

        long start = System.nanoTime();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("名字");
        UserEO eo = UserConvert.INSTANCE.dtoToEo(userDTO);
        System.out.println(eo);

        UserDTO dto = UserConvert.INSTANCE.eoToDto(eo);
        System.out.println(dto);
        List<UserEO> eos = UserConvert.INSTANCE.dtosToEos(Arrays.asList(dto));
        System.out.println(eos);
        System.out.println(UserConvert.INSTANCE.eosToDtos(eos));
        long end = System.nanoTime();
        System.out.println("mapstruct效率："+ (end-start));
    }

    public static void test1(){
        long start = System.nanoTime();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("名字");
        UserEO eo = new UserEO();
        BeanUtil.copyProperties(userDTO, eo);
        eo.setNow(LocalDateTime.now());
        System.out.println(eo);

//        UserDTO dto = UserConvert.INSTANCE.eoToDto(eo);
        BeanUtil.copyProperties(eo, userDTO);
        System.out.println(userDTO);
        List<UserEO> objects = new ArrayList<>();
        for (UserDTO dto : Collections.singletonList(userDTO)) {
            UserEO userEO = new UserEO();
            userEO.setNow(LocalDateTime.now());
            BeanUtil.copyProperties(dto,userEO);
            objects.add(userEO);
        }
        System.out.println(objects);
//        List<UserEO> eos = UserConvert.INSTANCE.dtosToEos(Arrays.asList(dto));
//        System.out.println(eos);
        List<UserDTO> objects1 = new ArrayList<>();
        for (UserEO object : objects) {
            UserDTO userDTO1 = new UserDTO();
            BeanUtil.copyProperties(object,userDTO1);
            objects1.add(userDTO1);
        }
        System.out.println(objects1);
        long end = System.nanoTime();
        System.out.println("hutool beanUtil效率："+ (end-start));
    }
}
