package com.venesa.ctvvcare.component;//package com.venesa.ctvvcare.component;
//
//import com.example.utils.ResponseData;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@Component
//public class WebClientComponent {
//
//	@Autowired
//	private WebClient webClient;
//
//	public <T, V> T callInternalService(ParameterizedTypeReference<T> type, V body, HttpMethod method, String url , Class<T> tClass) {
//		ResponseData responseData = webClient.method(method).uri(url).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.accept(MediaType.APPLICATION_JSON).body(Mono.just(body), type).retrieve()
//				.bodyToMono(ResponseData.class).block();
//		ObjectMapper objectMapper = new ObjectMapper();
//		T dto = objectMapper.convertValue(responseData.getData(),  tClass);
//		return dto;
//	}
//}
