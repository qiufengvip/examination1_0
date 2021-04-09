package cn.guoke.pojo;


import java.util.List;

import lombok.Data;

/**
 * @Desc 课程信息表
 * @author 语录
 *
 */
@Data
public class CourseInfo {

	private Integer cinfoid;
	private Integer cid;
	private Integer sid;
	private String createtime;
	private String display;
	private Integer state;
	private Student student;

}
