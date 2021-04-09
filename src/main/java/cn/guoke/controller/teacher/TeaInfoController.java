package cn.guoke.controller.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.teacher.ITeaInfoService;

/**
 * @Desc 教师首页信息获取
 * @author 语录
 *
 */
@Controller
@RequestMapping("/tea/index")
@ResponseBody
public class TeaInfoController {

	@Autowired
	ITeaInfoService iService;
	
	/**
	 * @Desc 获取信息
	 * @param token
	 * @return
	 */
	@RequestMapping("/{token}")
	public Map<String, Object> getInfo(@PathVariable("token")String token){
		return iService.getTeaInfoIndex(token);
	}
	
	
	/**
	 * @Desc 获取两个月内的成绩
	 * @param token
	 * @return
	 */
	@RequestMapping("/exam/{token}")
	public Map<String, Object> getTeaLatelyExam(@PathVariable("token")String token){
		return iService.getTeaLatelyExam(token);
		
	}
	
	
}
