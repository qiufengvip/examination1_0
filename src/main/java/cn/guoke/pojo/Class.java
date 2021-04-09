package cn.guoke.pojo;

import lombok.Data;

/**
 * @Descent 班级获取 这里的班级是只有学校可以创建
 * @author 语录
 *
 */
@Data
public class Class {
	private Integer cid;
	private String cname;
	private String ccode;
	private String createtime;
	private String display;
	private String schoolid;
	private String Wishes;
}
