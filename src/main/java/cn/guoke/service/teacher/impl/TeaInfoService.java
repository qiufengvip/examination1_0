package cn.guoke.service.teacher.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.ITeaTokenMapper;
import cn.guoke.mapper.teacher.ITeaInfoMapepr;
import cn.guoke.mapper.teacher.IteaSourseMapper;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Student;
import cn.guoke.pojo.Teacher;
import cn.guoke.service.teacher.ITeaInfoService;
import cn.guoke.utils.DataUtils;

@Service
public class TeaInfoService implements ITeaInfoService{

	@Autowired
	private IteaSourseMapper iSourseMapper;
	
    @Autowired
	private ITeaInfoMapepr iTeaInfoMapepr;

    
	@Autowired
	private ITeaTokenMapper iTeaTokenMapper;
    
    /**
     * @Desc 获取基本信息
     */
	public Map<String, Object> getTeaInfoIndex(String token) {
		
	
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
         Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }else{
			 //获取基本信息
			 Map<String, Object> teaInfo = new HashMap<String, Object>();
			 List<Course> myCourse = iSourseMapper.getMyAllCreate(teacher.getTid());
			 teaInfo.put("cnumber", myCourse==null?0:myCourse.size()); //课程数
			  List<Student> students = iSourseMapper.getStudnetByTid(teacher.getTid());
			 teaInfo.put("snumber", students==null?0:students.size()); //学生人数
			 data.put("data", teaInfo);	 
			 
		 }
		
		
		return DataUtils.print(data);
	}

	/**
	 * @Desc 获取两个月内的考试信息
	 */
	public Map<String, Object> getTeaLatelyExam(String token) {
		Map<String, Object> data = new HashMap<String, Object>(); //创建返回的数据
		//验证字段是否为空
		if (token==null||token.equals("")) {
			return DataUtils.print(data, "-1", "请重新登录");
		}
		//获取学生信息
         Teacher teacher = iTeaTokenMapper.getTeaByToken(token);
		 if (teacher==null) {  // 这里是验证 token 的
			 return DataUtils.print(data, "-1", "请重新登录");
		 }else{
			 //查询近期的考试
			  List<Examination> examList = iTeaInfoMapepr.getExaminationByTid(teacher.getTid());
			  if (examList==null) {  //验证是否创建过考试
				 return DataUtils.print(data,"-1","您还没有创建过考试");
	     	  }else{
	     		  //创建返回的数据  
	     		  List<String[]> dataList =new  ArrayList<String[]>();
	     		  for(Examination exam : examList){
	     			  //创建返回的数据
	     			   String[] dataArr = new String[2];
	     			  //获取两格月前的时间
	     			  Long nowTime = DataUtils.getDataTime()-Long.parseLong("5259600000");
	     			  //获取数据数据库存入的时间
	     			  Long createTime = Long.parseLong(exam.getCreatetime());
	     			  if (createTime-nowTime>0) {
	     				 dataArr[0]=DataUtils.stampToDate(exam.getCreatetime());
	     				 dataArr[1]=exam.getExaminationmark();
	     				 dataList.add(dataArr);
					   }
	     			  
	     			  
	     		  }
	     		  data.put("data", dataList);
	     		  
	     		  
	     		 return DataUtils.print(data);
	     	  }
			 
			 
			 
		 }
		
		
	}
    
    
	

	public static void main(String[] args) {
		System.out.println(DataUtils.getDataTime());
		
	}
    
}
