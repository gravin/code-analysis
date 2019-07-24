package com.codeanalysis;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


/**
 * <pre>
 * 该类用于管理注解 {@link } 的所有类。
 * 1、在应用启动时，将@ApiController 的类加载到内存中。
 * 2、框架通过此类获取 控制器。
 * 3、Bean命名规则：根据ApiController的 {type}_{name}_{version}进行命名
 * </pre>
 *
 * <context:component-scan name-generator=
 * "com.ting.common.entry.ApiControllerBeanManager">
 *
 * @author 丁伟
 * @date 2017年7月2日
 * @version 1.0
 */
public class ApiControllerBeanManager extends AnnotationBeanNameGenerator implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiControllerBeanManager.class);
    private static final String APICONTROLLER_ANNOTATION_CLASSNAME = "com.codeanalysis.ApiController";

    /** Spring Application Context */
    private static volatile ApplicationContext appContext;

    /** 所有控制器信息 */
    private static final List<ApiControllerBeanDto> API_CONTROLLER_BEANS = new ArrayList<>();

    /**
     * 设置应用，Spring 调用.ApplicationContextAware
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        if (ApiControllerBeanManager.appContext == null) {
            synchronized (ApiControllerBeanManager.class) {
                if (ApiControllerBeanManager.appContext == null) {
                    ApiControllerBeanManager.appContext = applicationContext;
                }
            }
        }
    }

    public static ApplicationContext getApplicationContext() {
        return appContext;
    }

    /** 获取所有控制器信息 */
    public static List<ApiControllerBeanDto> getApiControllerBeans() {
        return API_CONTROLLER_BEANS;
    }

    /** 清除所有控制器信息，避免占用内存 */
    public static void clearApiControllerBeans() {
        API_CONTROLLER_BEANS.clear();
    }

    public static Object getBean(String beanId) {
        if (beanId == null || beanId.length() == 0) {
            return null;
        }
        try {
            Object bean = appContext.getBean(beanId);
            if (bean == null) {
                LOGGER.warn("获取不到bean:{}", beanId);
            }
            return bean;
        } catch (Exception e) {
            LOGGER.warn("获取bean异常:{}", beanId);
            return null;
        }
    }

    /**
     * 获取Bean ，获取不到返回null
     *
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            return appContext.<T>getBean(clazz);
        } catch (Exception e) {
            LOGGER.warn("获取不到bean:{}", clazz);
            return null;
        }

    }

    /**
     * 获取Bean ，获取不到返回null
     *
     * @param name
     * @param clazz
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            return appContext.getBean(name, clazz);
        } catch (Exception e) {
            LOGGER.warn("获取不到bean:{}，{}", name, clazz);
            return null;
        }
    }

    /**
     * 获取ApiController, 获取注解@ApiController 的控制器
     *
     * @param ctrlType
     *            控制器类型
     * @param ctrlName
     *            控制器名称
     * @param ctrlVersion
     *            控制器版本
     * @return
     */
    public static Object getApiController(String ctrlType, String ctrlName, String ctrlVersion) {
        if (StringUtils.isEmpty(ctrlType) || StringUtils.isEmpty(ctrlName) || StringUtils.isEmpty(ctrlVersion)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (appContext == null) {
            throw SysException.SYSTEM_ERROR.format("未加载 ApplicationContext");
        }
        try {
            return appContext.getBean(getControllerName(ctrlType, ctrlName, ctrlVersion));
        } catch (Exception e) {
            LOGGER.warn("获取不到bean", e);
            return null;
        }
    }

    /** 生成BeanName */
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (isApiControllerBean(definition)) {
            return generateControllerName(definition);
        }
        return super.generateBeanName(definition, registry);
    }

    @Override
    protected String buildDefaultBeanName(BeanDefinition definition) {
        // 用类全路径作为beanName
        String shortClassName = definition.getBeanClassName();
        return Introspector.decapitalize(shortClassName);
    }

    /** 判断是不是控制器 */
    private boolean isApiControllerBean(BeanDefinition definition) {
        return typeHasAnnotation(definition, APICONTROLLER_ANNOTATION_CLASSNAME);
    }

    private boolean typeHasAnnotation(BeanDefinition definition, String annotationName) {
        if (!(definition instanceof AnnotatedBeanDefinition)) {
            return false;
        }
        AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> annotatedTypes = amd.getAnnotationTypes();
        for (String annotatedType : annotatedTypes) {
            if (annotatedType.equals(annotationName)) {
                return true;
            }
        }
        return false;
    }

    /** 产生控制器的Bean名称 */
    private String generateControllerName(BeanDefinition definition) {
        AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
        return generateApiControllerDefaultBeanName(annotatedDef);
    }

    private String generateApiControllerDefaultBeanName(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata amd = annotatedDef.getMetadata();
        Set<String> annotatedTypes = amd.getAnnotationTypes();
        for (String annotatedType : annotatedTypes) {
            AnnotationAttributes attributes = attributesFor(amd, annotatedType);
            if (annotatedType.equals(APICONTROLLER_ANNOTATION_CLASSNAME)) {
                String type = attributes.getString("type");
                String name = attributes.getString("name");
                String version = attributes.getString("version");
                LOGGER.info("加载，type:{}, 名称:{}, 版本:{}", type, name, version);
                String beanName = getControllerName(type, name, version);
                API_CONTROLLER_BEANS.add(new ApiControllerBeanDto(type, name, version, beanName));
                return beanName;
            }
        }
        return "";
    }

    private static AnnotationAttributes attributesFor(AnnotationMetadata metadata, String annoClassName) {
        return AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(annoClassName, false));
    }

    private static String getControllerName(String ctrlType, String ctrlName, String ctrlVersion) {
        return ctrlType + "_" + ctrlName + "_" + ctrlVersion;
    }
}
