package cn.guoke.pojo;

import lombok.Data;

/**
 * @Desc 考场表
 * @author 语录
 *
 */
@Data
public class Examination {

	private String examinationmark;
	private Integer examinationid;
	private Integer paperid;
	private Integer tid;
	private String state;
	private String createtime;
	private String begintime;
	private String announcements;
	private Integer cid;
}
