package cn.admin.service.login.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.admin.mapper.common.IRootTokenMapper;
import cn.admin.mapper.login.ILoginMapper;
import cn.admin.service.login.ILoginService;
import cn.guoke.utils.DataUtils;
import cn.guoke.utils.SnowflakeUtils;

@Service
public class LoginServiceImpl implements ILoginService {
	
	@Autowired
	private ILoginMapper loginMapper;
	
	@Autowired
	private IRootTokenMapper rootTokenMapper;
	
	/**
	 * 登录
	 */
	@Override
	public Map<String, Object> login(String rootname, String rootpassword) {
		// TODO Auto-generated method stub
		if (("".equals(rootname) || rootname == null)||("".equals(rootpassword) || rootpassword == null)) {// 判断是否非法调用
			return DataUtils.MyPrint(-2, "非法调用", null);
		}
		Map<String, Object> login = loginMapper.login(rootname, rootpassword);
		if (login == null) {
			return DataUtils.MyPrint(-1, "登录失败", null);
		}
		
		Long newToken = SnowflakeUtils.getSnowflake();
		
		int result = rootTokenMapper.flushRootToken(Integer.parseInt(login.get("rootinfoid").toString()), newToken.toString());
		if (result != 1) {
			return DataUtils.MyPrint(-1, "登录失败", null);
		}
		
		login.put("token", newToken);
		login.remove("rootinfoid");
		return DataUtils.MyPrint(200, "登录成功", login);
	}

}
