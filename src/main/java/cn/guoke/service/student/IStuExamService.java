package cn.guoke.service.student;

import java.util.Map;

/** 
 * @Desc 考试信息获取的接口
 * @author 语录
 *
 */
public interface IStuExamService {

	/**
	 * @Desc 查询待答的考试
	 * @param token
	 * @return
	 */
	public Map<String, Object> getReplyExam(String token);	
	
	/**
	 * @Desc 获取的已经完成的考试
	 * @param token
	 * @return
	 */
	public Map<String, Object> getSeccExam(String token);
	
	
	
	/**
	 * @Descent 获取试卷信息
	 * @param token
	 * @param examid
	 * @return
	 */
	public Map<String, Object> getExamInfo(String token,Integer examid);
	
	
	/**
	 * @Desc 获取考前注意事项
	 * @param id
	 * @return
	 */
	public Map<String, Object> getAnnouncements(Integer id);
	

	
	
}
