package beta;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

// 지정된  패키지(beta)를 스캔하여 MyBatis 매퍼 인터페이서를 검색 및 등록
// 여기서는 "Mapper.class"라는 어노테이션이 있는 클래스를 검색
@MapperScan(basePackages = "beta", annotationClass = Mapper.class)
public class BetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BetaApplication.class, args);
    }

}
