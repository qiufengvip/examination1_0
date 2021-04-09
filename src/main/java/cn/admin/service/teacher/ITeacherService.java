package cn.admin.service.teacher;

import java.util.Map;

import cn.guoke.pojo.Teacher;

public interface ITeacherService {
	
	/**
	 * 查询所有老师
	 * @return
	 */
	public Map<String, Object> queryAllTeacher(String token);
	
	/**
	 * 修改老师名字
	 * @return
	 */
	public Map<String, Object> changeTeacher(String token, Teacher teacher);
}
