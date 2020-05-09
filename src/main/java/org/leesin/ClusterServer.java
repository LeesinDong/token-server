package org.leesin;

import com.alibaba.csp.sentinel.cluster.server.ClusterTokenServer;
import com.alibaba.csp.sentinel.cluster.server.SentinelDefaultTokenServer;
import com.alibaba.csp.sentinel.cluster.server.config.ClusterServerConfigManager;
import com.alibaba.csp.sentinel.cluster.server.config.ServerTransportConfig;

import java.util.Collections;

/**
 * @description:
 * @author: Leesin Dong
 * @date: Created in 2020/5/9 0009 20:54
 * @modified By:
 */
public class ClusterServer {
    public static void main(String[] args) throws Exception {
        ClusterTokenServer clusterTokenServer = new SentinelDefaultTokenServer();
        ClusterServerConfigManager.loadGlobalTransportConfig(
                //                          超时时间                端口
                new ServerTransportConfig().setIdleSeconds(600).setPort(9999)
        );
        ClusterServerConfigManager.loadServerNamespaceSet(Collections.singleton("App-Leesin"));//这个就是namespace，可以传入多个
        clusterTokenServer.start();

    }
}
