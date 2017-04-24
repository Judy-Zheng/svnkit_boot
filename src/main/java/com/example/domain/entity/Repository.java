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
public class Repository {
    private boolean forkable;
    private String hierarchyId;
    private String id;
    private String name;
    private Project project;

    private String scmId;

    public boolean isForkable() {
        return forkable;
    }

    public void setForkable(boolean forkable) {
        this.forkable = forkable;
    }

    public String getHierarchyId() {
        return hierarchyId;
    }

    public void setHierarchyId(String hierarchyId) {
        this.hierarchyId = hierarchyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getScmId() {
        return scmId;
    }

    public void setScmId(String scmId) {
        this.scmId = scmId;
    }
}
