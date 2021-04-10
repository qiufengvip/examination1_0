package cn.guoke.service.teacher.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.ITeaTokenMapper;
import cn.guoke.mapper.student.IStuExamInfoMapper;
import cn.guoke.mapper.teacher.IteaSourseMapper;
import cn.guoke.pojo.Grade;
import cn.guoke.pojo.Teacher;
import cn.guoke.service.teacher.ITeaCoursService;
import cn.guoke.utils.DataUtils;
import javafx.geometry.Side;

/**
 * @Desc 获取成绩
 * @author 语录
 *
 */
@Service
public class TeaCourService implements ITeaCoursService {

	@Autowired
	private IteaSourseMapper  iStuExamInfoMapper;
	
	
	@Autowired
	private ITeaTokenMapper iTeaTokenMapper;
	
	@Override
	public Map<String, Object> getCourse(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> getGradeMyStu(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
	 * @Desc 增加课程
	 */
	@Override
	public Map<String, Object> addCourse(String token, String name) {
		
		
		
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
           Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 
		iStuExamInfoMapper.addCourse(name , teacher.getTid(), DataUtils.getDataTime()+"");
		return DataUtils.print(data);
	}

	@Override
	public Integer  getGrade(String snumber, String  code,String score) {

		 
		 
		 // 获取 学生id
		  Integer sid = iStuExamInfoMapper.getStuBySnum(snumber).getSid();
		  //获取试卷 id
		  Integer pid = iStuExamInfoMapper.getPaperByCode(code).getPaperid();
		  //成绩
		   		
		return iStuExamInfoMapper.addGrde(sid, pid, score);
	}

	
	
	
	
}
