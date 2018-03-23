

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.sendtomoon.mozart.mapper")
public class ReNew {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ReNew.class, args);
	}

}
