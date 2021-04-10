package cn.admin.service.student.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.admin.mapper.common.IRootTokenMapper;
import cn.admin.mapper.student.IStudentMapper;
import cn.admin.service.student.IStudentService;
import cn.guoke.pojo.Student;
import cn.guoke.utils.DataUtils;

/**
 * 学生service接口实现类
 * 
 * @author 梦伴
 *
 */
@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentMapper studentMapper;

	@Autowired
	private IRootTokenMapper rootTokenMapper;

	/**
	 * 查询所有学生
	 */
	@Override
	public Map<String, Object> queryAllStudent(String token) {
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
		List<Student> queryAllStudent = studentMapper.queryAllStudent();
		return DataUtils.MyPrint(200, "请求成功", queryAllStudent);
	}

	/**
	 * 修改一个学生的姓名
	 */
	@Override
	public Map<String, Object> changeStudent(String token, Student student) {
		// TODO Auto-generated method stub
		if (("".equals(token) || token == null) || (student.getSname() == null || student.getSid() == null )) {//判断参数是否非法调用
			return DataUtils.MyPrint(-2,"非法调用",null);
		}
		
		Map<String, Object> queryRootToken = rootTokenMapper.queryRootToken(token);//判断是否登录
		if (queryRootToken == null) {
			return DataUtils.MyPrint(-1,"未登录",null);
		}
		
		int result = studentMapper.changeStudent(student.getSname(),student.getSid());
		if (result == 0) {
			return DataUtils.MyPrint(-3,"修改失败",null);
		}
		
		return DataUtils.MyPrint(200,"修改成功",null);
	}

}
