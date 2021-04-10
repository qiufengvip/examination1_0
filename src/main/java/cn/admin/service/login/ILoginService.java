package cn.admin.service.login;

import java.util.Map;

public interface ILoginService {
	
	/**
	 * 登录
	 * @param rootname
	 * @param rootpassword
	 * @return
	 */
	public Map<String, Object> login(String rootname, String rootpassword);
}
