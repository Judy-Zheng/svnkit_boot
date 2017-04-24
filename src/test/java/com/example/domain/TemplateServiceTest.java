package com.example.domain;

import com.example.domain.entity.ItemModel;
import com.example.domain.entity.ReportModel;
import freemarker.template.TemplateException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
 * @date 17/4/22
 */
public class TemplateServiceTest {

    @Test
    public void process() throws Exception {
        TestObject t = new TestObject();
        t.setName(1f+"张三");
        TemplateService<TestObject> ts = new TemplateService<TestObject>("test.ftl",t);
        String result = ts.process();
        System.out.println(new String(result.getBytes("UTF-8")));
        Assert.assertTrue(result.indexOf(t.getName())!=-1);
    }

    @Test
    public void testReport() throws IOException, TemplateException {
        ReportModel r = new ReportModel();
        r.setBeginDay("2017-01-91");
        r.setEndDay("2017-01-01");
        r.setCommitter("张三");
        List<ItemModel> list = new ArrayList<>();
        list.add(new ItemModel("1","的水水水水是"));
        list.add(new ItemModel("2","测试啥啥啥啥啥啥"));
        r.setList(list);
        TemplateService<ReportModel> ts = new TemplateService<ReportModel>("report.ftl",r);
        String result = ts.process();
        System.out.println(result);
    }
}