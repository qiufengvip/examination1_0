package cn.guoke.service.student.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.IStuTokenMapper;
import cn.guoke.mapper.student.IStuClassMapper;
import cn.guoke.pojo.Class;
import cn.guoke.pojo.Student;
import cn.guoke.service.student.IStuClassService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 
 * @author 语录
 *
 */
@Service
public class StuClassServiceImpl implements IStuClassService{

	@Autowired
	private IStuTokenMapper iStuTokenMapper;
	
	@Autowired
	private IStuClassMapper iStuClassMapper;
	
	/**
	 * @Desc 加入班级
	 */
	public Map<String, Object> addClass(String token, String cLassCode) {

		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		else if (cLassCode==null||cLassCode.equals("")) {
			return DataUtils.print(data, "-3", "请输入邀请码");
		}
		
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }		
		 //验证是否已经加入班级
		 
		 //验证验证码是否存在
		 Class classs = iStuClassMapper.getClassByCode(cLassCode);
		 if (classs==null) {
			 return DataUtils.print(data, "-2", "邀请码错误");
		 }else { //存在

			 //加入班级
			 int count = iStuClassMapper.updateStuClass(classs.getCid(), studnet.getSid());
			 if (count>0) {
				//封装数据
				Map<String, Object> dataClass = new HashMap<String, Object>();
				//
				dataClass.put("name", classs.getCname());  //班级姓名 
				dataClass.put("createTime", DataUtils.stampToDate(classs.getCreatetime())); //创建时间
				dataClass.put("count", iStuClassMapper.getStudentByCid(studnet.getCid()).size()); // 人数
				dataClass.put("schoolName", iStuClassMapper.getSchoolById(studnet.getSchoolid()).getSchoolName()); // 学校
				dataClass.put("班级寄语", "没有太晚的开始，不如就从今天行动。总有一天，那个一点一点可见的未来，会在你心里，也在你的脚下慢慢清透。生活，从不亏待每一个努力向上的人。"); // 寄语
				data.put("data", dataClass);
				return DataUtils.print(data, "200", "加入成功");
			 }else {
				 return DataUtils.print(data, "-4", "您已经存在该班级了");
			}
		 }
		 

	}


	/**
	 * @Descent 获取班级信息
	 */
	public Map<String, Object> getClass(String token) {
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }else{
		    Class classs = iStuClassMapper.getClassByCid(studnet.getCid());
		    data.put("data", classs);	
		   return DataUtils.print(data);
		 }

	}

	
}
