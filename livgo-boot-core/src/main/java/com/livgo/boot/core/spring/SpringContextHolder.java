package com.livgo.boot.core.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Description:获取Spring Bean的通用方法，任意位置都能获取到
 * Author:     gaocl
 * Date:       2018/1/4
 * Version:    V1.0.0
 * Update:     更新说明
 */
public class SpringContextHolder implements ApplicationContextAware {
	public static ApplicationContext applicationContext = null;
	
	/**
	 * 通过beanName获取spring管理的bean
	 * 
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * 根据class获取spring管理的bean
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		checkApplicationContext();
		return (T) applicationContext.getBeansOfType(clazz);
	}


	/**
	 * 检验ApplicationContext是否成功注入
	 * 
	 */
	private static void checkApplicationContext() {
		if (applicationContext == null) {
			//AsyncLogRecord.getInstance().error("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder",SpringContextHolder.class);
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}
}
