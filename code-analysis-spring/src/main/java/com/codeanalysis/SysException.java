
package com.codeanalysis;

/**
 * 系统异常。编码前缀：9998。 <br>
 * 注意：请不要在业务系统中引用该异常类。
 * 
 * @author 顾用峰
 * @version 1.0
 * @date 2017年9月4日 下午10:21:37
 */
public class SysException extends BizException {
	private static final long serialVersionUID = -4039545330355705367L;

	/*** 以下从 BizException 复制过来，暂时不改变编码值 ***/
	/** 系统错误：{0} */
	public static final BizException SYSTEM_ERROR = new BizException(99990016, "系统错误：{0}");
	/** 创建业务异常新实例失败 */
	public static final BizException NEW_EXCEPTION_INSTANCE_FAILED = new BizException(99990003, "创建业务异常新实例失败");
	/** 请求的业务参数异常。 */
	public static final BizException BIZ_DATA_ERROR = new BizException(99990013, "请求的业务参数解析异常。");
	/** 系统不舒服，请稍后再试 */
	public static final BizException SYSTEM_INTERNAL_ERROR = new BizException(99990019, "系统不舒服，请稍后再试");
	/** 调用的服务不存在 */
	public static final BizException SERVICE_NOT_EXIST = new BizException(99990021, "调用的服务不存在");
	/** 服务类型错误 */
	public static final BizException SERVICE_TYPE_ERROR = new BizException(99990022, "服务类型错误");
	/*** END-- 以下从 BizException 复制过来，暂时不改变编码值 ***/
	
	
	//以下为SysException
	
	/** 克隆上下文环境失败 */
	public static final SysException CLONE_CONTEXT_FAILED = new SysException(99980001, "克隆上下文环境失败");
	/** 时间戳过期 */
	public static final SysException TIMESTAMP_EXPIRED = new SysException(99980002, "时间戳过期");
	
	protected SysException(int code, String msg) {
		super(code, msg);

	}
}