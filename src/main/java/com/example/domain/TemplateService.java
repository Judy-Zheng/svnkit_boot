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
package com.example.domain;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.StringWriter;

/**
 * 模板服务
 *  1) 依据模板生成字符
 * @author how
 * @date 17/4/22
 */
public class TemplateService<T> {

    private String templateName;

    private T data;
    private Configuration configuration;
    private Template template;

    public TemplateService(@NotNull  String templateName,@NotNull T data) throws IOException {
        this.templateName = templateName;
        this.data = data;
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(getClass(),"/templates/");
        configuration.setOutputEncoding("UTF-8");
        template = configuration.getTemplate(templateName,"UTF-8");
        template.setEncoding("UTF-8");
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws TemplateException
     */
    public String process() throws IOException, TemplateException {
        StringWriter out = new StringWriter();
        template.process(data,out);
        return out.toString();
    }
}
