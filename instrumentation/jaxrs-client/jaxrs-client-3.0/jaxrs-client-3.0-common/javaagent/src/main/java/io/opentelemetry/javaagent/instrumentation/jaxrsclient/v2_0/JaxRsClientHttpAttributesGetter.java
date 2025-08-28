/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.jaxrsclient.v2_0;

import static java.util.Collections.emptyList;

import io.opentelemetry.instrumentation.api.semconv.http.HttpClientAttributesGetter;

import java.util.List;
import jakarta.annotation.Nullable;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;

enum JaxRsClientHttpAttributesGetter
    implements HttpClientAttributesGetter<ClientRequestContext, ClientResponseContext> {

  INSTANCE;

  @Nullable
  @Override
  public String getUrlFull(ClientRequestContext clientRequestContext) {
    System.out.println("getUrlFull: " + clientRequestContext.getUri().toString());
    return clientRequestContext.getUri().toString();
  }

  @Nullable
  @Override
  public String getServerAddress(ClientRequestContext clientRequestContext) {
    return clientRequestContext.getUri().getHost();
  }

  @Nullable
  @Override
  public Integer getServerPort(ClientRequestContext clientRequestContext) {
    return clientRequestContext.getUri().getPort();
  }

  @Nullable
  @Override
  public String getHttpRequestMethod(ClientRequestContext clientRequestContext) {
    return clientRequestContext.getMethod();
  }

  @Override
  public List<String> getHttpRequestHeader(ClientRequestContext clientRequestContext, String name) {
    return clientRequestContext.getStringHeaders().getOrDefault(name, emptyList());
  }

  @Nullable
  @Override
  public Integer getHttpResponseStatusCode(ClientRequestContext clientRequestContext,
      ClientResponseContext clientResponseContext, @Nullable Throwable error) {
    return clientResponseContext.getStatus();
  }

  @Override
  public List<String> getHttpResponseHeader(ClientRequestContext clientRequestContext,
      ClientResponseContext clientResponseContext, String name) {
    return clientResponseContext.getHeaders().getOrDefault(name, emptyList());
  }
}
