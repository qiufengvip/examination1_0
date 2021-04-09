package cn.guoke.service.sign;

import java.util.Map;

/**
 * @Desc 定义学生登录的业务层接口
 * @author 语录
 *
 */
public interface ILoginService{

	//学生登录
	public Map<String, Object> stuLogin(String sNumber, String Psssword);
	
	//教师登录
	public Map<String, Object> teaLogin(String tNumber, String psssword);
}
