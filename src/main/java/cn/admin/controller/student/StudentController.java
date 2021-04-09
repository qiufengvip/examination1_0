package cn.admin.controller.student;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.admin.service.student.IStudentService;
import cn.guoke.pojo.Student;

/**
 * 学生信息管理
 * @author 梦伴
 *
 */
@Controller
@RequestMapping("/admin/student")
@ResponseBody
public class StudentController {
	
	@Autowired
	private IStudentService studentServiceImpl;
	
	@RequestMapping("/queryAllStudent/{token}")
	public Map<String, Object> queryAllStudent(@PathVariable("token") String token){
		return studentServiceImpl.queryAllStudent(token);
	}
	
	@RequestMapping("/changeStudent/{token}")
	public Map<String, Object> changeStudent(@PathVariable("token") String token,Student student){
		return studentServiceImpl.changeStudent(token,student);
	}

	
}
