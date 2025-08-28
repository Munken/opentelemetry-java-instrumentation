plugins {
  id("otel.javaagent-instrumentation")
}

muzzle {
  pass {
    group.set("jakarta.ws.rs")
    module.set("jakarta.ws.rs-api")
    versions.set("[3.0,)")
  }
}

dependencies {
  compileOnly("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
  compileOnly("jakarta.annotation:jakarta.annotation-api:3.0.0")

//  testInstrumentation(project(":instrumentation:jaxrs-client:jaxrs-client-2.0:jaxrs-client-2.0-cxf-3.0:javaagent"))
//  testInstrumentation(project(":instrumentation:jaxrs-client:jaxrs-client-2.0:jaxrs-client-2.0-jersey-2.0:javaagent"))
//  testInstrumentation(project(":instrumentation:jaxrs-client:jaxrs-client-2.0:jaxrs-client-2.0-resteasy-3.0:javaagent"))

  testImplementation("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")

  testLibrary("org.glassfish.jersey.core:jersey-client:2.0")
  testLibrary("org.jboss.resteasy:resteasy-client:3.0.5.Final")
  // ^ This version has timeouts https://issues.redhat.com/browse/RESTEASY-975
  testLibrary("org.apache.cxf:cxf-rt-rs-client:3.1.0")
  // Doesn't work with CXF 3.0.x because their context is wrong:
  // https://github.com/apache/cxf/commit/335c7bad2436f08d6d54180212df5a52157c9f21

  testImplementation("javax.xml.bind:jaxb-api:2.2.3")

  testInstrumentation(project(":instrumentation:apache-httpclient:apache-httpclient-4.0:javaagent"))

  latestDepTestLibrary("org.glassfish.jersey.inject:jersey-hk2:2.+")
  latestDepTestLibrary("org.glassfish.jersey.core:jersey-client:2.+")
  latestDepTestLibrary("org.jboss.resteasy:resteasy-client:3.0.26.Final")
}

// Requires old Guava. Can't use enforcedPlatform since predates BOM
//configurations.testRuntimeClasspath.resolutionStrategy.force("com.google.guava:guava:19.0")
