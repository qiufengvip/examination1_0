package cn.guoke.mapper.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Paper;

public interface ITeaExamMapper {

	/**
	 * 老师创建考试
	 * 
	 * @param token
	 * @param paperid
	 * @param cid
	 * @return
	 */
	@Insert("INSERT INTO examination(examinationmark,paperid,tid,state,createtime,announcements,cid) VALUES(#{examinationmark}, #{paperid},#{tid},#{state},#{createtime},#{announcements},#{cid})")
	@Options(useGeneratedKeys = true, keyProperty = "examinationid", keyColumn = "examinationid")
	public int createExam(@Param("examinationmark") String examinationmark, @Param("paperid") int paperid,
			@Param("tid") int tid, @Param("state") int state, @Param("createtime") String createtime,
			@Param("announcements") String announcements, @Param("cid") int cid);

	/**
	 * 老师查看自己创建的所有考试
	 * 
	 * @param token
	 * @return
	 */
	@Select("select * from examination where tid = #{tid}")
	public List<Examination> queryMyExam(@Param("tid") int tid);

	/**
	 * 老师结束考试
	 * 
	 * @param token
	 * @param examinationid
	 * @param tid
	 * @return
	 */
	@Update("UPDATE examination SET state=-1 WHERE examinationid=#{examinationid} and tid = #{tid}")
	public int closeExam(@Param("examinationid") int examinationid,@Param("tid") int tid);

	/**
	 * 获取老师自己 创建的所有课程(course)
	 * 
	 * @param examinationid
	 * @return
	 */
	@Select("select * from course where tid = #{tid}")
	public List<Course> queryMyClass(@Param("tid") int tid);

	/**
	 * 获取老师自己 创建的所有试卷(paper)
	 * 
	 * @param examinationid
	 * @return
	 */
	@Select("select * from paper where tid = #{tid}")
	public List<Paper> queryMyPaper(@Param("tid") int tid);
}
