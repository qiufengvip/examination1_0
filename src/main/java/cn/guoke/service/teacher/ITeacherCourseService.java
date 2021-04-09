package cn.guoke.service.teacher;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.ITeaTokenMapper;

/**
 * @Desc 获取教师的课程
 * @author 语录
 *
 */
public interface ITeacherCourseService {


	/**
	 * @Desc 获取课程的基本信息
	 * @param token
	 * @return
	 */
	public Map<String, Object> getCourseInfo(String token);
	
	
	/**
	 * @Desc 获取课程的下的所有学生
	 * @param token
	 * @param cid
	 * @return
	 */
	public Map<String, Object> getMyCourse(String token,Integer cid);
	
	
	/**
	 * @Desc 获取所有的课程
	 * @param token
	 * @return
	 */
	public Map<String, Object> getAllCourse(String token);
	
}
