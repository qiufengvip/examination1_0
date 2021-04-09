package cn.guoke.controller.sign;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.guoke.service.sign.ILoginService;
import cn.guoke.service.sign.IRegisterService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 登录模块的 C 层
 * @author 语录
 *
 */
@Controller
@RequestMapping("/sign")
@ResponseBody
public class SignController {

	
	@Autowired
	private  ILoginService iLoginService;  
	
	@Autowired
	private IRegisterService iRegisterService;
	/**
	 * @Desc 登录 
	 * @param name
	 * @param password
	 * @param type 登录的类型
	 * @return
	 */
	@PostMapping("/login")
	public Map<String, Object> login(String name , String password , String type){

		if (type==null||type.equals("1")) {
			return iLoginService.stuLogin(name, password);
		 }
		else if (type.equals("2")) {
			return iLoginService.teaLogin(name, password);
		}else {
			return DataUtils.print("-1", "请正确调用接口");
		}

	}
	
	
	//用户注册
	@PostMapping("/register")
	public Map<String, Object> register(String type ,String userName,String password,String realName,String email,String code ,String identification,Integer schoolid){

		return iRegisterService.register(type, userName, password, realName, email, code, identification, schoolid);
	}
	
	/**
	 * @Descent 获取验证码
	 * @return
	 */
	@GetMapping("/getcode")
	public Map<String, Object> getCode(){
		return iRegisterService.getCode();	
	}
	
	
	/**
	 * @Descent 获取学校
	 * @return
	 */
	@GetMapping("/getschool")
	public Map<String, Object> getSchool(){
		return iRegisterService.getSchool();
	}
	
	
	
	
}
