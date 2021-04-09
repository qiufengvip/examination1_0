package cn.guoke.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Examinationinfo;
import cn.guoke.pojo.Grade;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;

/**
 * @Desc 学生的基本信息获取
 * @author 语录
 *
 */
public interface IStuInfoMapper {

	// 根据 token 查询学生信息
	@Select("select * from student where sid = (select sid from stuinfo where token=#{token})")
	public Student getStuByToken(String token);
	
	
	// 获取学生 所有修课程
	@Select("SELECT * FROM  course WHERE cid in (SELECT cid FROM courseinfo WHERE sid =#{sid} ) AND  display = 0 ;") 
	public List<Course> getCourseBySid(Integer sid);
 	
	
	// 获取学生 没有通过课程数
	@Select("SELECT * FROM  course WHERE cid in (SELECT cid FROM courseinfo WHERE sid =#{sid} AND state = 0 or state = -1 ) AND  display = 0 ;") 
	public List<Course> getNotCourseBySid(Integer sid);
 	
	
	// 获取学生的考试记录
	@Select("SELECT * from examination WHERE examinationid in (SELECT examinationid FROM examinationinfo WHERE  sid= #{sid})")
	public List<Examinationinfo> getExam(Integer sid);
	
	// 获取学生的成绩 主要的获取 通过考试数目
	@Select("SELECT * FROM grade WHERE sid= #{sid}") 
	public List<Grade> getGradeById(Integer sid);
	
	
	@Select("SELECT * FROM grade WHERE sid= #{sid} And score < 60") 
	public List<Grade> getGradeNotById(Integer sid);
	
	// 获取没有考试的
	@Select("SELECT * FROM examination WHERE  state < '1' AND examinationid in (SELECT examinationid FROM examinationinfo WHERE sid = #{sid}) ")
	public List<Examination> getExaminationNotRe(Integer sid);
	
	// 获取所有的最近的考试
	@Select("SELECT * from examination  WHERE examinationid in (SELECT examinationid FROM examinationinfo WHERE  sid= #{sid}) ORDER BY createtime  LIMIT 6")
	public List<Examination> getExaminationById(Integer sid);
	
	// 获取监考老师姓名
	@Select("SELECT * FROM teacher WHERE tid= #{tid}")
	public Teacher getTeacher(Integer tid);
	
	// 根据试卷id 获取试卷
	@Select("SELECT * FROM grade WHERE paperid= #{paperid}") 
	public Grade getgradeById(Integer paperid);
	
	
	
	
}
