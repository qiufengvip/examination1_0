package cn.guoke.controller.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.mapper.student.IStuExamInfoMapper;
import cn.guoke.service.teacher.ITeaCoursService;
import cn.guoke.service.teacher.ITeacherCourseService;

/**
 * @Desc 教师的控制层
 * @author 语录
 *
 */
@Controller
@RequestMapping("/tea/course")
@ResponseBody
public class TeaCourseConroller {

	@Autowired
	private ITeacherCourseService iService;
	@Autowired
	private ITeaCoursService iTeaCoursService;
	
	// 获取基本信息
	@RequestMapping("/{token}")
	public Map<String, Object> getCourseInfo(@PathVariable("token")String token){
		return iService.getCourseInfo(token);
	}
	
	
	@RequestMapping("/my/{token}")
	public Map<String, Object> getMyCourse(@PathVariable("token")String token,Integer cid){
		return iService.getMyCourse(token, cid);
	}
	
	
	// 获取创建的所有课程
	@RequestMapping("/m/{token}")
	public Map<String, Object> getAllCourse(@PathVariable("token")String token){
		return iService.getAllCourse(token);
	}
	
	
	/**
	 * @Desc 添加课程
	 * @param token
	 * @param name
	 * @return
	 */
	@RequestMapping("/add")
	public Map<String, Object> addCourse(String token, String name){
		return iTeaCoursService.addCourse(token, name);
	}
	
	
}
