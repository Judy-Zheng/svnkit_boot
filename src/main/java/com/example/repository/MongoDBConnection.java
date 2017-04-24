/**
 * Copyright 2015-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.repository;


import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Purpose.
 * <p>
 * A description of why this class exists.
 * For what reason was it written?
 * Which jobs does it perform?
 * {@code DataAccessException} using
 *
 * @author how
 * @date 17/4/20
 */
/**
 * @todo 通过数据库名称连接数据库，如果指定的数据库不存在，mongo会自动创建数据库
 * @author mulan
 * @time 2017年4月10日
 */
public class MongoDBConnection {
    /**
     * @todo 连接名为databaseName的数据库，存在直接连接，不存在创建后连接
     * @author mulan
     * @time 2017年4月10日
     */
    public MongoDatabase dbconnect() {
        MongoDatabase mongoDatabase = null;
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase("logs");
        return mongoDatabase;
    }

    /**
     * @todo 通过集合名获取集合
     * @author mulan
     * @time 2017年4月11日
     */
    public MongoCollection<Document> getCollection(String collectionName) {
        MongoDatabase mongoDatabase = dbconnect();
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection;
    }

    /**
     * @todo 直接通过数据库名和集合名获取数据库链接、集合
     * @author mulan
     * @time 2017年4月11日
     */
    public MongoCollection<Document> getDBAndCollection(String dbName, String collectionName) {
        MongoDatabase mongoDatabase = null;
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoDatabase = mongoClient.getDatabase(dbName);
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection;
    }
}

