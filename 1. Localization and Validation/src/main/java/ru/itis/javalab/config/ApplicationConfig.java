package ru.itis.javalab.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableWebMvc
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "ru.itis.javalab")
public class ApplicationConfig {
    @Autowired
    private Environment environment;

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".ftlh");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;

    }
    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        return configurer;
    }

    @Bean
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("spring.mail.properties.mail.smtp.starttls.enable","true");
        properties.put("spring.mail.properties.mail.smtp.allow8bitmime","true");
        properties.put("spring.mail.properties.mail.smtp.ssl.trust","smtp.gmail.com");
        properties.put("spring.mail.properties.debug","true");

        return mailSender;
    }
    @Bean
    public freemarker.template.Configuration configuration(){
        freemarker.template.Configuration configuration = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_30);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateLoader(
                new SpringTemplateLoader(new ClassRelativeResourceLoader(this.getClass()),"/"));
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }
    @Bean
    public ExecutorService executorService(){
        return Executors.newCachedThreadPool();
    }
}
