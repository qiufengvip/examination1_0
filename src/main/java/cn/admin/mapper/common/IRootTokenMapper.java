package cn.admin.mapper.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.admin.pojo.root;
import cn.admin.pojo.rootinfo;
import cn.guoke.pojo.Student;

public interface IRootTokenMapper extends BaseMapper<rootinfo>{
	
	/**
	 * 查询管理员token
	 * @param token
	 * @return
	 */
	@Select("select * from rootinfo WHERE rootinfo.token = #{token}")
	public Map<String, Object> queryRootToken(String token);
	

}
