package cn.guoke.service.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Paper;

public interface ITeaExamService {

	/**
	 * 老师创建考试
	 * @param token
	 * @param paperid
	 * @param cid
	 * @return
	 */
	public Map<String, Object> createExam(String token,Examination examinfo);
	

	/**
	 * 老师查看自己创建的所有考试
	 * @param token
	 * @return
	 */
	public Map<String, Object> queryMyExam(String token);
	
	
	/**
	 * 老师结束考试
	 * @param token
	 * @param examinationid
	 * @param tid
	 * @return
	 */
	public Map<String, Object> closeExam(String token,int examinationid);
	
	/**
	 * 获取老师自己 创建的所有课程(course)
	 * @param examinationid
	 * @return
	 */
	public Map<String, Object> queryMyClass(String token);
	
	/**
	 * 获取老师自己 创建的所有试卷(paper)
	 * @param examinationid
	 * @return
	 */
	public Map<String, Object> queryMyPaper(String token);
}
