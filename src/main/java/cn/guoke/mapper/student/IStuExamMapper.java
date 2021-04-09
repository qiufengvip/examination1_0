package cn.guoke.mapper.student;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Grade;

/**
 * @Descent 获取的
 * @author 语录
 *
 */
public interface IStuExamMapper extends BaseMapper<Examination>{
 	
	/**
	 * @Descent 获取考场信息
	 * @param examinationid
	 * @return
	 */
	@Select("SELECT * FROM examination WHERE examinationid =#{examinationid} AND state <> 0 ")
	public Examination getExamInfoByIDY(Integer examinationid);
	
	@Select("SELECT * FROM examination WHERE examinationid =#{examinationid} AND state = 0 ")
	public Examination getExamInfoID(Integer examinationid);
	
	
	/**
	 * @Desc 获取学生的成绩
	 * @param sid
	 * @param paperid
	 * @return
	 */
	@Select("SELECT * FROM grade WHERE sid =#{sid} and paperid =#{paperid} ") 
	public Grade getStuScoure(@Param("sid")Integer sid,@Param("paperid")Integer paperid);
	
	
	
	
	
	
}
