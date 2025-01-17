plugins {
    id("gradlebuild.distribution.api-java")
    id("gradlebuild.jmh")
}

description = "Implementation of configuration model types and annotation metadata handling (Providers, software model, conventions)"

gradlebuildJava {
    usesJdkInternals = true
}

dependencies {
    api(projects.baseServices)
    api(projects.coreApi)
    api(projects.files)
    api(projects.functional)
    api(projects.hashing)
    api(projects.messaging)
    api(projects.modelReflect)
    api(projects.persistentCache)
    api(projects.resources)
    api(projects.serialization)
    api(projects.serviceLookup)
    api(projects.snapshots)
    api(projects.stdlibJavaExtensions)

    api(libs.asm)
    api(libs.jsr305)
    api(libs.inject)
    api(libs.groovy)
    api(libs.guava)

    implementation(projects.baseServicesGroovy)
    implementation(projects.baseAsm)
    implementation(projects.classloaders)
    implementation(projects.logging)
    implementation(projects.problemsApi)
    implementation(projects.serviceProvider)
    implementation(projects.serviceRegistryBuilder)

    implementation(libs.kotlinStdlib)
    implementation(libs.slf4jApi)
    implementation(libs.commonsLang)

    compileOnly(libs.errorProneAnnotations)

    testFixturesApi(testFixtures(projects.baseDiagnostics))
    testFixturesApi(testFixtures(projects.core))
    testFixturesApi(projects.internalIntegTesting)
    testFixturesImplementation(projects.baseAsm)
    testFixturesImplementation(libs.guava)
    testFixturesImplementation(libs.groovyAnt)
    testFixturesImplementation(libs.groovyDatetime)
    testFixturesImplementation(libs.groovyDateUtil)

    testImplementation(projects.processServices)
    testImplementation(projects.fileCollections)
    testImplementation(projects.native)
    testImplementation(projects.resources)
    testImplementation(testFixtures(projects.coreApi))
    testImplementation(testFixtures(projects.languageGroovy))
    testImplementation(testFixtures(projects.modelReflect))

    integTestImplementation(projects.platformBase)

    testRuntimeOnly(projects.distributionsCore) {
        because("Tests instantiate DefaultClassLoaderRegistry which requires a 'gradle-plugins.properties' through DefaultPluginModuleRegistry")
    }
    integTestDistributionRuntimeOnly(projects.distributionsNative) {
        because("ModelRuleCachingIntegrationTest requires a rules implementation")
    }

    jmhImplementation(platform(projects.distributionsDependencies))
}

strictCompile {
    ignoreRawTypes() // raw types used in public API
}

integTest.usesJavadocCodeSnippets = true

packageCycles {
    excludePatterns.add("org/gradle/model/internal/core/**")
    excludePatterns.add("org/gradle/model/internal/inspect/**")
    excludePatterns.add("org/gradle/api/internal/tasks/**")
    excludePatterns.add("org/gradle/model/internal/manage/schema/**")
    excludePatterns.add("org/gradle/model/internal/type/**")
    excludePatterns.add("org/gradle/api/internal/plugins/*")
    // cycle between org.gradle.api.internal.provider and org.gradle.util.internal
    // (api.internal.provider -> ConfigureUtil, DeferredUtil -> api.internal.provider)
    excludePatterns.add("org/gradle/util/internal/*")
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
