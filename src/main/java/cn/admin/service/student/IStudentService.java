package cn.admin.service.student;

import java.util.List;
import java.util.Map;

import cn.guoke.pojo.Student;

public interface IStudentService {
	/**
	 * 查询所有学生
	 * @return
	 */
	public Map<String, Object> queryAllStudent(String token);

	/**
	 * 修改一个学生姓名
	 * @param token
	 * @param student 
	 * @return
	 */
	public Map<String, Object> changeStudent(String token, Student student);
}
