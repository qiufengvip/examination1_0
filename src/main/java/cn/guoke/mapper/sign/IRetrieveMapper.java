package cn.guoke.mapper.sign;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Code;

/**
 * @Desc 找回密码 
 * @author 语录
 *
 */
public interface IRetrieveMapper {

	//添加验证码  emali
	@Insert("INSERT INTO code(codeid,code,createtime,emali)  VALUES (#{codeid}, #{code}, #{createtime},#{emali})")
	public int addCode(Code code);
	
	//根据标识 获取 验证码
	@Select("select * from code where emali=#{emali} and display=0") 
	public Code getCodeByIden(String emali);
	
	//删除验证码
	@Update("update code set display='-1' where emali=#{emali} ")
	public int delCode(String emali);

	
	
}
