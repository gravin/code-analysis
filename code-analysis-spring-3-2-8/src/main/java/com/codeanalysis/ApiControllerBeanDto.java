package com.codeanalysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * api控制器对象DTO
 * @author 陈章伟
 * @time 2018年5月27日下午3:09:44
 */
public class ApiControllerBeanDto implements Serializable {
	
	private static final long serialVersionUID = 6808136174969023263L;

	private String ctrlType;
	
	private String ctrlName;
	
	private String ctrlVersion;
	
	private String beanName;
	
	private List<String> methodNames;

	public ApiControllerBeanDto() {
		
	}

	public ApiControllerBeanDto(String ctrlType, String ctrlName, String ctrlVersion, String beanName) {
		super();
		this.ctrlType = ctrlType;
		this.ctrlName = ctrlName;
		this.ctrlVersion = ctrlVersion;
		this.beanName = beanName;
		this.methodNames = new ArrayList<String>();
	}


	public String getCtrlType() {
		return ctrlType;
	}


	public void setCtrlType(String ctrlType) {
		this.ctrlType = ctrlType;
	}


	public String getCtrlName() {
		return ctrlName;
	}


	public void setCtrlName(String ctrlName) {
		this.ctrlName = ctrlName;
	}


	public String getCtrlVersion() {
		return ctrlVersion;
	}


	public void setCtrlVersion(String ctrlVersion) {
		this.ctrlVersion = ctrlVersion;
	}


	public String getBeanName() {
		return beanName;
	}


	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}


	public List<String> getMethodNames() {
		return methodNames;
	}


	public void setMethodNames(List<String> methodNames) {
		this.methodNames = methodNames;
	}

}
