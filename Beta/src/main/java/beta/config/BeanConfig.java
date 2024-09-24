package beta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class BeanConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        /*  Note:
         *  ReloadableResourceBundleMessageSource is a class in the Spring Framework used for
         *  internationalization(i18n) in Java-based applications. This class allows you to
         *  load messages (usually for localization) from a properties file and make it possible
         *  to reload these files without restarting the application.
         * */
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

        // basename: Specifies the base name of the properties file(s) for message loading.
        source.setBasename("classpath:/messages/message");      // 여기서는 'classpath:/messages/message' 경로에 위치한 메시지 파일 참조

        // defaultEncoding: Sets the character encoding used for reading the message files.
        source.setDefaultEncoding("UTF-8");     // UTF-8로 설정

        /* Note:
         *  cacheSeconds: Defines the interval (in seconds) after which the message files should be reloaded.
         *  This is useful for environments where you need dynamic changes to your message files without
         *  restarting the server.
         * */
        source.setCacheSeconds(30);            // 30초마다 메시지 소스를 다시 로드

        // setDefault(Locale newLocale): Sets the default locale for this instance of the Java Virtual Machine
        Locale.setDefault(Locale.KOREA);

        return source;
    }
}
