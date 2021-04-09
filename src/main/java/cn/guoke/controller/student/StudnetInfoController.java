package cn.guoke.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.student.IStuInfoService;

/**
 * @Desc 学生的控制层
 * @author 语录
 *
 */
@Controller
@RequestMapping("/student")
@ResponseBody
public class StudnetInfoController {

	
	@Autowired
	private IStuInfoService iStuInfoService;
	
	// 获取首页学生信息
	@RequestMapping("/stuinfo")
	public Map<String, Object> getInfo(String token){
		return iStuInfoService.getStuInfo(token);
	}
	
	@RequestMapping("/recentExam")
	public Map<String, Object> getRecentExam(String token){
		return iStuInfoService.getExamRecently(token);
	}
	
	
	
	
}
