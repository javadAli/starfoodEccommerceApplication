pluginManagement {
    repositories {
        google()
        maven { url = uri("https://maven.google.com")}
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
        jcenter()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        maven { url = uri("https://maven.google.com")}
        maven { url = uri("https://jitpack.io") }
        mavenCentral()
        jcenter()

    }
}

rootProject.name = "StarFood"
include(":app")
