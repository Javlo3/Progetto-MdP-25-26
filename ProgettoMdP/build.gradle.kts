plugins {
    id("java")
    id("application")
}

group = "org.progetto"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.progetto.Main")
}

dependencies {
    val javafxVersion = "25.0.3"
    val osName = System.getProperty("os.name").lowercase()
    val platform = when {
        osName.contains("win") -> "win"
        osName.contains("mac") -> "mac"
        else -> "linux"
    }
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-base:$javafxVersion:$platform")

    implementation("com.google.code.gson:gson:2.11.0")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}