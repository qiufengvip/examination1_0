package cn.guoke.service.sign.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.sign.IStuLoginMapper;
import cn.guoke.mapper.sign.ITeacherMapper;
import cn.guoke.pojo.StuInfo;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;
import cn.guoke.service.sign.ILoginService;
import cn.guoke.utils.DataUtils;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * @Desc 学生登录业务层实现
 * @author 语录
 *
 */
@Service
public class SignServiceImpl implements ILoginService{

	@Autowired
	private IStuLoginMapper iStuLoginMapper; //学生的 dao 层
	
	@Autowired
	private ITeacherMapper iTeacherMapper; // 教师的 dao 层

	
	//学生登录
	public Map<String, Object> stuLogin(String sNumber, String psssword) {
		// TODO Auto-generated method stub
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>();
		//验证是否为空
		if (sNumber==null||psssword==null||sNumber.equals("")||psssword.equals("")) {
			return DataUtils.print(data, "请输入用户名密码");
		}else { 
			Student student = iStuLoginMapper.queryStudnetBySNumber(sNumber);
			if (student==null) { // 数据库中是否有该对象
				return DataUtils.print(data,"-2","请您先经行注册");
			}else{
				 String password = DigestUtil.md5Hex(psssword);
				 String myPassword = student.getPassword();
				 
				 if (password.equals(myPassword)) {  // 验证密码是否正确
					 //获取id
					Integer stuid = student.getSid();
					//获取token
					 StuInfo userInfo = iStuLoginMapper.queryStudentBySid(stuid);
					 //获取 时间
					 String loginTime = userInfo.getCreatetime();
					 Long nowTime = Long.parseLong(loginTime);
					 
					//创建返回的 数据
					Map<String, Object> dataP = new HashMap<String, Object>();

					 if (DataUtils.getDataTime()-nowTime>604800000) { //七天后更新数据 token
						String newToken = IdUtil.simpleUUID(); //获取token
						iStuLoginMapper.updateStuInfoToken(newToken, stuid,DataUtils.getDataTime()+""); //更新数据
						dataP.put("token", newToken);
						data.put("data", dataP);
						return DataUtils.print(data);
					 }else {
						 dataP.put("token", userInfo.getToken());
						 data.put("data", dataP);
						 return DataUtils.print(data);	 
					 }
					 
					
						
				}else{  //密码不正确
					return DataUtils.print(data, "-3", "请输入正确的密码");
				}
			}
			
			
		}
		
	}
	
	
	//教师登录
	public Map<String, Object> teaLogin(String tNumber, String psssword){
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>();
		//验证参数是否为空
		if (tNumber==null||tNumber.equals("")||psssword==null||psssword.equals("")) {  //验证参数是否为空
			return DataUtils.print(data, "请输入用户名密码");
		}else {
			//获取获取教师
			Teacher teacher= iTeacherMapper.queryTeacherBySNumber(tNumber);
			if (teacher==null) {  // 验证用户是否存在
				return DataUtils.print(data,"-2","请您先经行注册");
			}else {
				//开始验证密码
				//用户输入的密码
				String password = DigestUtil.md5Hex(psssword);
				//数据库中的密码
				String tPassword = teacher.getPassword();
				if (password.equals(tPassword)) {  //验证密码是否正确
					//获取 teacher 的token 
					TeacherInfo teacherinfo = iTeacherMapper.queryTeacherBySid(teacher.getTid());
					String loginTime = teacherinfo.getCreatetime(); //获取登录时间
					long time = Long.parseLong(loginTime);
					 
					//返回的数据参数
					Map<String, Object> dataP = new HashMap<String, Object>();

					if (DataUtils.getDataTime()-time>604800000) { //七天后更新数据 token
						String newToken = IdUtil.simpleUUID(); //获取token
						iTeacherMapper.updateTeaInfoToken(newToken, teacher.getTid(),DataUtils.getDataTime()+""); //更新数据
						 dataP.put("token", newToken);
						 data.put("data", dataP);
						 return DataUtils.print(data);	
					}else{
						 dataP.put("token", teacherinfo.getToken());
						 data.put("data", dataP);
						 return DataUtils.print(data);	 
					}
					
				}else {
					return DataUtils.print(data, "-3", "请输入正确的密码");
				}
			}
		}
	}
 	
	
	
	public static void main(String[] args) {
		System.out.println(DigestUtil.md5Hex("0"));
	}

}
