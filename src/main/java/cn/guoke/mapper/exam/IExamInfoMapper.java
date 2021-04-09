package cn.guoke.mapper.exam;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Mcq;
import cn.guoke.pojo.Paper;
import cn.guoke.pojo.Paperinfo;
import cn.guoke.pojo.Topic;

/**
 * @Desc 考场信息获取
 * @author 语录
 *
 */
public interface IExamInfoMapper {

	/*
	 * @Desc 获取考试
	 * @param examid
	 * @return
	 */
	@Select("SELECT * FROM examination WHERE examinationid =#{examinationid} ")
	public Examination getAnnouncements(Integer examinationid);
	

	/**
	 * @Desc 创建试卷
	 * @param paper
	 * @return
	 */
	@Insert("insert into paper(tid,createtime,name,info,papercode) VALUES(#{tid},#{createtime},#{name},#{info},#{papercode})")
	@Options(useGeneratedKeys=true, keyProperty="paperid", keyColumn="paperid") 
	public int addPaper(Paper paper);
	
	/**
	 * 添加选择题
	 * @param faqid
	 * @param gfcon
	 * @param answer
	 * @param analysis
	 * @return
	 */
	@Insert("insert into faq(faqid,gfcon,answer) VALUES(#{faqid},#{gfcon},#{answer})")
	public int addFaq(@Param("faqid")String faqid,@Param("gfcon")String gfcon,@Param("answer")String answer);
	 
	@Insert("insert into mcq(mcqid,mcqcon,A,B,C,D,answer) VALUES(#{mcqid},#{mcqcon},#{A},#{B},#{C},#{D},#{answer})")
	public int addMcq(@Param("mcqid")String mcqid,@Param("mcqcon")String mcqcon,@Param("A")String A,@Param("B")String B,@Param("C")String C,@Param("D")String D,@Param("answer")String answer);
	
	
	@Select("select * from paper where paperid=#{paperid}")
	public Paper getPaperById(String paperid);

	
	@Select("select * from paper where papercode=#{papercode}")
	public Paper getPaperByCode(String papercode);
	
	// 给题库中增加题目
	@Insert("insert into topic(topid,type,tid,score,createtime) VALUES(#{topid},#{type},#{tid},#{score},#{createtime})")
	public int  addTopic(@Param("topid")String topid,@Param("type")String type,@Param("tid")Integer tid,@Param("score")String score,@Param("createtime")String createtime);
	
	/**
	 * @Desc 查询题库
	 * @param topid
	 * @return
	 */
	@Select("select * from topic where topid=#{topid}")
	public Topic getTopidById(String topid);

	@Insert("insert into paperinfo(paperid,topicid) VALUES(#{paperid},#{topicid})")
	@Options(useGeneratedKeys=true, keyProperty="paperinfoid", keyColumn="paperinfoid") 
	public int addPaperinfo(@Param("paperid")Integer paperid,@Param("topicid")Integer topicid);
	
	/************************************************这里是用来获取试卷信息的**************************************************************/
	
	
	// 获取试卷的的内容
	@Select("select * from  paperinfo WHERE paperid=#{paperid}")
	public List<Paperinfo> getPaperInfoByPid(Integer paperid);
	
	//获取题库内容
	@Select("select * from  topic WHERE topicid=#{#} and display =0 ")
	public Topic getTopicByID(Integer topicid);
	
	//获取题目内容
	@Select("select * from mcq where mcqid=#{mcqid}")
	public Mcq  getMcq(String mcqid);
	
	//删除 题库中的内容
	@Update("UPDATE topic SET display='-1' WHERE topicid =#{topicid}  ")
	public int delTopic(Integer topicid);
	
	//删除 题库中的内容
	@Update("UPDATE topic SET display='-1' WHERE topid =#{topid} ")
	public int delTopicByid(Integer topid);
	
	
	
	
	
	
}
