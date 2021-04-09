package cn.guoke.mapper.student;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @Desc 添加方法
 * @author 语录
 *
 */
public interface IStuSetMapper {

	
	/**
	 * @Desc 加入课程
	 * @param cid
	 * @param sid
	 * @return
	 */
	@Insert("INSERT into courseinfo(cid,sid,createtime,) VALUES(#{cid},#{sid},#{createtime}) ")
	@Options(useGeneratedKeys=true, keyProperty="cinfoid", keyColumn="cinfoid") 
	public int addSouser(@Param("cid")Integer cid, @Param("sid")Integer sid,@Param("createtime")String createtime);
	
	
}
