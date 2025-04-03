plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tyme.shared.test"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    lint {
        disable.add("MissingTranslation")
        checkReleaseBuilds = false
        abortOnError = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs +=
            listOf(
                "-P",
                "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=" +
                        "${project.rootDir.absolutePath}/compose_compiler_config.conf",
            )
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

}

dependencies {
//    implementation(libs.androidx.appcompat)
//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.annotation)
//    // Paging
//    implementation(libs.androidx.paging.runtime.ktx)
//    implementation(libs.androidx.paging.common.ktx)
//    implementation(libs.androidx.paging.compose)

    // Dagger
//    implementation(libs.hilt.android)
//    implementation(libs.androidx.hilt.navigation.compose)
//    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.common.java8)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.annotation)

    // Material
    implementation(libs.material)

    //Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    // Paging
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.common.ktx)
    implementation(libs.androidx.paging.compose)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.ui.viewbinding)
    debugImplementation(libs.ui.tooling)

    // Coil - load image
    implementation(libs.coil.compose)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Logging
    implementation(libs.timber)

    // Dagger
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    // Testing
    implementation(libs.junit)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.kotlinx.coroutines.test)
    implementation(libs.junit.jupiter.engine)
    implementation(libs.mockk)
    implementation(libs.turbine)

    implementation(libs.androidx.core)
    implementation(libs.androidx.runner)
    implementation(libs.androidx.rules)
    implementation(libs.androidx.orchestrator)

    implementation(libs.androidx.junit.ext)
    implementation(libs.androidx.truth)
    implementation(libs.truth)

    implementation(libs.ui.test.junit4)
    implementation(libs.ui.test.manifest)
    implementation(libs.androidx.ui.test.junit4.android)

    implementation(libs.androidx.espresso.core.test)
    androidTestImplementation(libs.androidx.espresso.contrib)
    implementation(libs.androidx.espresso.intents)
    implementation(libs.androidx.espresso.accessibility)
    implementation(libs.androidx.idling.concurrent)

}