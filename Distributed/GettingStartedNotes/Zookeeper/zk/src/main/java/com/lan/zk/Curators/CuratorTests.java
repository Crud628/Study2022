package com.lan.zk.Curators;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher.Event.EventType;

/**
 * @author Keason
 * @version 创建时间：2022年11月3日 下午11:18:36
 * @TODO
 * 
 */
public class CuratorTests {
	public static void main(String[] args) throws Exception {
		String connectString = "192.168.253.128:2181";
		String path = "curator1";
		ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 10);
		CuratorFramework client = CuratorFrameworkFactory.newClient(connectString, retry);
		client.start();
		
		// watcher
		client.getCuratorListenable().addListener((CuratorFramework c,CuratorEvent event) -> {
			
			switch(event.getType()) {
				case WATCHED:
					WatchedEvent watchedEvent = event.getWatchedEvent();
					if(watchedEvent.getType() == EventType.NodeDataChanged) {
						System.out.println(new String(c.getData().forPath(path)));
					}
			}
		});
		
		String data = "test";
		String data2 = "test2";
		client.create().withMode(CreateMode.EPHEMERAL).forPath(path, data.getBytes());
		
		byte[] bytes = client.getData().watched().forPath(path);
		System.out.println(new String(bytes));
		
		client.setData().forPath(path, data2.getBytes());
		
		client.delete().forPath(path);
		Thread.sleep(2000);
		
		// 永久监听
		String pathNew = "curatorNew";
		client.create().withMode(CreateMode.EPHEMERAL).forPath(pathNew, data.getBytes());
		
		NodeCache nodeCache = new NodeCache(client, pathNew);
		nodeCache.start();
		nodeCache.getListenable().addListener(new NodeCacheListener() {

			@Override
			public void nodeChanged() throws Exception {
				// TODO Auto-generated method stub
				ChildData currentData = nodeCache.getCurrentData();
				if (currentData != null) {
					System.out.println(new String(currentData.getData()));
				}
			}
			
		});
		
		// 测试
		client.setData().forPath(pathNew, data2.getBytes());
		Thread.sleep(2000);
		client.setData().forPath(pathNew, data2.getBytes());
		Thread.sleep(2000);
		client.setData().forPath(pathNew, data2.getBytes());
		Thread.sleep(2000);
		client.delete().forPath(pathNew);
	}
}
