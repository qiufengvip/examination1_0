package cn.guoke.controller.student;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.student.IStuExamService;
import cn.guoke.service.student.IStuGradeService;

/**
 * @Desc 获取基本成绩
 * @author 语录
 *
 */

@Controller
@RequestMapping("/student/exam")
@ResponseBody
public class StuExamControler {

	
	@Autowired
	private IStuExamService iStuExamService;
	
	@Autowired
	private IStuGradeService iStuGradeService;
	
	
	//获取未完成的考试
	@RequestMapping("/nexam/{token}")
	public Map<String, Object> getNotExam(@PathVariable("token")String token){
		return iStuExamService.getReplyExam(token);  //获取未完成的考试
	}
 	
	
	//获取已经完成的考试
	@RequestMapping("/yexam/{token}")
	public Map<String, Object> getYesExam(@PathVariable("token")String token){
		return iStuExamService.getSeccExam(token);  //获取完成的考试
	}
	
	/**
	 * @Desc 获取考试公告
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}")
	public Map<String, Object> getAnn(@PathVariable("id")Integer id){
		return iStuExamService.getAnnouncements(id);
		
	}
	
	/**
	 * @Desc 获取全部成绩
	 * @param token
	 * @return
	 */
	@RequestMapping("/grades/{token}")
	public Map<String, Object> getAllGrade(@PathVariable("token")String token){
		return iStuGradeService.getMyAllGrade(token);
	}
	
	
	
	
	
}
