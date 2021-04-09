package cn.guoke.mapper.common;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.StuInfo;
import cn.guoke.pojo.Student;

/**
 * @Desc 对学生的 token 进行的操作
 * @author 语录
 *
 */
public interface IStuTokenMapper {

	@Select("select * from stuinfo where sid=#{sid}")
	public StuInfo getTokenById(Integer sid);
	
	@Update("update stuinfo set token=#{token},createtime=#{createtime} where sid=#{sid}")
	public int updateStuInfoToken(@Param("token")String token,@Param("sid")Integer sid,@Param("createtime") String createtime);
	
	// 根据 token 查询学生信息
	@Select("select * from student where sid = (select sid from stuinfo where token=#{token})")
	public Student getStuByToken(String token);

	
	
	
}
