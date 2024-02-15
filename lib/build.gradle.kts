val kotlinVersion: String by project

plugins {
    alias(libs.plugins.jvm)
    alias(libs.plugins.ktor)
    `java-library`
    `maven-publish`
}

group = "com.administrativektor"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    // Test dependencies
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation(libs.junit.jupiter.engine)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // Development dependencies
    implementation(libs.guava)
    implementation("io.ktor:ktor-server-core")

    // This dependency is exported to consumers, that is to say found on their compile classpath.
    api(libs.commons.math3)

}

java {
    // Apply a specific Java toolchain to ease working on different environments.
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("ktor-admin") {
            artifactId = "ktor-admin"
            from(components["java"])
            pom {
                name = "Ktor Admin"
                description = "An admin interface library for ktor applications."
                url = "https://github.com/believemanasseh/ktor-admin"
                licenses {
                    license {
                        name = "The 3-Clause BSD License"
                        url = "https://opensource.org/license/bsd-3-clause/"
                    }
                }
                developers {
                    developer {
                        id = "believemanasseh"
                        name = "Believe Manasseh"
                        email = "believemanasseh@gmail.com"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com/believemanasseh/ktor-admin.git"
                    developerConnection = "scm:git:ssh://github.com/believemanasseh/ktor-admin.git"
                    url = "https://github.com/believemanasseh/ktor-admin"
                }
            }
        }
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
