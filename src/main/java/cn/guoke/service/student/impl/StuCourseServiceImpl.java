package cn.guoke.service.student.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean2a;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.IStuTokenMapper;
import cn.guoke.mapper.student.IStuGetMapper;
import cn.guoke.mapper.student.IStuSetMapper;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.CourseInfo;
import cn.guoke.pojo.Student;
import cn.guoke.service.student.IStuCourseService;
import cn.guoke.utils.DataUtils;

@Service
public class StuCourseServiceImpl implements IStuCourseService{

	@Autowired
	private IStuSetMapper iStuSetMapper;
	
	@Autowired
	private IStuGetMapper iStuGetMapper;

	@Autowired
	private IStuTokenMapper iStuTokenMapper;
	
	
	/**
	 * @Desc 获取所有的课程  这个是为 layui 框架准备的
	 */
	public Map<String, Object> getAllCourse(String token) {
		
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
		 
		 /***********分割线*************/
		 
		 //查询所有的课程
		  List<Course> courseList = iStuGetMapper.getCourseBySchoolId(studnet.getSchoolid());
		  if (courseList==null||courseList.size()==0) { // 验证课程是否为空
			  return DataUtils.print(data, "-3", "很遗憾,该校还没有课程");
		  }else {
			  //分装数据 
			 List<Map<String, Object>> dataLsit =  new  ArrayList<Map<String,Object>>();  //创建封装的数据
			 
			 // 这里主要有 课程的名称 创建课程的教师  加入课程的时间  课程创建的时间  创建的课程的教师姓名 课程的 id
			 for (Course course : courseList) {
				//创建返回的 Map
				Map<String, Object> courseMap = new HashMap<String, Object>(); //创建返回的数据
				courseMap.put("id", course.getCid());  //课程的id
				courseMap.put("tname", iStuGetMapper.getTeacherById(course.getTid()).getTname()); // 教师的名字
				courseMap.put("createTime", DataUtils.stampToDate(course.getCreatetime())); // 课程创建的时间
				courseMap.put("cname", course.getCname());   // 课程的名称
				courseMap.put("count", iStuGetMapper.getAllCourseInfo(course.getCid()).size());  //加入课程的人数				
				
				//加入数据 
				dataLsit.add(courseMap);
			 }
			 data.put("data", dataLsit);
			  return DataUtils.print(data,"0","succeed");
		  }

	}

	
	/**
	 * @Descent 获取正在修的课程
	 */
	public Map<String, Object> getMyCourse(String token) {
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
		 
		 /***********分割线*************/	 
		 
		 // 获取正在修的课程
		  List<Course> courseList = iStuGetMapper.getMyCourse(studnet.getSid());
		  
		  if (courseList==null||courseList.size()==0) {
			  return DataUtils.print(data, "-3", "您还没有加入任何课程");
		  }else {
		
				  //分装数据 
				   List<Map<String, Object>> dataLsit =  new  ArrayList<Map<String,Object>>();  //创建封装的数据
					 
					 // 这里主要有 课程的名称 创建课程的教师  加入课程的时间  课程创建的时间  创建的课程的教师姓名 课程的 id
				   for (Course course : courseList) {
						//创建返回的 Map
						Map<String, Object> courseMap = new HashMap<String, Object>(); //创建返回的数据
						courseMap.put("id", course.getCid());  //课程的id
						courseMap.put("tname", iStuGetMapper.getTeacherById(course.getTid()).getTname()); // 教师的名字
						courseMap.put("createTime", DataUtils.stampToDate(course.getCreatetime())); // 课程创建的时间
						courseMap.put("time", DataUtils.stampToDate(iStuGetMapper.getCourseInfo(course.getCid(), studnet.getSid()).getCreatetime())); // 加入课程的时间
						courseMap.put("cname", course.getCname());   // 课程的名称
						courseMap.put("count", iStuGetMapper.getAllCourseInfo(course.getCid()).size());  //加入课程的人数				
						
						//加入数据 
						dataLsit.add(courseMap);
					 }
					 data.put("data", dataLsit);
					return DataUtils.print(data,"0","succeed");
			  }
	
	

	}

	/**
	 * @Descent 添加课程
	 */
	public Map<String, Object> addCourse(String token, Integer cid) {
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")||cid==null||cid.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 //验证是否已经加入了该课程
		 CourseInfo courseinfo= iStuGetMapper.getCourseInfo(cid, studnet.getSid());
		 if (courseinfo!=null) {
			return DataUtils.print(data, "-2", "您已经加入该课程了");
		}
		int count = iStuSetMapper.addSouser(cid, studnet.getSid(), DataUtils.getDataTime()+"");
		if (count>0) {  // 是否加入成功 
			return DataUtils.print(data, "200", "succeed");
		}else {
			return DataUtils.print(data, "-1", "加入失败");
		}

	}

	/**
	 * @Descent 分页获取所有的课程  这个现在还不需要  预留接口
	 */
	public Map<String, Object> getCourseByPage(String token, Integer paeg, Integer size) {
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
		 
		 
		 
		return null;
	}
	
	
	
}
