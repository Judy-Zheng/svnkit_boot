package com.example;

import com.example.domain.entity.Author;
import com.example.domain.entity.Commit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

/**
 * Purpose.
 * <p>
 * A description of why this class exists.
 * For what reason was it written?
 * Which jobs does it perform?
 * {@code DataAccessException} using
 *
 * @author how
 * @date 17/4/23
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringMongoConfigTest {


    @Test
    public void mongoTemplate() throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
        Commit commit = new Commit();
        commit.setAuthor(new Author("张三","808080@2323.com"));
        commit.setAuthorTimestamp(new Timestamp(System.currentTimeMillis()));
        mongoOperation.save(commit);
    }
}