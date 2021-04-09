package cn.guoke.mapper.sign;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Code;
import cn.guoke.pojo.School;
import cn.guoke.pojo.StuInfo;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;

/**
 * @Desc 学生 教师 注册的接口
 * @author 语录
 *
 */
public interface IRegisterMapper {


	//添加验证码 这个是经行注册的验证码 是不需要 emali
	@Insert("INSERT INTO code(codeid,code,createtime,identification)  VALUES (#{codeid}, #{code}, #{createtime},#{identification})")
	public int addCode(Code code);
	
	//根据标识 获取 验证码
	@Select("select * from code where identification=#{identification} and display=0") 
	public Code getCodeByIden(String identification);
	
	//删除验证码
	@Update("update code set display='-1' where codeid=#{codeid} ")
	public int delCode(String codeid);
	
	//根据学号经行查询
	@Select("select * from student where snumber=#{snumber} or emali=#{emali}")
	public Student getStuByName(@Param("snumber")String snumber,@Param("emali") String emali);
	
	//根据教师号经行查询
	@Select("select * from teacher where tnumber=#{tnumber} or emali=#{emali}")
	public Teacher getTeaByName(@Param("tnumber")String tnumber,@Param("emali") String emali);
	
	//需要的 添加教师 
	@Insert("insert into teacher(tname,schoolid,tnumber,password,emali,createtime) value(#{tname},#{schoolid},#{tnumber},#{password},#{emali},#{createtime})")
	@Options(useGeneratedKeys=true, keyProperty="tid", keyColumn="tid") 
	public int addTeacher(Teacher teacher);
	
	//添加学生 
	@Insert("insert into student(snumber,sname,schoolid,password,emali,createtime,state)  value(#{snumber},#{sname},#{schoolid},#{password},#{emali},#{createtime},#{state})")
	@Options(useGeneratedKeys=true, keyProperty="sid", keyColumn="sid") 
	public int addStudent(Student student);
	
	//查询所有学校
	@Select("select * from school where display=0")
	public List<School> getAllScool();
	
	//生成token
	//学生的 token
	@Insert("insert into stuInfo(token,createtime,sid) value(#{token},#{createtime},#{sid})")
	@Options(useGeneratedKeys=true, keyProperty="stuinfoid", keyColumn="stuinfoid") 
	public int addStuToken(StuInfo stuInfo);
	
	//教师的 Token
	@Insert("insert into teacherinfo(token,createtime,tid) value(#{token},#{createtime},#{tid})")
	@Options(useGeneratedKeys=true, keyProperty="teacherinfo", keyColumn="teacherinfo") 
	public int addTeaToken(TeacherInfo teacherInfo);
	
	
	
	
	
}
