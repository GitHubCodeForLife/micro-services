package com.programmingtechie.productservice.utils;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.logging.Logger;

public class MongoDBUtil {
    public static Logger logger = Logger.getLogger(MongoDBUtil.class.getName());
    public static void main(String[] args) {
        String uri = "mongodb://root:root@192.168.163.128:27017/product-service?authSource=admin"; // Replace with your MongoDB URI if different

        try(MongoClient mongoClient = MongoClients.create(uri)) {
            System.out.println("Connected to MongoDB");
            //get database
            logger.info("get database");
            mongoClient.listDatabaseNames().forEach(System.out::println);
            //get collection
            logger.info("get collection");
            mongoClient.getDatabase("product-service").listCollectionNames().forEach(System.out::println);

            MongoCollection<Document> collection = mongoClient.getDatabase("product-service").getCollection("product");

            logger.info("find all");
            collection.find().forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
