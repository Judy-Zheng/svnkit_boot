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
package com.example.domain.entity;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Purpose.
 *
 * A description of why this class exists.  
 *   For what reason was it written?  
 *   Which jobs does it perform?
 * {@code DataAccessException} using 
 * @author how
 * @date 17/4/17
 */
@Document(collection = "commits")
public class Commit {
    private String displayId;
    private String id;
    private Author author;
    private Date authorTimestamp;
    private String message;
    private List<?> parents = new ArrayList<>();

    private Map<String,String> properties;

    private Repository repository;

    public String getDisplayId() {
        return displayId;
    }

    public void setDisplayId(String displayId) {
        this.displayId = displayId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getAuthorTimestamp() {
        return authorTimestamp;
    }

    public void setAuthorTimestamp(Date authorTimestamp) {
        this.authorTimestamp = authorTimestamp;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getParents() {
        return parents;
    }

    public void setParents(List<?> parents) {
        this.parents = parents;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
