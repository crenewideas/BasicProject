package cn.pxl.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class HelloWorld {

    private ZooKeeper zooKeeper = new ZooKeeper("PXL001:2181,PXL002:2181,PXL003:2181",10000, HelloWorld::process);

    public HelloWorld() throws IOException {
    }

    private static void process(WatchedEvent e) {
        System.out.println(e);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }
}
