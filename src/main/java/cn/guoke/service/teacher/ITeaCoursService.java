package cn.guoke.service.teacher;

import java.util.Map;

/**
 * @Desc 获取学生考试成绩
 * @author 语录
 *
 */
public interface ITeaCoursService {

	/**
	 * @Desc 获取的教师的考试 已经批改的试卷
	 * @param token
	 * @return
	 */
	public Map<String, Object> getCourse(String token);
	
	
	/**
	 * @Desc 获取学生的成绩
	 * @param token
	 * @return
	 */
	public Map<String, Object> getGradeMyStu(String token);
	
	
	
	
	
}
