package com.lan.zk.watcher;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author Keason
 * @version 创建时间：2022年11月3日 下午11:04:50
 * @TODO
 * 
 */
public class DataChangeWatcher implements Watcher{

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("被调用了");
		if (event.getType() == EventType.NodeDataChanged) {
			System.out.println("数据改变");
		}
		if (event.getType() == EventType.NodeDeleted) {
			System.out.println("节点删除");
		}
	}

}
