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

import java.util.List;

/**
 * 周报模板-模型
 * @author how
 * @date 17/4/22
 */
public class ReportModel {
    private String beginDay;
    private String endDay;

    private String committer;

    private List<ItemModel> list;



    public String getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(String beginDay) {
        this.beginDay = beginDay;
    }



    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
    }

    public String getCommitter() {
        return committer;
    }

    public void setCommitter(String committer) {
        this.committer = committer;
    }

    public List<ItemModel> getList() {
        return list;
    }

    public void setList(List<ItemModel> list) {
        this.list = list;
    }
}
