package cn.guoke.mapper.student;

import java.util.List;

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
		
	
  
}
