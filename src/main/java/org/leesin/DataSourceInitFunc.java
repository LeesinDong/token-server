package org.leesin;

import com.alibaba.csp.sentinel.cluster.flow.rule.ClusterFlowRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/9 0009 21:04
 * @modified By:
 */
public class DataSourceInitFunc implements InitFunc {
    private final String remoteAddres = "192.168.191.1";
    private final String groupId = "SENTINEL_GROUP";
    private final String FLOW_POSTFIX = "-flow-rules"; //dataId（namespace+postfix）

    //意味着当前的token-server会从nacos上获得限流的规则
    @Override
    public void init() throws Exception {
    //    从nacos上拿到规则
    //    集群限流管理
    //    这是lamda表达式
        ClusterFlowRuleManager.setPropertySupplier(namespace->{
            //读取数据源
            ReadableDataSource<String, List<FlowRule>> rds =
                    //从nacos读取
                    new NacosDataSource<List<FlowRule>>(remoteAddres, groupId, namespace + FLOW_POSTFIX,
                            //如果服务器上返回的是json格式，需要通过这个进行解析
                            source -> JSON.parseObject(source,new TypeReference<List<FlowRule>>(){}));
            return rds.getProperty();
        });

    //    先json解析，然后解析成Flow，然后谁知道数据源
    }


}
