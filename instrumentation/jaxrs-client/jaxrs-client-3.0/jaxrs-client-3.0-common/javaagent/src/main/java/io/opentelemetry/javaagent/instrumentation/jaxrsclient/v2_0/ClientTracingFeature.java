/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.jaxrsclient.v2_0;

import jakarta.ws.rs.core.Feature;
import jakarta.ws.rs.core.FeatureContext;

public class ClientTracingFeature implements Feature {


  @Override
  public boolean configure(FeatureContext context) {
    context.register(new ClientTracingFilter());
    return true;
  }
}
