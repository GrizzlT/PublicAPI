package net.hypixel.api.http;

import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public interface HypixelHttpClient {

    String DEFAULT_USER_AGENT = "Hypixel PublicAPI/4.0.0";

    Mono<HypixelHttpResponse> makeRequest(String url);

    Mono<HypixelHttpResponse> makeAuthenticatedRequest(String url);

    void shutdown();

}
