package cn.guoke.service.student.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.IStuTokenMapper;
import cn.guoke.mapper.exam.IExamInfoMapper;
import cn.guoke.mapper.student.IStuExamInfoMapper;
import cn.guoke.mapper.student.IStuExamMapper;
import cn.guoke.mapper.student.IStuGetMapper;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Examinationinfo;
import cn.guoke.pojo.Grade;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.service.student.IStuExamService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 获取实现学生的接口
 * @author 语录
 *
 */
@Service
public class StuExamServiceImpl implements IStuExamService{

	@Autowired
	private IStuExamMapper iStuExamMapper;
	
	@Autowired
	private IStuExamInfoMapper iStuExamInfoMapper;
	
	@Autowired
	private IStuTokenMapper iStuTokenMapper;
	
	@Autowired
	private IStuGetMapper iStuGetMapper;
	
	@Autowired
	private IExamInfoMapper exammapepr;
	
	/**
	 * @Desc 获取等待答题的试卷
	 */
	public Map<String, Object> getReplyExam(String token) {
		// TODO Auto-generated method stub
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 
		  List<Course> courseList = iStuGetMapper.getMyCourse(studnet.getSid());  //获取课程信息
		  if (courseList==null||courseList.size()==0) {   //验证是否有加入课程 没有加入课程没有考试
			  return DataUtils.print(data, "-2", "宁还未参加过考试");
		  }else {
			  
			   List<Map<String, Object>> dataList = new  ArrayList<Map<String,Object>>(); //创建返回的数据
			  //获取老师创建的所有考试
			   for(Course course : courseList){
				   
				    List<Examination> examList = iStuExamInfoMapper.getExamById(course.getTid()); //获取老师创建的所有考试
				    
				    //获取
				    for(Examination examination : examList){

						 // 获取考场信息
				    	  Map<String, Object> dataMap = new HashMap<String, Object>(); // 封装数据
						  dataMap.put("examinationmark", examination.getExaminationmark()); //考场号
						  //查询教考老师 
						   Teacher teacher = iStuGetMapper.getTeacherById(examination.getTid());
						  dataMap.put("teacher", teacher.getTname()); //监考老师
						  dataMap.put("createTiem", DataUtils.stampToDate(examination.getCreatetime())); //创建的时间
						  dataMap.put("begintime", DataUtils.stampToDate(examination.getBegintime())); //开始时间
						  dataMap.put("id", examination.getExaminationid()); //考场id
						  dataList.add(dataMap);
				    }
				    
				    
			   }
			  data.put("data", dataList);
			  
			  return DataUtils.print(data, "0", "succeed");
		}
		 
		 

	}

	/***
	 * @Descent 获取待答的试卷
	 */
	public Map<String, Object> getSeccExam(String token) {
		// TODO Auto-generated method stub
		
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 
		//获取学生答题过的试卷
		 List<Examinationinfo> examInfoList = iStuExamInfoMapper.getExaminfoBySid(studnet.getSid());
		 if (examInfoList==null||examInfoList.size()==0) {  //验证是否参加过考试
			return DataUtils.print(data, "-2", "宁还未参加过考试");
		 }else{
			 
			 //封装数据
			 List<Map<String, Object>> dataList = new  ArrayList<Map<String,Object>>();
			 for(Examinationinfo examinationinfo : examInfoList){
				 // 获取考场信息
				 Map<String, Object> dataMap = new HashMap<String, Object>(); // 封装数据
				  Examination exam = iStuExamMapper.getExamInfoByIDY(examinationinfo.getExaminationid());
				  if (exam==null) {
					 continue;
				  }
				  dataMap.put("examinationmark", exam.getExaminationmark()); //考场号
				  //查询教考老师 
				   Teacher teacher = iStuGetMapper.getTeacherById(exam.getTid());
				  dataMap.put("teacher", teacher.getTname()); //监考老师
				  dataMap.put("createTiem", DataUtils.stampToDate(exam.getCreatetime())); //创建的时间
				  dataMap.put("begintime", DataUtils.stampToDate(exam.getBegintime())); //开始时间
				  dataMap.put("id", exam.getExaminationid()); //考场id
				   
				  //获取成绩
				  Grade grade = iStuExamMapper.getStuScoure(studnet.getSid(), exam.getPaperid());
				  
				  dataMap.put("scoure", grade==null?"成绩还为生成":grade.getScore()); //学生成绩
				 //存入数据
				 dataList.add(dataMap);
				 
			 }
			 
			 data.put("data", dataList);
			 return DataUtils.print(data,"0","succeed");
		 }
	}

	
	public Map<String, Object> getExamInfo(String token, Integer examid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Descent 获取考前注意事项
	 */
	public Map<String, Object> getAnnouncements(Integer id) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据

		if (id==null) {  //验证参数是否为空
			return DataUtils.print(data,"-1","请输入参数");
		}else {
			Examination exma = exammapepr.getAnnouncements(id);
			Map<String, Object> dataMap = new HashMap<String, Object>(); //创建返回的数据
			if (exma==null) { // 验证考试是否存在
				return DataUtils.print(data,"-2","没有该考试");
			}else {
				 String annn = exma.getAnnouncements();
				 dataMap.put("announcements", annn);
				 data.put("data", dataMap);
				 return DataUtils.print(data);
			}
		}

	}

}
