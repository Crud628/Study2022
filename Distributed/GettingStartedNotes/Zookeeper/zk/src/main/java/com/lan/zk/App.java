package com.lan.zk;

import java.io.IOException;

import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

import com.lan.zk.watcher.ConnectWatcher;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws IOException, InterruptedException{
    	ConnectWatcher connectWatcher = new ConnectWatcher();
        ZooKeeper zooKeeper = new ZooKeeper(zkConstant.ZK_HOST, zkConstant.CONNECT_TIMEOUT, connectWatcher);
        System.out.println("客户端开始连接zk服务器");
        States state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(20000);// 要很长时间 不知原因
        state = zooKeeper.getState();
        System.out.println(state);
        Thread.sleep(8000);
        zooKeeper.close();
    }
}
