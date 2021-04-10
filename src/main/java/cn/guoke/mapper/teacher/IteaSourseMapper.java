package cn.guoke.mapper.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.Paper;
import cn.guoke.pojo.Student;

/**
 * @Descent课程的dao
 * @author 语录
 *
 */
public interface IteaSourseMapper {

	//获取创建的课程
	@Select("SELECT * FROM course WHERE tid=#{tid} and display =0")
	public List<Course> getMyAllCreate(Integer tid);
	
	/**
	 * @Descent 获取教师创建课程下所有的学生
	 * @param tid
	 * @return
	 */
	@Select("SELECT	* FROM student WHERE sid in (SELECT sid FROM courseinfo WHERE cid in (SELECT cid FROM course WHERE tid=#{tid}) )")
	public List<Student> getStudnetByTid(Integer tid);
	
	/**
	 * @Desc 获取课程下的所有学生
	 * @param cid
	 * @return
	 */
	@Select("SELECT * FROM student WHERE sid in (SELECT sid FROM courseinfo WHERE cid = #{cid})")
	public List<Student> getStuByCid(Integer cid);
	
	/**
	 * @Desc 
	 * @param cname
	 * @param tid
	 * @param createtime
	 * @return
	 */
	@Insert("INSERT INTO course(cname,tid,createtime) VALUES(#{cname},#{tid},#{createtime})")
	@Options(useGeneratedKeys=true, keyProperty="cid", keyColumn="cid") 
	public int addCourse(@Param("cname")String cname,@Param("tid")Integer tid,@Param("createtime")String createtime);

	
	@Insert("INSERT INTO grade(sid,paperid,score) VALUES(#{sid},#{paperid},#{score})")
	@Options(useGeneratedKeys=true, keyProperty="gradeid", keyColumn="gradeid") 
	public int addGrde(@Param("sid")Integer sid,@Param("paperid")Integer paperid,@Param("score")String score);

	
	/**
	 * @Desc 根据学号 获取学生id
	 * @param snumber
	 * @return
	 */
	@Select("select * FROM student where snumber=#{snumber}")
	public Student getStuBySnum(String snumber);
	
	@Select("select * FROM paper where papercode=#{papercode}")
	public Paper getPaperByCode(String papercode);
	
}
