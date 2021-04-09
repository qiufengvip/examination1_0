package cn.guoke.service.student;

import java.util.Map;

/**
 * @Descent 获取
 * @author 语录
 *
 */
public interface IStuGradeService {

	/**
	 * @Desc 获取学生所有考试的成绩
	 * @param token
	 * @return
	 */
	public Map<String, Object> getMyAllGrade(String token);
}
