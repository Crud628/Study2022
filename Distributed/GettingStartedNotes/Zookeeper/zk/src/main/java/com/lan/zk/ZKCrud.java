package com.lan.zk;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.Stat;

import com.lan.zk.watcher.ConnectWatcher;

/**
 * @author Keason
 * @version 创建时间：2022年11月3日 下午10:52:29
 * @TODO
 * 
 */
public class ZKCrud {
    public static void main( String[] args ) throws IOException, InterruptedException, KeeperException{
    	ConnectWatcher connectWatcher = new ConnectWatcher();
        ZooKeeper zooKeeper = new ZooKeeper(zkConstant.ZK_HOST, zkConstant.CONNECT_TIMEOUT, connectWatcher);
        System.out.println("客户端开始连接zk服务器");
        States state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(2000);// 要很长时间 不知原因
        state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(2000);
        // 增加
        zooKeeper.create(zkConstant.PATH1, "java1".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        Thread.sleep(2000);
        // 
        byte[] data = null;
        data = zooKeeper.getData(zkConstant.PATH1, null, null);
        System.out.println(new String(data));
        
        zooKeeper.setData(zkConstant.PATH1, "java2".getBytes(), -1);
        data = zooKeeper.getData(zkConstant.PATH1, null, null);
        System.out.println(data);
        
        zooKeeper.delete(zkConstant.PATH1, -1);
        zooKeeper.close();
    }
}
