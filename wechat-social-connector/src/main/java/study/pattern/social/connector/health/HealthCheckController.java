package study.pattern.social.connector.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Api(value = "Wechat Social Connector API Health Check Controller")
@Slf4j
public class HealthCheckController {

	@GetMapping("/ping")
	public ResponseEntity<?> getWeChat() {
		log.info("Invoking the getWeChat method for the Organization Id {}");
		return ResponseEntity.ok().build();
	}
}
