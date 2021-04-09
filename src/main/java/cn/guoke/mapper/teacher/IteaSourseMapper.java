package cn.guoke.mapper.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.Student;

/**
 * @Descent课程的dao
 * @author 语录
 *
 */
public interface IteaSourseMapper {

	//获取创建的课程
	@Select("SELECT * FROM course WHERE tid=#{tid} and display =0")
	public List<Course> getMyAllCreate(Integer tid);
	
	/**
	 * @Descent 获取教师创建课程下所有的学生
	 * @param tid
	 * @return
	 */
	@Select("SELECT	* FROM student WHERE sid in (SELECT sid FROM courseinfo WHERE cid in (SELECT cid FROM course WHERE tid=#{tid}) )")
	public List<Student> getStudnetByTid(Integer tid);
	
	/**
	 * @Desc 获取课程下的所有学生
	 * @param cid
	 * @return
	 */
	@Select("SELECT * FROM student WHERE sid in (SELECT sid FROM courseinfo WHERE cid = #{cid})")
	public List<Student> getStuByCid(Integer cid);
	
}
