package cn.guoke.controller.exam;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.exam.IExamService;


@Controller
@RequestMapping("/stu")
@ResponseBody
public class StuExamController {

	@Autowired
	private IExamService  iService;
	
	@RequestMapping("/exam/sum")
	public Map<String, Object> setGrad(String token,String id,String type,String answer,String code){
		return iService.setGrad(token, id, type, answer, code);
	}
	
	
	
	/**
	 * @Desc获取试卷信息
	 * @param pid
	 * @return
	 */
	@RequestMapping("/exam")
	public Map< String, Object> getStuExam(String token,String mark){
		
		return iService.getExamPar(token, mark);
	}

	
	
}
