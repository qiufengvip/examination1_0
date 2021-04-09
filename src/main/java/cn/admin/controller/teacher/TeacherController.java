package cn.admin.controller.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.admin.service.teacher.ITeacherService;
import cn.guoke.pojo.Teacher;

@ResponseBody
@Controller
@RequestMapping("/admin/teacher")
public class TeacherController {
	
	@Autowired
	private ITeacherService teacherServiceImpl;
	
	@RequestMapping("/queryAllTeacher/{token}")
	public Map<String, Object> queryAllTeacher(@PathVariable("token") String token){
		return teacherServiceImpl.queryAllTeacher(token);
	}
	
	@RequestMapping("/changeTeacher/{token}")
	public Map<String, Object> changeTeacher(@PathVariable("token") String token,Teacher teacher){
		return teacherServiceImpl.changeTeacher(token,teacher);
	}
}
