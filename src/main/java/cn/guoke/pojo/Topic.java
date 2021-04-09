package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc 题库表
 * @author 语录
 *
 */
@Data
public class Topic {

	private Integer topicid;
	private String topid;
	private String type;
	private String tid;
	private String score;
	private String display;
	private String createtime;	
}
