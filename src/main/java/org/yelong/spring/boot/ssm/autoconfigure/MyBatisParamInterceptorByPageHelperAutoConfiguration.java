/**
 * 
 */
package org.yelong.spring.boot.ssm.autoconfigure;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.yelong.support.orm.mybaits.interceptor.MyBatisInterceptorSortUtils;
import org.yelong.support.orm.mybaits.interceptor.MyBatisPlaceholderMapInterceptor;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;

/**
 * 添加mybatis参数拦截器。
 * 该对象只有在使用分页插件时才会注入。
 * mybatis参数拦截器必须在分页拦截器之前执行
 * @author 彭飞
 * @date 2019年9月5日下午2:30:32
 * @version 1.2
 */
@Configuration
@ConditionalOnBean(PageHelperAutoConfiguration.class)
@AutoConfigureAfter(PageHelperAutoConfiguration.class)
public class MyBatisParamInterceptorByPageHelperAutoConfiguration {

	@Autowired
	private List<SqlSessionFactory> sqlSessionFactoryList;

	@PostConstruct
	public void addMyBaticParamInterceptor() {
		//System.out.println("--------------执行了添加拦截器ByPageHelper");
		sqlSessionFactoryList.forEach(x->{
			try {
				MyBatisInterceptorSortUtils.sortInterceptor(x.getConfiguration(),( (y,z) -> {
					if( y instanceof MyBatisPlaceholderMapInterceptor ) {
						return 1;
					}
					if( z instanceof MyBatisPlaceholderMapInterceptor ) {
						return -1;
					}
					return 0;
				}));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
}
