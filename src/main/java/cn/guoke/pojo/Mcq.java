package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc 选择题
 * @author 语录
 *
 */
@Data
public class Mcq {

	private String mcqid;
	private String mcqcon;
	private String A;
	private String B;
	private String C;
	private String D;
	private String answer;
	private String analysis;
}
