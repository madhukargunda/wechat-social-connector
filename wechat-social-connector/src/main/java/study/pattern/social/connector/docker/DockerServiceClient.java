package study.pattern.social.connector.docker;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;

import lombok.Getter;

@Service
public class DockerServiceClient {

	@Getter
	private DockerClient dockerClient;

	@PostConstruct
	public void init() {
		dockerClient = DockerClientBuilder.getInstance().build();
	}
	
}
