package cn.guoke.service.student.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.IStuTokenMapper;
import cn.guoke.mapper.student.IStuGetMapper;
import cn.guoke.mapper.student.IStuGradeMapper;
import cn.guoke.pojo.Grade;
import cn.guoke.pojo.Student;
import cn.guoke.service.student.IStuGradeService;
import cn.guoke.utils.DataUtils;

/**
 * @Desc 成绩的业务层
 * @author 语录
 *
 */
@Service
public class StuGradeService implements IStuGradeService{

	@Autowired
	private IStuGradeMapper iStuGradeMapper;
    @Autowired 
	private IStuTokenMapper iStuTokenMapper;
	
    @Autowired
    private IStuGetMapper iStuGetMapper;
	//获取获取
	public Map<String, Object> getMyAllGrade(String token) {
		//创建返回的数据
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		
		//获取学生信息
		 Student studnet= iStuTokenMapper.getStuByToken(token);	
		 if (studnet==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }
		 
		 //获取成绩 信息
		 List<Grade> gradeList = iStuGradeMapper.getGrade(studnet.getSid());
		 if (gradeList==null||gradeList.size()==0) {
			 return DataUtils.print(data, "-2", "您还没有成绩");
		 }else {
			 
			 
		    List<Map<String, Object>> dataList =  new ArrayList<Map<String, Object>>();
		   for(Grade grade : gradeList){  //分装学生成绩
				//获取我的所有成绩
			   Map<String, Object> dataMap = new  HashMap<String, Object>();
			   
			   dataMap.put("teacher", iStuGetMapper.getTeacherByPaper(grade.getPaperid()).getTname());  //教师
			   dataMap.put("paperid", grade.getPaperid());  //试卷id
			   dataMap.put("score", grade.getScore());  //考试成绩   
			   dataList.add(dataMap);
		   }
		   data.put("data", dataList);	
		   return DataUtils.print(data, "0","succeed");
		}
		 
		
	}
	
	

}
