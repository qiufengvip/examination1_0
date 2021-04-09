package cn.guoke.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.student.IStuClassService;

/**
 * @Desc 对班级获取的控制层
 * @author 语录
 *
 */
@Controller
@RequestMapping("/student/class")
@ResponseBody
public class StuClassController {

	@Autowired
	private IStuClassService iStuClassService;
	
	@RequestMapping("/add/{token}")
	public Map<String, Object> addClass(@PathVariable("token")String token,String code){
		return iStuClassService.addClass(token, code);
		
	}
	@RequestMapping("/{token}")
	public Map<String, Object> getClass(@PathVariable("token")String token){
		return iStuClassService.getClass(token);
	}
	
}
