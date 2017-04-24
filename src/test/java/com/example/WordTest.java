package com.example;

import com.example.repository.MongoDBConnection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @todo ��ȡ���ڡ��ύ�˼���־��Ϣ�Զ������ܱ�word�ĵ�
 * @author mulan
 * @time 2017��4��12��
 */
public class WordTest {
	private Configuration configuration = null;
	
	public WordTest(){
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
	}
	
	public static void main(String[] args){
		WordTest test = new WordTest();
		Map<String,Object> dataMap = new HashMap<String,Object>();
		test.getData(dataMap);
		String downloadType = "wordModel.ftl";
		String savePath = "D:/MinshengProject/weeklypapers/�����ܱ�.doc";
		test.createWord(dataMap,downloadType,savePath);
	}
	
	public void createWord(Map<String, Object> dataMap,String downloadType,String savePath){
		//������Ҫװ���ģ��
		Template template = null;
		try {
			//����ģ���ļ�
			configuration.setClassForTemplateLoading(this.getClass(), "/com/mongodb/cl/util");
			//���ö����װ��
//			configuration.setObjectWrapper(new DefaultObjectWrapper());
			//�����쳣������
//			configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
			//����Template����,ע��ģ��������downloadTypeҪһ��
			template = configuration.getTemplate(downloadType);
			//����ĵ�
			File outFile = new File(savePath);
			Writer out = null;
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile),"UTF-8"));
			template.process(dataMap, out);
			outFile.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getData(Map<String,Object> dataMap){
		try{
			dataMap.put("beginMonth", 04);
			dataMap.put("beginDay", 06);
			dataMap.put("endMonth", 04);
			dataMap.put("endDay", 13);
	
			MongoCursor<Document> committerCursor = getMongoCursor("committer");
			String name = "";
			while(committerCursor.hasNext()){
				Document committer = committerCursor.next();
				String addr = committer.getString("addr");
		        if("yuxuebing".equals(addr)){
		        	name = committer.getString("name");
		        }
			}
			dataMap.put("committer", name);
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			MongoCursor<Document> logCursor = getMongoCursor("git_logs");
			Integer i = 1;
			while(logCursor.hasNext()){
				Map<String,Object> map = new HashMap<String,Object>();
				Document gitLog = logCursor.next();
				String pName = gitLog.getString("name");
				String log = "";
		        if("yuxuebing".equals(pName)){
		        	log = gitLog.getString("log");
					map.put("number", i);
					map.put("content", log);
					i++;
					list.add(map);
		        }
			}
			for(i=0;i<list.size();i++){
				System.out.println(list.get(i).toString());
			}
			dataMap.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @todo ��ѯ����collectionName�����ĵ�����ȡ������
	 * @author mulan
	 * @time 2017��4��11��
	 */
	private MongoCursor<Document> getMongoCursor(String collectionName){
		MongoDBConnection conn = new MongoDBConnection();
		MongoCollection<Document> collection = conn.getCollection(collectionName);
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		return mongoCursor;
	}
}
