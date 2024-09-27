package beta.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class BeanConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {

        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

        // 메시지 소스의 기본 경로를 설정.
        source.setBasename("classpath:/messages/message");  // 여기서는 'classpath:/messages/message' 경로에 위치한 메시지 파일을 참조
        // 메시지 소스의 기본 인코딩 설정.
        source.setDefaultEncoding("UTF-8");                 // UTF-8로 설정
        // 메시지 소스를 30초 동안 캐싱하도록 설정.
        source.setCacheSeconds(30);                         // 30초마다 메시지 소스를 다시 로드
        
        Locale.setDefault(Locale.KOREA);

        return source;
    }
}
