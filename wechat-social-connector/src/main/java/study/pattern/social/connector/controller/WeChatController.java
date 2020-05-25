package study.pattern.social.connector.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import study.pattern.social.connector.docker.DockerServiceClient;
import study.pattern.social.connector.dto.Message;

@RestController
@Api(value = "Wechat Social Connector API")
@Slf4j
public class WeChatController {

	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	DockerServiceClient dockerServiceClient;

	/**
	 * Simple Rest API To know the Request Header 
	 * Parameters and its values
	 * 
	 * @param orgId
	 * @param headers
	 * @param requestHeader
	 * @param req
	 * @return
	 * @throws UnknownHostException 
	 */
	@GetMapping("/v1/{OrgId}/socialconnections/wechat")
	public ResponseEntity<?> getWeChat(@PathVariable("OrgId") String orgId,
			@RequestHeader(required = false) MultiValueMap<String, String> headers, @RequestHeader(required = false) HttpHeaders requestHeader,
			HttpServletRequest req) throws UnknownHostException {
		log.info("Invoking the getWeChat method for the Organization Id {}", orgId);
		
		InetAddress inetAddress = InetAddress.getLocalHost();
		
		headers.forEach((key, value) -> {
			log.info("The Request Header name and values {} ",
					String.format("Header '%s' = %s", key, value.stream().collect(Collectors.joining("|"))));
		});
		InetSocketAddress host = requestHeader.getHost();
		String url = "http://" + host.getHostName() + ":" + host.getPort();
		log.info("The Request Header name and values {} ", url);

		if (Character.isDigit(orgId.charAt(0))) {
			return ResponseEntity.status(Integer.valueOf(orgId)).build();
		}

		log.info("HttpServletRequest headers ");
		Enumeration<String> headerNames = req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			Enumeration<String> headersh = req.getHeaders(headerName);
			while (headersh.hasMoreElements()) {
				String headerValue = headersh.nextElement();
				log.info("The Request header name: {} value: {} ", headerName, headerValue);
			}
		}
		
		log.info("Request  ending successfully");
		return ResponseEntity.ok("Wechat - Request from the Container Id ".concat(inetAddress.getHostName().concat(" - "))
				.concat(inetAddress.getHostAddress()).concat(" - ").concat(orgId));
	}

	@ApiOperation(value = "Post Message to Wechat", nickname = "Wecaht Social Connector  API", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Message sent to Respect Channel", response = Message.class) })
	@PostMapping(value = "/v1/{OrgId}/socialconnections/wechat", consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Message postWechat(
			@ApiParam(value = "Send Notifications to clients ", required = true) @RequestBody Message message,
			@PathVariable("OrgId") String orgId) throws IOException {
		log.info("The InputRequest in json format is :{} ", objectMapper.writeValueAsString(message));
		return message;
	}
}
