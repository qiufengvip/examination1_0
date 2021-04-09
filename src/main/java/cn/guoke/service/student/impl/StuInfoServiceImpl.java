package cn.guoke.service.student.impl;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Sides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.IStuTokenMapper;
import cn.guoke.mapper.student.IStuInfoMapper;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Examinationinfo;
import cn.guoke.pojo.Grade;
import cn.guoke.pojo.Student;
import cn.guoke.service.student.IStuInfoService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 学生信息获取
 * @author 语录
 *
 */
@Service
public class StuInfoServiceImpl implements IStuInfoService{

	@Autowired
	private IStuInfoMapper iStuInfoMapper;  //获取学生基本信息的 dao 层 
	
	@Autowired
	private IStuTokenMapper  iStuTokenMapper;
	
	


	/**
	 * @Desc 获取学生的基本信息
	 * @param token
	 * @return
	 */
	public Map<String, Object> getStuInfo(String token) {
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据

		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student student = iStuInfoMapper.getStuByToken(token);
		 if (student==null) {  //验证token
			return DataUtils.print(data, "-1", "请重新登录");
		 }else{		
			 //获取学生的 id
			 Integer sid = student.getSid();
			//封装学生的基本信息
			 Map<String, Object>  stuInfo = new HashMap<String, Object>(); 
			//查询数据
			stuInfo.put("stuid", student.getSid()) ; //学生 id
			stuInfo.put("realName", student.getSname()) ; //学生 的 真实姓名
			//查询数据
			List<Course> courseList = iStuInfoMapper.getCourseBySid(sid);
			//在修课程
			Integer countCourse;
			if (courseList ==null )  countCourse = 0; 
			else  countCourse = courseList.size(); 

			// 获取未通过课程 课程数		
			List<Course> courseNotList = iStuInfoMapper.getNotCourseBySid(sid);
			//未通过课程
			Integer countNotCourse;
			if (courseNotList ==null ) countNotCourse = 0; 
			else countNotCourse = courseNotList.size(); 
			//存入数据
			stuInfo.put("countCourse", countCourse) ;  //在修课程数
			stuInfo.put("countNotCourse", countNotCourse) ; //没有通过课程数

			//获取考试数
			List<Examinationinfo> examList = iStuInfoMapper.getExam(sid);
			Integer conutExam;
			if (examList==null)  conutExam=0;
			else  conutExam =examList.size();
			
			// 不及格 过 考试数
			List<Examination> examNotList =  iStuInfoMapper.getExaminationNotRe(sid);
			
			Integer conutNotExam ;
			if (examNotList==null)  conutNotExam=0;
			else  conutNotExam =examNotList.size();			
						
			//存入数据
			stuInfo.put("conutExam", conutExam) ;  //总考试数
			stuInfo.put("conutNotExam", conutNotExam) ; // 未通过考试数
			
			//ifExam 是否有未通过的考试 
			List<Examination> ifExamList = iStuInfoMapper.getExaminationNotRe(sid);
			Integer ifExam ;
			if (ifExamList==null)  ifExam=0;
			else  ifExam =ifExamList.size();		
			stuInfo.put("ifExam", ifExam);
			
			data.put("data", stuInfo);
			return DataUtils.print(data, "200", "succeed");
						
		 }

	}



	/**
	 * @Descent 获取近期的考试
	 */
	public Map<String, Object> getExamRecently(String token) {
		// TODO Auto-generated method stub、
		
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据

		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student student = iStuInfoMapper.getStuByToken(token);
		 if (student==null) {  //验证token
			return DataUtils.print(data, "-1", "请重新登录");
		 }else{
			 //获取学生的 id
			 Integer sid = student.getSid();
			 // 获取数据
			 List<Examination> examList = iStuInfoMapper.getExaminationById(sid);
			 if (examList==null) {  // 查询是有数据
				 return DataUtils.print(data, "-2", "您还没有进行过考试");
			 }else {
				
				 //获取 data 中的数据
				 List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
				 
				 //封装返回的数据 
				 for(Examination examination : examList){ //遍历数据
					   Map<String, Object> recentExam = new HashMap<String, Object>(); //创建返回的数据
					   recentExam.put("examId", examination.getExaminationmark()); //考场号					   
					   recentExam.put("time", DataUtils.stampToDate(examination.getBegintime())); // 开考时间					   
					   recentExam.put("teacher", iStuInfoMapper.getTeacher(examination.getTid()).getTname()); // 老师姓名		
					   //获取成绩 
					   Grade grade = iStuInfoMapper.getgradeById(examination.getPaperid());
					   String myGrade;
					   if (grade==null) {
						   myGrade="成绩还未生成";
					   }else {
						   myGrade=grade.getScore();
					   }
					   recentExam.put("grade", myGrade);  
					   Integer isqs = Integer.parseInt(examination.getState());
					   if (isqs>0) recentExam.put("isQualified", "已通过");
					   else  recentExam.put("isQualified", "未通过");
					  
					   dataList.add(recentExam);
				 }
				 
				 data.put("data", dataList);
				 
				 return DataUtils.print(data, "200", "succeed");
				 
			 }
			 
		 }

	}


	
	
	
	
	
}
