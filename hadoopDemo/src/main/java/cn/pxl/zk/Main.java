package cn.pxl.zk;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        HelloWorld helloWorld = new HelloWorld();
        ZooKeeper zooKeeper = helloWorld.getZooKeeper();
        List<String> children = zooKeeper.getChildren("/", false);
        System.out.println(children);
        //创建临时节点
        String s = zooKeeper.create("/sanguo", "sanguo".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(s);
        Thread.sleep(10000);
        System.out.println(zooKeeper.getChildren("/sanguo", false) != null);
        zooKeeper.close();
    }
}
