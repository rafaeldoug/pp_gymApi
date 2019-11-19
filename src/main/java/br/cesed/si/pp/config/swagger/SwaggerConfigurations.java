package br.cesed.si.pp.config.swagger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.JsonPathLinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.hal.HalLinkDiscoverer;
import org.springframework.hateoas.server.LinkRelationProvider;
import org.springframework.hateoas.server.core.DelegatingLinkRelationProvider;
import org.springframework.hateoas.server.core.EvoInflectorLinkRelationProvider;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.plugin.core.support.PluginRegistryFactoryBean;

import br.cesed.si.pp.model.Usuario;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations {
	
	@Bean
	public LinkDiscoverers discoverers() {
		List<JsonPathLinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new HalLinkDiscoverer());
		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}

	@Bean
	public LinkRelationProvider provider() {
		return new EvoInflectorLinkRelationProvider();
	}
	
	@Bean
	@Primary
	public PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext>
		myPluginRegistryProvider(){
		
		PluginRegistryFactoryBean<LinkRelationProvider, LinkRelationProvider.LookupContext> factory 
			= new PluginRegistryFactoryBean<>();
		
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
						Arrays.asList(
								new ParameterBuilder()
								.name("Authorization")
								.description("Header para Token JWT")
								.modelRef(new ModelRef("string"))
								.parameterType("header")
								.required(false)
								.build()));
	}

}
