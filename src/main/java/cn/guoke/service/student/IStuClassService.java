package cn.guoke.service.student;

import java.util.Map;

/**
 * @Desc 这里
 * @author 语录
 *
 */
public interface IStuClassService {

	/***
	 * @Desc 加入课程
	 * @param token
	 * @param cLassCode
	 * @return
	 */
	public Map<String, Object> addClass(String token,String cLassCode);
	
	/**
	 * @Descent 获取班级信息
	 * @param token
	 * @return
	 */
	public Map<String, Object> getClass(String token);
	
}
