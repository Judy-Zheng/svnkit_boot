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
 * @date 17/4/22
 */
public class ItemModel {
    private String number;
    private String content;

    public ItemModel(String number, String content) {
        this.number = number;
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
