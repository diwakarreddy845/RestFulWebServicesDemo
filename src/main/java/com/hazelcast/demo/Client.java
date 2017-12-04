package com.hazelcast.demo;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import java.io.FileInputStream;
import java.util.concurrent.BlockingQueue;

public class Client {

    public static void main(String[] args) throws Exception {
        boolean clientSererMode = false;
        String serverFileName="C:\\Users\\c21757a\\IdeaProjects\\RestFulWebServicesDemo\\src\\main\\resources\\hazelcast.xml";
        String clientFileName= "C:\\Users\\c21757a\\IdeaProjects\\RestFulWebServicesDemo\\src\\main\\resources" +
                               "\\hazelcast-client.xml";
        if (clientSererMode) {
            ClientConfig clientConfig;
            clientConfig = new XmlClientConfigBuilder(clientFileName).build();

            //ClientConfig clientConfig = new ClientConfig();
//            clientConfig.getNetworkConfig().addAddress("127.0.0.1");
            //clientConfig.getGroupConfig().setName("dev").setPassword("dev-pass");
            HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
            System.out.println(clientConfig.toString());

            BlockingQueue<String> queue = client.getQueue("queue");
            queue.put("Hello!");
            System.out.println("Message sent by Hazelcast Client!");

            //HazelcastClient.shutdownAll();
        }else{
            FileInputStream input = new FileInputStream(serverFileName);
            Config config = new XmlConfigBuilder(input).build();
            HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
            IMap map = hazelcastInstance.getMap("DEFAULT");
            System.out.println(map);
        }
    }
}
