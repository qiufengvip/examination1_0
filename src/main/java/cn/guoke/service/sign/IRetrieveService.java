package cn.guoke.service.sign;

import java.util.Map;

/**
 * @Desc 找回密码的业务层
 * @author 语录
 *
 */
public interface IRetrieveService {

	/**
	 * @Desc 找回密码 
	 * @param type
	 * @param emali
	 * @param userNmae
	 * @return
	 */
	public Map<String, Object> retrieve(String type,String emali,String userNmae);
	
	
	/**
	 * @Desc 验证验证码
	 * @param code 
	 * @param myCode
	 * @return
	 */
	public Map<String,Object> isCode(String code ,String myCode);
	
	/**
	 * @Desc 发送验证码
	 * @param emali
	 * @return
	 */
	public Map<String, Object> sendEmail(String emali);
	
}
