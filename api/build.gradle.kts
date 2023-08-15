plugins {
	java
	id("org.springframework.boot")
	id("io.spring.dependency-management")
}

group = "kz.axelrod.api"
version = "0.0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

tasks.bootJar {
	enabled = true
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.6")
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito:mockito-core:4.6.1")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
