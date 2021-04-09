package cn.guoke.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.CourseInfo;
import cn.guoke.pojo.Teacher;

/**
 * @Desc 学生的查询
 * @author 语录
 *
 */
public interface IStuGetMapper {

	/**
	 * @Desc  根据 学校 id 获取所有的课程
	 * @param schoolId
	 * @return
	 */
	@Select("SELECT * FROM	 course WHERE tid in (SELECT tid FROM teacher WHERE schoolid = #{schoolId}) AND display =0")
	public List<Course> getCourseBySchoolId(Integer schoolId);
	
	
	/**
	 * @Descent 获取所有的课程 分页
	 * @param schoolId
	 * @return
	 */
	@Select("SELECT * FROM	 course WHERE tid in (SELECT tid FROM teacher WHERE schoolid = #{schoolId}) AND display =0 LIMIT #{page},#{size}")
	public List<Course> getCourseBySchoolIdLi(@Param("schoolid")Integer schoolid,@Param("paeg")Integer paeg,@Param("size")Integer size);
	
	
	/**
	 * @Descent 查询教师更具教师 id
	 * @param tid
	 * @return
	 */
	@Select("select * from teacher where tid=#{tid} ")
	public Teacher getTeacherById(Integer tid);
	
	/**
	 * @Descent 查询课程的信息
	 * @param cid
	 * @param sid
	 * @return
	 */
	@Select("SELECT * FROM courseinfo WHERE  sid =#{sid} AND cid =#{cid}  AND display = 0")
	public CourseInfo getCourseInfo(@Param("cid")Integer cid,@Param("sid")Integer sid);
	
	
	/**
	 * @Desc 获取加入课程的所有人
	 * @param cid
	 * @return
	 */
	@Select("SELECT * FROM courseinfo WHERE  cid = #{cid}")
	public List<CourseInfo> getAllCourseInfo(Integer cid);
	
	/**
	 * @Descent 查询我加入的所有的课程
	 * @param sid
	 * @return
	 */
	@Select("SELECT * FROM course WHERE cid in (SELECT cid FROM courseinfo WHERE sid=#{sid} )")
	public List<Course> getMyCourse(Integer sid);
	
	
	/**
	 * @Desc 获取教师
	 * @param paperid
	 * @return
	 */
	@Select("SELECT * FROM  teacher WHERE tid in (SELECT tid FROM paper WHERE paperid = #{paperid}) ")
	public Teacher getTeacherByPaper(Integer paperid);
	
	//查村课程
	@Select("select * from course where cid=#{cid}")
	public Course getCourseById(Integer cid);
}
