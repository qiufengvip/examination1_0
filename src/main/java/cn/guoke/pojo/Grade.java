package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc 成绩表
 * @author 语录
 *
 */
@Data
public class Grade {

	private Integer gradeid;
	private Integer sid;
	private Integer paperid;
	private String score;

}
