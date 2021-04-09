package cn.guoke.service.student;

import java.util.Map;

public interface IStuInfoService {

	
	/**
	 * @Desc 获取学生的基本信息
	 * @param token
	 * @return
	 */
	public Map<String,Object> getStuInfo(String token);
	
	/**
	 * @Desc 获取近期的考试
	 * @param sid
	 * @return
	 */
	public Map<String,Object> getExamRecently(String token);
	
}
