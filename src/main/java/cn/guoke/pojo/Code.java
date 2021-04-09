package cn.guoke.pojo;

import lombok.Data;


/**
 * @December 验证码表
 * @author 语录
 *
 */
@Data
public class Code {

	private String codeid;
	private String code;
	private String createtime;
	private String emali;
	private String identification;
	private String display;
}
