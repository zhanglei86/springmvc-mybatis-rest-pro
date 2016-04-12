package win.leizhang.system;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import jodd.util.StringUtil;

/**
 * 定制 Jackson 的 ObjectMapper
 *
 * @author huangyong
 * @since 1.0.0
 */

/*
 * 暂不使用
 */
public class CustomObjectMapper extends ObjectMapper {

	private boolean camelCaseToLowerCaseWithUnderscores = false;
	private String dateFormatPattern;

	public void setCamelCaseToLowerCaseWithUnderscores(boolean camelCaseToLowerCaseWithUnderscores) {
		this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
	}

	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}

	public void init() {
		// 排除值为空属性
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		// 进行缩进输出
		configure(SerializationFeature.INDENT_OUTPUT, true);
		// 将驼峰转为下划线
		if (camelCaseToLowerCaseWithUnderscores) {
			setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		// 进行日期格式化
		if (StringUtil.isNotEmpty(dateFormatPattern)) {
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			setDateFormat(dateFormat);
		}
	}
	
	/*
	<!-- Jackson的序列化行为进行定制，比如，排除值为空属性、进行缩进输出、将驼峰转为下划线、进行日期格式化等 -->
	<bean id="objectMapper" class="win.leizhang.system.CustomObjectMapper"
		init-method="init">
		<property name="camelCaseToLowerCaseWithUnderscores" value="false" />
		<property name="dateFormatPattern" value="yyyy-MM-dd HH:mm:ss" />
	</bean>

	<bean id="mappingJackson2HttpMessageConverter"
		class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
		<property name="objectMapper" ref="objectMapper" />
	</bean>
	 */
}
