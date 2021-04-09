package cn.guoke.service.exam.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;

import cn.guoke.mapper.common.ITeaTokenMapper;
import cn.guoke.mapper.exam.IExamInfoMapper;
import cn.guoke.mapper.student.IStuExamInfoMapper;
import cn.guoke.pojo.Mcq;
import cn.guoke.pojo.Paper;
import cn.guoke.pojo.Paperinfo;
import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.Topic;
import cn.guoke.service.exam.IExamService;
import cn.guoke.utils.DataUtils;
import cn.guoke.utils.SnowflakeUtils;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @Desc 获取考试的信息 
 * @author 语录
 *
 */
@Service
public class ExamService implements IExamService{

	@Autowired
	private IExamInfoMapper iExamInfoMapper;
	
	@Autowired
	private ITeaTokenMapper iTeaTokenMapper;

	public Map<String, Object> getExamPar(Integer pid) {
		// TODO Auto-generated method stub
		
		
		
		return null;
	}

	/**
	 * @Desc 这个是试卷的id 
	 */
	public Map<String, Object> getExamAcc(String token, String parpercode) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")||parpercode==null) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取教师信息
         Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 
		 //获取试卷标题信息
		  Paper paper =  iExamInfoMapper.getPaperByCode(parpercode);
		   
		  Integer pid = paper.getPaperid();
		 //创建
		 Map<String, Object> dataP = new HashMap<String, Object>(); //创建返回的数据
		 Map<String, Object> paperInfo = new HashMap<String, Object>(); //创建返回的数据
		 paperInfo.put("title", paper.getName());  // 试卷
		 paperInfo.put("info", paper.getInfo());  // 试卷信息
		 dataP.put("paperInfo", paperInfo);
		 
		 
		 
		 //根据试卷 id 获取题目 
		  List<Paperinfo> paperInfoList = iExamInfoMapper.getPaperInfoByPid(pid);

		  List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();  //封装说有的试卷
		  for (Paperinfo paperinfo : paperInfoList) {
				Map<String, Object> dataMap = new HashMap<String, Object>(); //创建返回的数据 题的信息

			   //获取 题的内容
			    Integer topicid = paperinfo.getTopicid();
			    Topic topic = iExamInfoMapper.getTopicByID(topicid);
			    String topid = topic.getTopid(); //题的id 
			    String type = topic.getType();
			    if (type.equals("1")) {  //题的类型 选择题
					// 获取题目内容
			    	Mcq mcp = iExamInfoMapper.getMcq(topid);
			    	dataMap.put("type", "radio");  // 题的类型
			    	dataMap.put("title",mcp.getMcqcon()); // 题目
			    	dataMap.put("id", mcp.getMcqid());  // 题的id
			    	dataMap.put("fraction", topic.getScore()); // 题的分数
			    	
			    	
			    	 List<Map<String, Object>> content = new ArrayList<Map<String,Object>>();  //创建返回的数据 题的信息
			    	 content.add(getContentS(mcp.getAnswer().equals("A")?"true":"false", mcp.getMcqid(), mcp.getA()));
			    	 content.add(getContentS(mcp.getAnswer().equals("B")?"true":"false", mcp.getMcqid(), mcp.getB()));
			    	 if (!mcp.getC().equals("no")) {  // 验证是否有选项 c
				    	 content.add(getContentS(mcp.getAnswer().equals("C")?"true":"false", mcp.getMcqid(), mcp.getC()));
			    		 if (!mcp.getD().equals("no")) {  // 验证是否有选项 D
					    	 content.add(getContentS(mcp.getAnswer().equals("D")?"true":"false", mcp.getMcqid(), mcp.getD()));
						 }
					 }
			    	 
			    	 
			    	
			    	
				}else if (type.equals("4")) {
					
				}
			    
			   dataList.add(dataMap);  
		  }
		 
		 
		
		  dataP.put("topics", dataList);
		 
		
		
		return null;
	}

	public Map<String, Object> getStuExamPid(String token, Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getTeaExamPid(String token, Integer pid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> getCorrect(String token, String data) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> setExam(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Object> setTeaTip(String token, String tipid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Descent 给试卷添加信息 
	 */
	public Map<String, Object> setPar(String token, String tips) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取教师信息
         Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 //解析 tips 
		 // 将接收到的 json 转为对象 
		 JSONObject piper = JSONUtil.parseObj(tips);
		 // 获取试卷信息
		 JSONObject paperInfo = JSONUtil.parseObj(piper.get("paperInfo"));
		 // 获取试卷名称
		  String name = paperInfo.get("title").toString();
		  //获取试卷信息
		  String info = paperInfo.get("info").toString();
		  Paper paper = new Paper();
		  paper.setTid(teacher.getTid());
		  paper.setCreatetime(DataUtils.getDataTime().toString());
		  paper.setName(name);
		  paper.setInfo(info);
		  String papercode = SnowflakeUtils.getSnowflake()+"";
		  paper.setPapercode(papercode);;
		  //创建试卷 
		  iExamInfoMapper.addPaper(paper);
		  // 获取试卷的id
		  Paper myPaper= iExamInfoMapper.getPaperByCode(papercode);
		  Integer id = myPaper.getPaperid();  //试卷的id
		  ArrayList<String> tipidList = new ArrayList<String>();  //存入题的 id
		  
		  //获取题目
		   JSONArray topics = JSONUtil.parseArray(piper.get("topics").toString());
		   for(int i=0;i<topics.size();i++) {
			   JSONObject top = topics.getJSONObject(i); //获取题目对象
			   
			   if (top.get("type").equals("radio")) { // 单选题 
				   //解析单选题
				  // 1. 获取题目内容 
				   String mcqcon = top.get("title").toString();
				  // 2. 获取  分值
				   String fraction =top.get("fraction")==null?"5":top.get("fraction").toString();
				  // 3. 获取选项
				   JSONArray content = JSONUtil.parseArray(top.get("content").toString());
				   
				   // 获取题的  id
				   String topid = SnowflakeUtils.getSnowflake()+"";
				   tipidList.add(topid);
				   if (content.size()==2) {  //选项数量判断
					     //获取题目对象
						  String A = content.getJSONObject(0).get("title").toString();
						  String B = content.getJSONObject(1).get("title").toString();
						  String C = "no";
						  String D = "no";

						  System.out.println(topics.getJSONObject(0));
						  System.out.println(topics.getJSONObject(1));
						  System.out.println(topics.getJSONObject(0));
						  iExamInfoMapper.addMcq(topid, mcqcon, A, B, C, D, content.getJSONObject(0).get("checked").toString().equals("true")?"A":"B");

				  }else if(content.size()==3){
					     //获取题目对象
						  String A = content.getJSONObject(0).get("title").toString();
						  String B = content.getJSONObject(1).get("title").toString();
						  String C = content.getJSONObject(2).get("title").toString();
						  String D = "no";	
						  String answer = "";
						  if (content.getJSONObject(0).get("checked").toString().equals("true")) {  // 获取答案
							answer="A";
						  }else if(content.getJSONObject(1).get("checked").toString().equals("true")){
							  answer="B";
						 }else {
							 answer="C";
						 }
						  iExamInfoMapper.addMcq(topid, mcqcon, A, B, C, D, answer);
  
						  
				  }else{
					  String A = content.getJSONObject(0).get("title").toString();
					  String B = content.getJSONObject(1).get("title").toString();
					  String C = content.getJSONObject(2).get("title").toString();
					  String D = content.getJSONObject(3).get("title").toString();
					  
					  String answer = "";
					  if (topics.getJSONObject(0).get("checked").toString().equals("true")) {  // 获取答案
						answer="A";
					  }else if(content.getJSONObject(1).get("checked").toString().equals("true")){
						  answer="B";
					 }else if(content.getJSONObject(2).get("checked").toString().equals("true")){
						 answer="C";
					 }else {
						 answer="D";
					 }
					  iExamInfoMapper.addMcq(topid, mcqcon, A, B, C, D, answer);
				  }
				  
				   
				 // 给题库存入数据
				 
				   
				 iExamInfoMapper.addTopic(topid,"1",teacher.getTid(),fraction,DataUtils.getDataTime()+"");  
				   
				  
				  
				    
			   }else {   //这里是解答题
				   	
				   // 获取解答题
					// 1. 获取题目内容 
				   String mcqcon = top.get("title").toString();
				  // 2. 获取  分值
				   String fraction =top.get("fraction")==null?"5":top.get("fraction").toString();
				   //获取答案 
				   String content =top.get("content")==null?"no":top.get("content").toString();
				   
				   String topid = SnowflakeUtils.getSnowflake()+"";
				   tipidList.add(topid);
				   iExamInfoMapper.addFaq(topid, mcqcon, content);
				   //存入题库
				   iExamInfoMapper.addTopic(topid, "4", teacher.getTid(), fraction, DataUtils.getDataTime()+"");				   
				   				   
			 }
		      
		   }
		//存完题库 后要生成试卷
		
		//根据 题 topid 获取 tioicid
		 for (String topid : tipidList) {

			 Topic topic = iExamInfoMapper.getTopidById(topid);
			 Integer topicid = topic.getTopicid(); //获取 topicid	 
			 //生成试卷
			 iExamInfoMapper.addPaperinfo(id, topicid);
		 }  
		   
		return DataUtils.print(data);
	}
	
	
	
	public static void main(String[] args) {
		String data = "[{s:'1',b:true},{s:'2',b:false}]";
		JSONArray content = JSONUtil.parseArray(data);
		System.out.println(content.size());
		for (int i = 0; i < content.size(); i++) {
			System.out.println(JSONUtil.parseObj(content.get(1)).get("b").toString().equals("true"));
		}
	}
	
	
	
	public  Map<String, Object> getContentS(String checked,String id, String title){
   	 Map<String, Object> contentMap = new HashMap<String, Object>(); //创建返回的数据 题的信息	
   	 contentMap.put("checked", checked);
   	 contentMap.put("id", id);
   	 contentMap.put("title", title);
   	 return contentMap;
	}
	
}
