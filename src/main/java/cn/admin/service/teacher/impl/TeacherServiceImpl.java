package cn.admin.service.teacher.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.admin.mapper.common.IRootTokenMapper;
import cn.admin.mapper.teacher.ITeacherMapperAdmin;
import cn.admin.service.teacher.ITeacherService;
import cn.guoke.pojo.Teacher;
import cn.guoke.utils.DataUtils;

@Service
public class TeacherServiceImpl implements ITeacherService{

	@Autowired
	private ITeacherMapperAdmin teacherMapper;
	
	@Autowired
	private IRootTokenMapper rootTokenMapper;
	
	/**
	 * 查询所有老师
	 */
	@Override
	public Map<String, Object> queryAllTeacher(String token) {
		// TODO Auto-generated method stub
		if ("".equals(token) || token == null) {// 判断是否非法调用
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Map<String, Object> queryRootToken = rootTokenMapper.queryRootToken(token);// 判断是否登录
		if (queryRootToken == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}
		
		/**
		 * 验证登录成功之后
		 */
		List<Teacher> queryAllTeacher = teacherMapper.queryAllTeacher();
		return DataUtils.MyPrint(200, "请求成功", queryAllTeacher);
	}
	
	
	/**
	 * 修改老师名字
	 */
	@Override
	public Map<String, Object> changeTeacher(String token, Teacher teacher) {
		// TODO Auto-generated method stub
		if (("".equals(token) || token == null) || (teacher.getTname() == null || teacher.getTid() == null )) {//判断参数是否非法调用
			return DataUtils.MyPrint(-2,"非法调用",null);
		}
		
		Map<String, Object> queryRootToken = rootTokenMapper.queryRootToken(token);//判断是否登录
		if (queryRootToken == null) {
			return DataUtils.MyPrint(-1,"未登录",null);
		}
		
		int result = teacherMapper.changeTeacher(teacher.getTname(),teacher.getTid());
		if (result == 0) {
			return DataUtils.MyPrint(-3,"修改失败",null);
		}
		
		return DataUtils.MyPrint(200,"修改成功",null);
	}
	
}
