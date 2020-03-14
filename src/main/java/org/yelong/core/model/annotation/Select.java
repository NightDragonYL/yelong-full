/**
 * 
 */
package org.yelong.core.model.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(TYPE)
/**
 * 标注在model上面。
 * 替换默认的查询语句
 * @author 彭飞
 * @date 2019年9月30日下午2:46:22
 * @version 1.2
 */
public @interface Select {

	/**
	 * 查询语句。默认： select alias.* from tableName alias
	 * @author 彭飞
	 * @date 2019年9月30日下午2:47:08
	 * @version 1.2
	 * @return
	 */
	String value() default "";
	
	
	
}
