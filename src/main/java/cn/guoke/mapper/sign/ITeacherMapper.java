package cn.guoke.mapper.sign;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;

public interface ITeacherMapper {
		//根据教师号获取 教师
		@Select("select * from teacher where tnumber=#{tnumber} ")
		public Teacher queryTeacherBySNumber(String tNumber);
		
		//根据 tid 获取教师token
		@Select("select * from teacherinfo where tid=#{tid}")
		public TeacherInfo queryTeacherBySid(Integer tid);
		
		@Update("update teacherinfo set token=#{token},createtime=#{createtime} where tid=#{tid}")
		public int updateTeaInfoToken(@Param("token")String token,@Param("tid")Integer tid,@Param("createtime") String createtime);
}
