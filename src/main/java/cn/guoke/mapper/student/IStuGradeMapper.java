package cn.guoke.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Grade;

/**
 * @Descent 获取成绩
 * @author 语录
 *
 */
public interface IStuGradeMapper {

	// 获取学生成绩所有成绩
	@Select("select * from grade where sid = #{sid}")
	public List<Grade> getGrade(Integer sid);
		
	/**
	 * @Desc 增加考试成绩
	 * @param sid
	 * @param paperid
	 * @return
	 */
	@Insert("INSERT into courseinfo(sid,paperid,score,) VALUES(#{sid},#{paperid},#{score}) ")
	@Options(useGeneratedKeys=true, keyProperty="gradeid", keyColumn="gradeid") 
	public int addGrade(Integer sid,Integer paperid );
	
  
}
