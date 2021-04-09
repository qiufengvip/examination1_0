package cn.guoke.service.exam;

import java.util.Map;

/**
 * @Descent 
 * @author 语录
 *
 */
public interface IExamService {


	/**根据id试卷信息获取信息 这个是不获取答案的
	 * @Desc 
	 * @param pid
	 * @return
	 */
	public Map< String, Object> getExamPar(Integer pid);
	
	
	/**
	 * @Desc 带答案的考试信息
	 * @param token
	 * @param pid
	 * @return
	 */
	public Map<String,Object> getExamAcc(String token, String parpercode);
	
	/**
	 * @Desc 考生查看试卷 带正确答案
	 * @param token
	 * @param pid
	 * @return
	 */
	public Map<String, Object> getStuExamPid(String token,Integer pid);
	
	
	/**
	 * @Desc 教师批改试卷 这里是赋值的
	 * @param token
	 * @param pid
	 * @return
	 */
	public Map<String, Object> getTeaExamPid(String token,Integer pid);
	
	
	/**
	 * @Desc 批改试卷
	 * @param token
	 * @param data
	 * @return
	 */
	public Map<String, Object> getCorrect(String token,String data);
	
	/**
	 * @Desc 创建试卷
	 * @param token
	 * @return
	 */
	public Map<String, Object> setExam(String token);
	
	/**
	 * @Desc 给试卷中添题目
	 * @param token
	 * @param tipid
	 * @return
	 */
	public Map<String, Object> setTeaTip(String token,String tipid);
	
	
	
	public Map<String, Object> setPar(String token,String tips);
	
}
