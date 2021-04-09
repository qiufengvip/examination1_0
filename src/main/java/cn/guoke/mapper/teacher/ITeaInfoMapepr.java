package cn.guoke.mapper.teacher;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Examination;

/**
 * @Desc 获取教师首页信息
 * @author 语录
 *
 */
public interface ITeaInfoMapepr {

	/**
	 * @Desc 获取创建试卷
	 * @param tid
	 * @return
	 */
	@Select("SELECT * FROM examination  WHERE tid =#{tid}")
	public List<Examination> getExaminationByTid(Integer tid);
	
	
	
}
