# Vert.x JAX-RS Sample module

It accepts HTTP connection on port 8080 when you start the module.

Returns the response string "Hello World" If you try to access the http://localhost:8080/rest/test/hoge

Including vertx-mod-jersey and vertx-mod-hk2.

h4. build and running
```bash
./gradlew clean build
./gradlew runmod
```

Limitation:
 Test cases are not implemented yet.
