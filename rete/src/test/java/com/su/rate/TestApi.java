package com.su.rate;

import com.su.rete.simple.SimpleRule;
import org.junit.Test;

import java.util.UUID;

/**
 * @author xusu
 * @since 2021/5/8
 */
public class TestApi {

    @Test
    public void simple(){
        SimpleRule sr = new SimpleRule();
        sr.process(UUID.randomUUID().toString(),"man",15);
    }


}
