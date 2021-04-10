package cn.guoke.controller.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.teacher.ILibService;

/**
 * @Desc 获取题库
 * @author 语录
 *
 */
@Controller
@RequestMapping("/lib")
@ResponseBody
public class LibController {

	@Autowired
	private ILibService iLibService;
	/**
	 * @Desc 
	 * @return
	 */
	@RequestMapping("/")
	public Map<String, Object> getLib(){
		return iLibService.getLiib();
	}
	
	
}
