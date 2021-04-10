package cn.guoke.service.teacher.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.guoke.mapper.common.ITeaTokenMapper;
import cn.guoke.mapper.teacher.ITeaExamMapper;
import cn.guoke.pojo.Course;
import cn.guoke.pojo.Examination;
import cn.guoke.pojo.Paper;
import cn.guoke.pojo.Teacher;
import cn.guoke.service.teacher.ITeaExamService;
import cn.guoke.utils.DataUtils;
import cn.guoke.utils.SnowflakeUtils;

@Service
public class TeaExamServiceImpl implements ITeaExamService {

	@Autowired
	private ITeaExamMapper teaExamMapper;

	@Autowired
	private ITeaTokenMapper teaTokenMapper;

	/**
	 * 创建考试
	 */
	@Override
	public Map<String, Object> createExam(String token, Examination examinfo) {
		// TODO Auto-generated method stub

		if ((token == null || "".equals(token)) || examinfo.getPaperid() == null || examinfo.getCid() == null) {
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Teacher teacher = teaTokenMapper.getTeaByToken(token);
		if (teacher == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}

		/**
		 * 开始设置默认值
		 */
		String announcements = examinfo.getAnnouncements();// 考前警示语
		if (announcements == null || "".equals(announcements.trim())) {// 清除空格字符串
			announcements = "请认真答题，注意考场纪律";// 设置默认值
		}
		String examinationmark = SnowflakeUtils.getSnowflake().toString();// 考试标识符
		int state = 0;// 考试状态默认值 0待考试
		String createtime = String.valueOf(System.currentTimeMillis());// 考试生成时间

		/**
		 * 创建考试
		 */
		int createExam = teaExamMapper.createExam(examinationmark, examinfo.getPaperid(), teacher.getTid(), state,
				createtime, announcements, examinfo.getCid());
		if (createExam != 1) {
			return DataUtils.MyPrint(-3, "创建失败", null);
		}

		return DataUtils.MyPrint(200, "创建成功", null);
	}

	/**
	 * 查询此老师创建的所有考试
	 */
	@Override
	public Map<String, Object> queryMyExam(String token) {
		// TODO Auto-generated method stub

		if (token == null || "".equals(token)) {
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Teacher teacher = teaTokenMapper.getTeaByToken(token);
		if (teacher == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}

		List<Examination> myExam = teaExamMapper.queryMyExam(teacher.getTid());

		return DataUtils.MyPrint(200, "请求成功", myExam);
	}

	/**
	 * 老师关闭考试
	 */
	@Override
	public Map<String, Object> closeExam(String token, int examinationid) {
		// TODO Auto-generated method stub
		
		if ((token == null || "".equals(token))) {
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Teacher teacher = teaTokenMapper.getTeaByToken(token);
		if (teacher == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}

		int result = teaExamMapper.closeExam(examinationid, teacher.getTid());
		if (result != 1) {
			return DataUtils.MyPrint(-3, "关闭失败", null);
		}

		return DataUtils.MyPrint(200, "关闭成功", null);
	}

	/**
	 * 获取老师自己 创建的所有课程(course)
	 */
	@Override
	public Map<String, Object> queryMyClass(String token) {
		// TODO Auto-generated method stub
		if (token == null || "".equals(token)) {
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Teacher teacher = teaTokenMapper.getTeaByToken(token);
		if (teacher == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}

		List<Course> myClass = teaExamMapper.queryMyClass(teacher.getTid());
		return DataUtils.MyPrint(200, "请求成功", myClass);
	}

	/**
	 * 获取老师自己 创建的所有试卷(paper)
	 */
	@Override
	public Map<String, Object> queryMyPaper(String token) {
		// TODO Auto-generated method stub
		if (token == null || "".equals(token)) {
			return DataUtils.MyPrint(-2, "非法调用", null);
		}

		Teacher teacher = teaTokenMapper.getTeaByToken(token);
		if (teacher == null) {
			return DataUtils.MyPrint(-1, "未登录", null);
		}

		List<Paper> myPaper = teaExamMapper.queryMyPaper(teacher.getTid());
		return DataUtils.MyPrint(200, "请求成功", myPaper);
	}

}
