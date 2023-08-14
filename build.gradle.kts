plugins {
	java
	id("org.springframework.boot") version "3.1.2"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "kz.axelrod"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations["annotationProcessor"])
	}
}

allprojects {

	repositories {
		mavenCentral()
	}
}
