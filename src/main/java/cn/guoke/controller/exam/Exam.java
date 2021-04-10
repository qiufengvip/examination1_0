package cn.guoke.controller.exam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.guoke.service.exam.IExamService;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;

/**
 * @Descent 获取考试信息
 * @author 语录
 *
 */

@Controller
@RequestMapping("/exam")
@ResponseBody
public class Exam {
	
	@Autowired
	private IExamService  iExamService;
	
	
	/**
	 * @Desc获取试卷信息
	 * @param pid
	 * @return
	 */
	public Map< String, Object> getStuExam(Integer pid){
		
		return null;
	}

	@RequestMapping("/")
	public Map<String, Object> setPar(String token,String tips){

		return iExamService.setPar(token,tips);
	}

	@RequestMapping("/parper")
	public  Map<String, Object> getExamAcc(String token, String parpercode){
		return iExamService.getExamAcc(token, parpercode);
	}
}
