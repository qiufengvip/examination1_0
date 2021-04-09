package cn.guoke.service.teacher.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.ITeaTokenMapper;
import cn.guoke.mapper.teacher.IteaSourseMapper;
import cn.guoke.pojo.Class;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;
import cn.guoke.service.teacher.ITeacherCourseService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 获取所有的课程
 * @author 语录
 *
 */
@Service
public class TeacherCourseServiceImpl implements ITeacherCourseService{

	@Autowired
	private ITeaTokenMapper iTeaTokenMapper;
	
	@Autowired
	private IteaSourseMapper iSourseMapper;
	
	/**
	 * @Desc 获取课程的基本信息
	 */
	public Map<String, Object> getCourseInfo(String token) {
		// TODO Auto-generated method stub
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
           Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }else{
			 //获取基本信息
			 Map<String, Object> teaInfo = new HashMap<String, Object>();
			 List<Course> myCourse = iSourseMapper.getMyAllCreate(teacher.getTid());
			 teaInfo.put("cnumber", myCourse==null?0:myCourse.size()); //课程数
			  List<Student> students = iSourseMapper.getStudnetByTid(teacher.getTid());
			 teaInfo.put("snumber", students==null?0:students.size()); //学生人数
			 data.put("data", teaInfo);
		 }
		
		return DataUtils.print(data);
	}

	/**
	 * @Descent 获取课程下所有的学生
	 */
	public Map<String, Object> getMyCourse(String token, Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @Descent 获取所有的课程
	 */
	
	public Map<String, Object> getAllCourse(String token) {
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
           Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }else{
			 // 获取所有的课程
			  List<Course> courses = iSourseMapper.getMyAllCreate(teacher.getTid());
			  if (courses==null||courses.size()==0) {
				return DataUtils.print(data, "-2", "您还没有课程，请您快去创建一个吧 ！！");
			  }
			  List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
			  for(Course course : courses){  //封装数据
					 Map<String, Object> courseMap = new HashMap<String, Object>();
					 
					 courseMap.put("id",course.getCid() ); //课程id
					 courseMap.put("name", course.getCname()); // 课程名称
					 courseMap.put("createTime", DataUtils.stampToDate(course.getCreatetime())); //创建时间
					 dataList.add(courseMap);
			  }
			  data.put("data", dataList);
			  return DataUtils.print(data, "0", "succeed");

		 }
	}
	
	
	

}
