package cn.guoke.mapper.sign;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.StuInfo;
import cn.guoke.pojo.Student;

/**
 * @Desc 执行登录的 dao 层
 * @author 语录
 *
 */
public interface IStuLoginMapper {

	//根据学号获取 学生
	@Select("select * from student where snumber=#{sNumber} ")
	public Student queryStudnetBySNumber(String sNumber);
	
	//根据 sid 获取学生token
	@Select("select * from stuinfo where sid=#{sid}")
	public StuInfo queryStudentBySid(Integer sid);
	
	@Update("update stuinfo set token=#{token},createtime=#{createtime} where sid=#{sid}")
	public int updateStuInfoToken(@Param("token")String token,@Param("sid")Integer sid,@Param("createtime") String createtime);
	
}
