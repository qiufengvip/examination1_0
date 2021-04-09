package cn.guoke.service.sign;

import java.util.Map;

/**
 * @Desc 注册的接口
 * @author 语录
 *
 */
public interface IRegisterService {

	/**
	 * @Desc 生成验证码
	 * @return
	 */
	public Map<String, Object> getCode();
	
	/**
	 * @Descent 验证验证码
	 * @param mycode
	 * @param code
	 * @return
	 */
	public boolean isCode(String mycode,String code );
	
	/**
	 * @Descent 注册
	 * @param type
	 * @param userName
	 * @param password
	 * @param realName
	 * @param email
	 * @param code
	 * @param identification
	 * @param schoolid
	 * @return
	 */
	public Map<String, Object> register(String type ,String userName,String password,String realName,String email,String code ,String identification,Integer schoolid);

	/**
	 * @Descent 获取学校
	 * @return
	 */
	public Map<String, Object> getSchool();
	
}
