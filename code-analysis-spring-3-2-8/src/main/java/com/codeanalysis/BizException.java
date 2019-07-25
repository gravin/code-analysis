package com.codeanalysis;


import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.text.MessageFormat;

/**
 * 业务异常<br>
 * 用途：用于在处理业务时，向框架抛出异常，框架将该异常作为错误信息进行输出。<br>
 * 使用场景：比如在深层业务代码判断参数无正确，就可以直接抛出该异常。<br>
 * 该异常可以控制事务是否回滚，如果不明确指定是否回滚，则code不等于0时回滚，等于0是不回滚。<br>
 * 
 * <pre>
 * code rule：{2}{2}{4}
 *            |  |  |  
 *           sys |  |
 *            module|
 *                error
 * 如：[10020001]前四位数为系统+模块编号，后4位为错误代码
 * 
 * 10xx - 账户平台
 * 11xx - 基础平台
 * 12xx - 支付结算平台
 * 13xx - 通用平台
 * 14xx - 电商平台
 * 15xx - 运营平台
 * 16xx - 运营活动平台
 * 17xx - 停车场平台
 * 18xx - 小Q平台
 * 19xx - 门禁平台
 * 
 * 810x - biz-common 账户平台
 * 811x - biz-common 基础平台
 * 812x - biz-common 支付结算平台
 * 813x - biz-event  业务事件
 * 
 * 90xx - 调度平台
 * 
 * 9995 - APP调用 - 框架级别
 * 9996 - 回调服务 - 框架级别
 * 9997 - 协作服务 - 框架级别
 * 9998 - 系统错误 - 框架级别
 * 
 * 9999 - 公共异常
 * </pre>
 * 
 * 
 * @author 顾用峰
 * @date 2016年8月7日 下午3:59:04
 */
public class BizException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 7231281873355632235L;

	/** 参数{0}超出范围 */
	public static final BizException ARG_OUT_RANGE = new BizException(99990002, "参数{0}超出范围");
	/** 服务返回结果对象为空对象 */
	public static final BizException RESULT_IS_NULL = new BizException(99990005, "服务返回结果对象为空对象");
	/** 启动一个数据库事务失败 */
	public static final BizException BEGIN_TRANS_FAILED = new BizException(99990006, "启动一个数据库事务失败");
	/** 参数{0}不能为空 */
	public static final BizException COMMON_PARAMS_NOT_NULL = new BizException(99990007, "参数{0}不能为空");
	/** 参数{0}非法 */
	public static final BizException COMMON_PARAMS_IS_ILLICIT = new BizException(99990008, "参数{0}非法");
	/** 你操作太频繁了，请歇一会儿！ */
	public static final BizException OPT_TOO_FREQUENTLY = new BizException(99990014, "你操作太频繁了，请歇一会儿！");
	/** 数据库操作失败:{0} */
	public static final BizException DB_OPERATOR_FAILED = new BizException(99990023, "数据库操作失败:{0}");
	/** 没有访问该系统的权限 */
	public static final BizException NO_SYS_AUTH = new BizException(99990024, "你没有访问该系统的权限");
	/** 没有访问该接口的权限 */
	public static final BizException NO_INTERFACE_AUTH = new BizException(99990025, "你没有访问该接口的权限");

	
	protected int code;
	protected String msg;
	protected boolean isRollbackTrans;

	/***
	 * 以后请不是使用该方法。
	 * 
	 * @param code
	 * @param msg
	 */
	protected BizException(int code, String msg) {
		this(code, msg, code != 0);
	}

	protected BizException(int code, String msg, boolean isRollback) {
		super(msg);
		this.code = code;
		this.msg = msg == null ? "" : msg;
		this.isRollbackTrans = isRollback;
	}


	/***
	 * 格式话消息.
	 * 
	 * @param args
	 * @return
	 */
	public BizException format(Object... args) {
		return this.newInstance(this.isRollbackTrans, MessageFormat.format(this.msg, args));
	}

	/**
	 * 创建新实例。
	 * 
	 * @param isRollback 是否回滚。true：回滚;false：不回滚
	 * @param msg 覆盖原有消息的新消息
	 * @return
	 */
	public BizException newInstance(boolean isRollback, String msg) {
		try {
			Class<?> clazz = this.getClass();
			Constructor<?> constructor;
			constructor = clazz.getDeclaredConstructor(new Class[] { int.class, String.class });
			constructor.setAccessible(true);
			BizException be;
			be = (BizException) constructor.newInstance(this.code, msg);
			be.isRollbackTrans = isRollback;
			return be;
		} catch (Exception e) {
			throw SysException.NEW_EXCEPTION_INSTANCE_FAILED.newInstance(true, "创建业务异常新实例失败" + e.toString());
		}
	}

	/**
	 * 创建新实例。
	 * 
	 * @param isRollback 是否回滚。true：回滚;false：不回滚
	 * @return
	 */
	public BizException newInstance(boolean isRollback) {
		return newInstance(isRollback, this.msg);
	}

	/**
	 * 获取状态吗
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 获取中文信息
	 * 
	 * @return
	 */
	public String getMsg() {
		return this.msg;
	}

	/**
	 * 获取是否回滚事务
	 * 
	 * @return
	 */
	public boolean isRollbackTrans() {
		return isRollbackTrans;
	}

	@Override
	public String toString() {
		return String.format("业务异常=%s, code=%s, msg=%s, isRollback=%s", this.getClass().getSimpleName(), this.code,
				this.msg, this.isRollbackTrans);
	}
}
