package cn.guoke.service.student;

import java.util.Map;

/**
 * @Descent 对学生课程的操作的接口
 * @author 语录
 *
 */
public interface IStuCourseService {

		
	/**
	 * @Descent 获取全部课程
	 * @param token
	 * @return
	 */
	public Map<String, Object> getAllCourse(String token);
	
	/**
	 * @Desc 获取正在修的课程
	 * @param token
	 * @return
	 */
	public Map<String, Object> getMyCourse(String token);
	
	
	/**
	 * @Desc  添加而课程
	 * @param token
	 * @param cid
	 * @return
	 */
	public Map<String, Object> addCourse(String token,Integer cid);
	
	
	/**
	 * @Descent 分页获取所有的课程
	 * @param token
	 * @param paeg
	 * @param size
	 * @return
	 */
	public Map<String, Object> getCourseByPage(String token,Integer paeg,Integer size);
	
	
}
