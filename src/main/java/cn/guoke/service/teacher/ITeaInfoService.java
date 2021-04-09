package cn.guoke.service.teacher;

import java.util.Map;

/**
 * @Desc 教师首页信息获取
 * @author 语录
 *
 */
public interface ITeaInfoService {

	/**
	 * @Desc 获取学生首页信息
	 * @param token
	 * @return
	 */
	public Map<String, Object> getTeaInfoIndex(String token);
	
	
	/**
	 * @Desc  最近俩个月的考试成绩
	 * @param token
	 * @return
	 */
	public Map<String, Object> getTeaLatelyExam(String token);
}
