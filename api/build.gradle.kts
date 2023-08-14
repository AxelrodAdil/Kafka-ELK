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
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
