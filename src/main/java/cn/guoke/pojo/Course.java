package cn.guoke.pojo;

import java.util.List;

import lombok.Data;

/**
 * @Desc 课程表
 * @author 语录
 *
 */
@Data
public class Course {

	private Integer cid;
	private String cname;
	private Integer tid;
	private String createtime;
	private String display;
	private List<CourseInfo> courseInfoList;
	
}
