package com.su.rete.complex.service.engine;


import com.su.rete.complex.model.aggregates.TreeRich;
import com.su.rete.complex.model.vo.EngineResult;

import java.util.Map;

public interface IEngine {

    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, final Map<String, String> decisionMatter);

}
