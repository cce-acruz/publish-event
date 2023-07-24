import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
	val kotlinVersion = "1.7.21"
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	id("com.github.davidmc24.gradle.plugin.avro") version "1.7.1"
}

group = "br.com.worktools"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven {
		url = uri("https://repo.intranet.pags/artifactory/gradle-all")
	}
}

extra["springCloudVersion"] = "2021.0.5"
val multipleCardEventsVersion = "2.32.0"
val mapStructVersion = "1.5.3.Final"
val openAPISwagger = "1.6.12"
val avroVersion = "1.11.1"
val kafkaAvroSerializerVersion = "6.1.1"

dependencies {
	implementation("org.springdoc:springdoc-openapi-ui:$openAPISwagger")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.cloud:spring-cloud-stream")
	implementation("multiple-card:multiple-card-events:$multipleCardEventsVersion")
	implementation("org.mapstruct:mapstruct:$mapStructVersion")
	implementation("org.springframework.cloud:spring-cloud-stream-binder-kafka:3.2.6")
	implementation("org.springframework.cloud:spring-cloud-starter-stream-kafka:3.2.6")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.apache.avro:avro:$avroVersion")
	implementation("io.confluent:kafka-avro-serializer:$kafkaAvroSerializerVersion")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.1")

	implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")

	annotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")

	kapt("org.mapstruct:mapstruct-processor:$mapStructVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:$mapStructVersion")
}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

kapt {
	correctErrorTypes = true
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
		arg("mapstruct.unmappedTargetPolicy", "IGNORE")
		arg("mapstruct.defaultInjectionStrategy", "constructor")
	}
}

sourceSets {
	main {
		java {
			srcDir(file("$buildDir/generated-main-avro-java"))
		}
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
