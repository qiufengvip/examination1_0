package cn.admin.controller.login;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.admin.service.login.ILoginService;

@ResponseBody
@Controller
@RequestMapping("/admin")
public class LoginController {
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("/login")
	public Map<String, Object> login(String rootname,String rootpassword){
		return loginService.login(rootname, rootpassword);
	}
	
}
