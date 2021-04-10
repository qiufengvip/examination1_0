package cn.guoke.mapper.teacher;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import cn.guoke.pojo.Mcq;

/**
 * @Desc 获取题库
 * @author 语录
 *
 */
public interface ILibMapper {

	/**
	 * @Desc 获取所有的选择题
	 * @return
	 */
	@Select("SELECT * FROM Mcq")
	public List<Mcq> getLibMcq();
	
}
