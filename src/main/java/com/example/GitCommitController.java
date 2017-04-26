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
package com.example;

import com.example.domain.entity.Commit;
import com.example.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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
 * @date 17/4/16
 */
@RestController
@RequestMapping(value="/commit")
public class GitCommitController {

    @Autowired
    private CommitRepository commitRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void saveNewCommit(@RequestBody List<Commit> commitList){
        commitRepository.saveCommit(commitList);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Commit> queryCommitByUser(@RequestParam  String userId,@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate){
        return commitRepository.findCommitByUser(userId,startDate);
    }
}
