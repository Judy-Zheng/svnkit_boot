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

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @todo comm_time集合中保存周报时间期间
 * @author mulan
 * @time 2017年4月14日
 */
public class CommitTimeDao {
    public static void main(String[] args) {
        try {
            MongoDBConnection conn = new MongoDBConnection();
            MongoCollection<Document> collection = conn.getCollection("comm_time");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = dateFormat.parse("2017-04-07");// 指定首个开始日期
            Date endDate = dateFormat.parse("2017-04-13"); // 指定首个结束日期

            Calendar calendar = Calendar.getInstance();
            List<Document> documents = new ArrayList<Document>();
            Document document = null;
            for (int i = 0; i < 100; i++) {
                document = new Document("beginDate", dateFormat.format(beginDate)).append("endDate", dateFormat.format(endDate));
                documents.add(document);
                calendar.setTime(beginDate);
                calendar.add(Calendar.DATE, 7);
                beginDate = calendar.getTime();

                calendar.setTime(endDate);
                calendar.add(Calendar.DATE, 7);
                endDate = calendar.getTime();
            }
            collection.insertMany(documents);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
