package cn.guoke.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Class;
import cn.guoke.pojo.School;
import cn.guoke.pojo.Student;

/**
 * @Desc 班级操作的接口
 * @author 语录
 *
 */
public interface IStuClassMapper {

	/**
	 * @Desc 添加班级 错误的方法 在这里记录 用于学校管理员调用
	 * @param class1
	 * @return
	 */
	@Insert("INSERT INTO class(cname,createtime,schoolid,ccode) VALUES(#{cname},#{createtime},#{schoolid},#{ccode})")
	@Options(useGeneratedKeys=true, keyProperty="cid", keyColumn="cid") 
	public int addClass(Class class1);
	 
	/**
	 * @Desc 获取班级根据班级邀请码
	 * @param ccode
	 * @return
	 */
	@Select("select * from class where ccode=#{ccode} and display = 0")
	public cn.guoke.pojo.Class getClassByCode(String ccode);
	
	/**
	 * @Desc 加入班级
	 * @param cid
	 * @return
	 */
	@Update("UPDATE student set  cid=#{cid} WHERE sid=#{sid}")
	public int updateStuClass(@Param("cid")Integer cid,@Param("sid")Integer sid);
	
	
	/**
	 * @Desc  获取班级下的所有学生
	 * @param cid
	 * @return
	 */
	@Select("select * FROM student where cid =#{cid}")
	public List<Student> getStudentByCid(Integer cid);
	
	/**
	 * @Descent 获取学校信息
	 * @param schoolid
	 * @return
	 */
	@Select("select * FROM school where schoolid=#{schoolid} ")
	public School getSchoolById(Integer schoolid);
	
	/**
	 * @Desc 获取班级的信息
	 * @param cid
	 * @return
	 */
	@Select("select * FROM class where cid=#{cid} and display = 0")
	public cn.guoke.pojo.Class getClassByCid(Integer cid);
	
	
}
