package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc  试卷表
 * @author 语录
 *
 */
@Data
public class Paper {

	private Integer paperid;
	private Integer tid;
	private String createtime;
	private String display;
	private String name;
	private String info;
	private String papercode;
}
