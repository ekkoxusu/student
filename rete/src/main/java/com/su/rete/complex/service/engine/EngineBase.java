package com.su.rete.complex.service.engine;


import com.su.rete.complex.model.aggregates.TreeRich;
import com.su.rete.complex.model.vo.EngineResult;
import com.su.rete.complex.model.vo.TreeNode;
import com.su.rete.complex.model.vo.TreeRoot;
import com.su.rete.complex.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public abstract class EngineBase extends EngineConfig implements IEngine {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    @Override
    public abstract EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter);

    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String, String> decisionMatter) {
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        Long rootNodeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNodeInfo = treeNodeMap.get(rootNodeId);
        //节点类型[NodeType]；1子叶、2果实
        while (treeNodeInfo.getNodeType().equals(1)) {
            // 获取根节点的规则值
            String ruleKey = treeNodeInfo.getRuleKey();
            // 获取根节点的获取参数的filter
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            // 调用filter获取到对应的比较值
            String matterValue = logicFilter.matterValue(treeId, userId, decisionMatter);
            // 触发比较模式
            Long nextNode = logicFilter.filter(matterValue, treeNodeInfo.getTreeNodeLinkList());
            // 获取下一个节点
            treeNodeInfo = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), userId, treeId, treeNodeInfo.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNodeInfo;
    }

}
