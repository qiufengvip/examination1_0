package cn.guoke.service.sign.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.sign.IRegisterMapper;
import cn.guoke.mapper.sign.IStuLoginMapper;
import cn.guoke.mapper.sign.ITeacherMapper;
import cn.guoke.pojo.Code;
import cn.guoke.pojo.School;
import cn.guoke.pojo.StuInfo;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.pojo.TeacherInfo;
import cn.guoke.service.sign.IRegisterService;
import cn.guoke.utils.CheckStrUtils;
import cn.guoke.utils.CodeUtils;
import cn.guoke.utils.DataUtils;
import cn.guoke.utils.SnowflakeUtils;
import cn.hutool.core.convert.NumberWordFormatter;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;

@Service
public class RegisterServiceImpl implements IRegisterService {

	@Autowired
	private IRegisterMapper iRegisterMapper;
	
	@Autowired
	private IStuLoginMapper iStuLoginMapper;
	
	@Autowired
	private ITeacherMapper iTeacherMapper;
	
	// 获取验证码
	public Map<String, Object> getCode() {
		// TODO Auto-generated method stub
		// 获取验证码
		String code = CodeUtils.getCode();
		// 获取标识
		 String identification = SnowflakeUtils.getSnowflake().toString();
		 Code codes = new Code();
		 codes.setCode(code);
		 codes.setCreatetime(DataUtils.getDataTime()+"");
		 codes.setCodeid(SnowflakeUtils.getSnowflake().toString());
		 codes.setIdentification(identification);		 
		//存入数据库中
		 int suenss = iRegisterMapper.addCode(codes);
		 // 创建返回的数据 
		 Map<String, Object> data = new HashMap<String, Object>();	
		 if (suenss>0) {
		    Map<String, Object> dataP = new HashMap<String, Object>();
		    dataP.put("identification", identification);
		    dataP.put("code", code);
		    data.put("data", dataP);
		    return DataUtils.print(data, "200", "secess");
		 }else {
			 return DataUtils.print(data, "-1", "获取失败");
		}
		
	}
	
	/**
	 * @Desc 注册
	 * @return
	 */
	public Map<String, Object> register(String type ,String userName,String password,String realName,String email,String code ,String identification,Integer schoolid){
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>();
		
		//验证参数是否符合要求
		if(userName==null||userName.equals("")||password==null||password.equals("")){  //验证验证码是否为空
			return DataUtils.print(data, "-1", "请输入用户名密码");
		}else {
			//验证真实姓名
			if (realName==null||realName.equals("")) {
				
				return DataUtils.print(data, "-1", "请输入您真实的姓名");
				
			}else{
				//邮箱前端经行验证
				
				if (CheckStrUtils.verifyPassword(password)) { //验证密码 密码必须大于六位 且 包含数字和字母和其他符号
					
					
					
					
					if (code!=null&&!code.equals("")) { // 验证验证码
						
						//根据标识符获取验证码
						Code codeObj = iRegisterMapper.getCodeByIden(identification);
						if (codeObj==null) {  // 根据 标识符号没有获取到验证码
							return DataUtils.print(data, "-2", "请您重新获取验证码");
						}else{
							String myCode = codeObj.getCode();
							long myTime = Long.parseLong(codeObj.getCreatetime());
							
							if (DataUtils.getDataTime()-myTime>600000) { //验证时间
								//重新生成验证码
								iRegisterMapper.delCode(codeObj.getCodeid()); //删除当前验证码
								return DataUtils.print(data, "-5", "验证码超时");
							}
							if (isCode(myCode, code)) {  //验证验证码是否正确
								
	
								
								if (type==null||type.equals("s1")) {
									
									//验证用户是否经行注册过
									Student myUser = iRegisterMapper.getStuByName(userName,email);
									if (myUser!=null) {
										return DataUtils.print(data, "-3", "该用户已经注册过了");
									}
									
									Student student = new Student();
									
									student.setCreatetime(DataUtils.getDataTime()+"");
									student.setEmali(email);
									student.setPassword(DigestUtil.md5Hex(password));
									student.setSchoolid(schoolid);
									student.setSname(realName);
									student.setState(1);
									student.setSnumber(userName);
									
									//验证码正确存入数据
									iRegisterMapper.addStudent(student);
									
									//生成 token
									StuInfo stuInfo = new StuInfo();
									stuInfo.setCreatetime(DataUtils.getDataTime()+"");
									
									Integer sid = iStuLoginMapper.queryStudnetBySNumber(userName).getSid();
									stuInfo.setSid(sid);
									student.setCreatetime(DataUtils.getDataTime()+"");
									String token = IdUtil.simpleUUID();
									stuInfo.setToken(token);
									//存入 token 
									iRegisterMapper.addStuToken(stuInfo);
									
									return DataUtils.print(data, "200", "注册成功");
								 }
								else if (type.equals("t2")) {
							
									//验证用户是否经行注册过
									Teacher myUser = iRegisterMapper.getTeaByName(userName, email);
									if (myUser!=null) {
										return DataUtils.print(data, "-3", "该用户已经注册过了");
									}
									Teacher teacher = new Teacher();
									// tname,schoolid,tnumber,password,emali,createtime
									teacher.setTnumber(userName);
									teacher.setEmali(email);
									teacher.setPassword(DigestUtil.md5Hex(password));
									teacher.setCreatetime(DataUtils.getDataTime()+"");
									teacher.setTname(realName);
									teacher.setSchoolid(schoolid);
									System.out.println(teacher.toString());	
									iRegisterMapper.addTeacher(teacher);
									
									
									//生成token 
									
									TeacherInfo teacherInfo = new TeacherInfo();
									teacherInfo.setCreatetime(DataUtils.getDataTime()+"");
									String token = IdUtil.simpleUUID();
									teacherInfo.setToken(token);
									Integer tid = iTeacherMapper.queryTeacherBySNumber(userName).getTid();
									teacherInfo.setTid(tid);
									iRegisterMapper.addTeaToken(teacherInfo);
									
									return DataUtils.print(data, "200", "注册成功");
								}else {
									return DataUtils.print("-1", "请正确调用接口");
								}
							
								
							}else{
								//重新生成验证码
								iRegisterMapper.delCode(codeObj.getCodeid()); //删除当前验证码
								return DataUtils.print(data, "-5", "验证码错误");
							}
						}
						
					}else{
						return DataUtils.print(data, "-1", "请输入验证码");
					}
					
					
				}else{
					//密码不符合规定
					return DataUtils.print(data, "-1", "请输入六位以上的密码并且包含三种以上的字符");
				}
				
							
			}
			
			
		}
	}
	
	

	
	// 验证验证码 
	public boolean isCode(String mycode,String code ){
		return mycode.equals(code);	
	}

	public Map<String, Object> getSchool() {
		// TODO Auto-generated method stub
		//获取 school 
		List<School> schoolList = iRegisterMapper.getAllScool();
		//创建返回 的Map
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for (School school : schoolList) {
			Map<String, Object> dataP = new HashMap<String, Object>();
			dataP.put("id",school.getSchoolid());
			dataP.put("name", school.getSchoolName());
			dataList.add(dataP);
		}
		data.put("data", dataList);
		//返回数据
		return DataUtils.print(data, "200", "secess");
		
	}




	
	

}
