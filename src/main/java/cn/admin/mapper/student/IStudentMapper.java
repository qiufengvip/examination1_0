package cn.admin.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.guoke.pojo.Student;

public interface IStudentMapper extends BaseMapper<Student> {

	/**
	 * 查询所有学生
	 * 
	 * @return
	 */
	@Select("select * from student")
	public List<Student> queryAllStudent();

	/**
	 * 修改一个学生姓名
	 * 
	 * @return
	 */
	@Update("UPDATE student SET sname=#{sname} WHERE sid = #{sid}")
	public int changeStudent(@Param("sname") String sname, @Param("sid") int sid);

}
