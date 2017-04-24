package com.example.repository;

import com.example.domain.entity.Author;
import com.example.domain.entity.Commit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose.
 * <p>
 * A description of why this class exists.
 * For what reason was it written?
 * Which jobs does it perform?
 * {@code DataAccessException} using
 *
 * @author how
 * @date 17/4/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommitRepositoryTest {

    @Autowired
    private  CommitRepository commitRepository;

    @Test
    public void saveCommit() throws Exception {
        List<Commit> list = new ArrayList<>();
        Commit commit = new Commit();
        commit.setAuthor(new Author("张三","808080@2323.com"));
        commit.setAuthorTimestamp(new Timestamp(System.currentTimeMillis()));
        list.add(commit);
        commitRepository.saveCommit(list);
    }

    @Test
    public void findCommitByUser() throws Exception {
        List<Commit> result = commitRepository.findCommitByUser("how");
        System.out.println(result);
    }
}