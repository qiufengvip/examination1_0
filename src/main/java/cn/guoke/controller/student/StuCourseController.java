package cn.guoke.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.student.IStuCourseService;

/**
 * @Descent 学生课程的操作 
 * @author 语录
 *
 */
@Controller
@RequestMapping("/student/course")
@ResponseBody
public class StuCourseController {

	@Autowired
	private IStuCourseService iStuCourseService;
	
	
	@RequestMapping("/all/{token}")
	public Map<String, Object> getAllCourse(@PathVariable("token")String token){
		return iStuCourseService.getAllCourse(token);
	}
	
	@RequestMapping("/add/{token}")
	public Map<String, Object> addCourse(@PathVariable("token")String token,Integer id){
		return iStuCourseService.addCourse(token, id);
	}
	
	@RequestMapping("/mycourse/{token}")
	public Map<String, Object> getMyCourse(@PathVariable("token")String token){
		return iStuCourseService.getMyCourse(token);
	}
	
	
	
}
