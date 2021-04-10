package cn.guoke.controller.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.pojo.Examination;
import cn.guoke.service.teacher.ITeaExamService;

/**
 * 老师对考试试卷的管理
 * @author 梦伴
 *
 */
@ResponseBody
@RequestMapping("/tea/exam")
@Controller
public class TeaExaminationController {
	
	@Autowired
	private ITeaExamService teaExamServiceImpl;
	
	/**
	 * 老师创建考试 
	 * @param token 老师的token
	 * @param paperid 试卷id
	 * @param cid 课程id
	 * @return
	 */
	@RequestMapping("/createExam/{token}")
	public Map<String, Object> createExam(@PathVariable("token") String token,Examination examinfo){
		return teaExamServiceImpl.createExam(token,examinfo);
	}
	
	
	/**
	 * 老师查看自己创建的所有考试
	 * @param token
	 * @return
	 */
	@RequestMapping("/queryMyExam/{token}")
	public Map<String, Object> queryMyExam(@PathVariable("token") String token){
		return teaExamServiceImpl.queryMyExam(token);
	}
	
	
	/**
	 * 老师结束考试
	 * @param token
	 * @param examinationid
	 * @param tid
	 * @return
	 */
	@RequestMapping("/closeExam/{token}/{examinationid}")
	public Map<String, Object> closeExam(@PathVariable("token") String token,@PathVariable("examinationid") int examinationid){
		return teaExamServiceImpl.closeExam(token, examinationid);
	}
	
	/**
	 * 老师获取自己创建的课程
	 * @param token
	 * @param examinationid
	 * @param tid
	 * @return
	 */
	@RequestMapping("/queryMyCourse/{token}")
	public Map<String, Object> queryMyCourse(@PathVariable("token") String token){
		return teaExamServiceImpl.queryMyClass(token);
	}
	
	/**
	 * 老师获取自己创建的试卷
	 * @param token
	 * @param examinationid
	 * @param tid
	 * @return
	 */
	@RequestMapping("/queryMyPaper/{token}")
	public Map<String, Object> queryMyPaper(@PathVariable("token") String token){
		return teaExamServiceImpl.queryMyPaper(token);
	}
}
