package cn.guoke.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("school")
public class School {
	private Integer schoolid;
	private String schoolName;
	private String crtatetime;
	private String display;
}
