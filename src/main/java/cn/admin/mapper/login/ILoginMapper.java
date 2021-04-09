package cn.admin.mapper.login;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ILoginMapper {
	
	/**
	 * 登录
	 * @return
	 */
	@Select("SELECT rootname,rootinfoid FROM root LEFT JOIN rootinfo ON root.rootid = rootinfo.rootinfoid where root.rootname = #{rootname} and root.`password` = #{rootpassword}")
	public Map<String, Object> login(@Param("rootname") String rootname,@Param("rootpassword") String rootpassword);
	
}
