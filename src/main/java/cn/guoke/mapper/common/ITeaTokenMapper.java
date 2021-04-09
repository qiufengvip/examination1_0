package cn.guoke.mapper.common;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;

/**
 * @Descent 教师信息的获取
 * @author 语录
 *
 */
public interface ITeaTokenMapper {

	@Select("select * from teacherinfo where tid=#{tid}")
	public TeacherInfo getTokenById(Integer tid);
	
	@Update("update teacherinfo set token=#{token},createtime=#{createtime} where tid=#{tid}")
	public int updateTeaInfoToken(@Param("token")String token,@Param("tid")Integer tid,@Param("createtime") String createtime);
	
	// 根据 token 查询学生信息
	@Select("select * from teacher where tid = (select tid from teacherinfo where token=#{token})")
	public Teacher getTeaByToken(String token);

}
