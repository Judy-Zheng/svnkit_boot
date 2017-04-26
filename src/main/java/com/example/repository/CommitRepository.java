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

import com.example.domain.entity.Commit;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Purpose.
 *
 * A description of why this class exists.  
 *   For what reason was it written?  
 *   Which jobs does it perform?
 * {@code DataAccessException} using 
 * @author how
 * @date 17/4/24
 */
@Service
public class CommitRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void saveCommit(List<Commit> commitList){
        commitList.stream().forEach(c->{
            mongoTemplate.save(c);
        });
    }

    public  List<Commit> findCommitByUser(String userId,Date startDate){
        Query query = new Query();
        query.addCriteria(Criteria.where("author.name").is(userId));
        Date endDay = DateUtils.addDays(startDate,7);
        query.addCriteria(Criteria.where("authorTimestamp").gt(startDate).lt(endDay));
        return mongoTemplate.find(query,Commit.class);
    }
}
