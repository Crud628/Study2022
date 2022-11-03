package com.lan.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper.States;

import com.lan.zk.watcher.ConnectWatcher;
import com.lan.zk.watcher.DataChangeWatcher;

/**
 * @author Keason
 * @version 创建时间：2022年11月3日 下午11:03:51
 * @TODO
 * 
 */
public class ZKWatcher {
	public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
		DataChangeWatcher dataChangeWatcher = new DataChangeWatcher();
        ZooKeeper zooKeeper = new ZooKeeper(zkConstant.ZK_HOST, zkConstant.CONNECT_TIMEOUT, dataChangeWatcher);
        System.out.println("客户端开始连接zk服务器");
        States state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(2000);// 要很长时间 不知原因
        state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(2000);
        // watcher设置为true 进行监听，否则不进行监听
        zooKeeper.create(zkConstant.PATH1, "java1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(2000);
        // 
        byte[] data = null;
        data = zooKeeper.getData(zkConstant.PATH1, true, null);
        System.out.println(new String(data));
        
        zooKeeper.setData(zkConstant.PATH1, "java2".getBytes(), -1);
        data = zooKeeper.getData(zkConstant.PATH1, true, null);
        System.out.println(data);
        
        zooKeeper.delete(zkConstant.PATH1, -1);
        zooKeeper.close();
	}
}
