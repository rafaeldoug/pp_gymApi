package br.cesed.si.pp.config.swagger;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DelegatingLinkRelationProvider;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.plugin.core.support.PluginRegistryFactoryBean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import br.cesed.si.pp.model.Usuario;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations extends WebMvcConfigurationSupport {

//	@Bean
//	public LinkDiscoverers discoverers() {
//		List<JsonPathLinkDiscoverer> plugins = new ArrayList<>();
//		plugins.add(new HalLinkDiscoverer());
//		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
//	}

	@Bean
	public LinkRelationProvider provider() {
		return new EvoInflectorLinkRelationProvider();
	}

	@Bean
	@Primary
	public PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext> myPluginRegistryProvider() {

		PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext> factory = new PluginRegistryFactoryBean<>();

		factory.setType(LinkRelationProvider.class);
		Class<?> classes[] = new Class<?>[1];
		classes[0] = DelegatingLinkRelationProvider.class;
		factory.setExclusions(classes);

		return factory;
	}

	@Bean
	public Docket gymApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.cesed.si.pp"))
				.paths(PathSelectors.ant("/**")).build()
				.ignoredParameterTypes(Usuario.class)
				.globalOperationParameters(
						Arrays.asList(new ParameterBuilder()
								.name("Authorization")
								.description("Header para Token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false).build()))
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Spring Boot REST API")
				.description("\"Spring Boot REST API for Gym (Projeto PP)\"").version("0.0.1")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
