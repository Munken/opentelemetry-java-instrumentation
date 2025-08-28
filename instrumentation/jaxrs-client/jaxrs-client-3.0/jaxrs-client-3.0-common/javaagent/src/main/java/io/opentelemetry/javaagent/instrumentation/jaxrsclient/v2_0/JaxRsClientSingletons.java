/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.jaxrsclient.v2_0;

import io.opentelemetry.instrumentation.api.instrumenter.Instrumenter;
import io.opentelemetry.javaagent.bootstrap.internal.JavaagentHttpClientInstrumenters;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;

public class JaxRsClientSingletons {
  private static final String INSTRUMENTATION_NAME = "io.opentelemetry.jaxrs-client-2.0";

  private static final Instrumenter<ClientRequestContext, ClientResponseContext> INSTRUMENTER;

  static {
    INSTRUMENTER =
        JavaagentHttpClientInstrumenters.create(
            INSTRUMENTATION_NAME,
            JaxRsClientHttpAttributesGetter.INSTANCE,
            ClientRequestContextHeaderSetter.INSTANCE);
  }

  public static Instrumenter<ClientRequestContext, ClientResponseContext> instrumenter() {
    return INSTRUMENTER;
  }

  private JaxRsClientSingletons() {}
}
