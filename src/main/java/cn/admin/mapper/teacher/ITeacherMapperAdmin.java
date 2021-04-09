package cn.admin.mapper.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Teacher;

public interface ITeacherMapperAdmin {
	
	/**
	 * 查询所有老师
	 * @return
	 */
	@Select("select * from teacher")
	public List<Teacher> queryAllTeacher();
	
	/**
	 * 修改老师名字
	 * @return
	 */
	@Update("update teacher set tname=#{tname} where tid=#{tid}")
	public int changeTeacher(@Param("tname") String tname, @Param("tid") int tid);
}
