package com.lan.zk.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author Keason
 * @version 创建时间：2022年11月2日 下午10:28:46
 * @TODO 连接Watcher
 * 
 */
public class ConnectWatcher implements Watcher{

	@Override
	public void process(WatchedEvent event) {
		System.out.println("被调用了");
		if (event.getState() == KeeperState.SyncConnected) {
			System.out.println("连接成功");
		}
		if (event.getState() == KeeperState.Closed) {
			System.out.println("连接关闭");
		}
	}

}
