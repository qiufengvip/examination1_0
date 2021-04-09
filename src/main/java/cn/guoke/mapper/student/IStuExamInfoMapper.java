package cn.guoke.mapper.student;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Examinationinfo;

/**
 * @Desc 考试信息获取
 * @author 语录
 *
 */
public interface IStuExamInfoMapper extends BaseMapper<Examinationinfo> {

	
	/**
	 * @Desc 查询已经考试的试卷
	 * @param sid
	 * @return
	 */
	@Select("SELECT * FROM examinationinfo WHERE sid = #{sid}")
	public List<Examinationinfo> getExaminfoBySid(Integer sid);
	
	
	/**
	 * @Desc 获取老师为开始的考试
	 * @param tid
	 * @return
	 */
	@Select("SELECT * FROM examination WHERE tid=#{tid} AND state = 0")
	public List<Examination> getExamById(Integer tid);
	
	
	
	
	
	
}
