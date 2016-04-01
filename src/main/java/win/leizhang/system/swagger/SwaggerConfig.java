package win.leizhang.system.swagger;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xiaohui on 2016/1/14.
 */
@Configuration
@EnableSwagger
public class SwaggerConfig {

	private SpringSwaggerConfig springSwaggerConfig;

	/**
	 * Required to autowire SpringSwaggerConfig
	 */
	@Autowired
	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * 自定义实现 customImplementation
	 * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
	 * framework - allowing for multiple swagger groups i.e. same code base
	 * multiple swagger resource listings.
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(apiInfo()).includePatterns(".*?");
	}

	/*
	 * "标题 title",
	 * "描述 description", 
	 * "termsOfServiceUrl", 
	 * "联系邮箱 contact email",
	 * "许可证的类型 license type", 
	 * "许可证的链接 license url"
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("springmvc-mybatis-rest-pro for Swagger-ui", "rest development croe framework", "My Apps API terms of service",
				"s_sir@qq.com", "The MIT License (MIT)", "http://rem.mit-license.org/");
		//ApiInfo apiInfo = new ApiInfo("InternshipTour-beetour", "实习实践，文化旅游，海外留学生，国内夏令营", "My Apps API terms of service","s_sir@qq.com", "co", "My Apps API License URL");
		return apiInfo;
	}
}
