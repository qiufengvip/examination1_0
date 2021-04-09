package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc 学生答题记录
 * @author 语录
 *
 */
@Data
public class Answer {

	private String answerid;
	private String sid;
	private String answer;
	private String paperinfoid;
	private String grade;
	
}
