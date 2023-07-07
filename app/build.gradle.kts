plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.project.napptilus"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.project.napptilus.di.HiltTestRunner"
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://2q2woep105.execute-api.eu-west-1.amazonaws.com\""
            )
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
            manifestPlaceholders["clearTraffic"] = true
        }
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["clearTraffic"] = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    packagingOptions {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }

    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
}

dependencies {
    /* Modules */
    implementation(project(":data")) // Implementation of data module
    implementation(project(":domain")) // Implementation of domain module
    implementation(project(":usecases")) // Implementation of usecases module

    /* Kotlin */
    implementation(Libs.Kotlin.Coroutines.core) // Kotlin Coroutines library for asynchronous programming

    /* AndroidX */
    implementation(Libs.AndroidX.core) // AndroidX core library for compatible Android components
    implementation(Libs.AndroidX.appCompat) // AndroidX AppCompat library for compatibility with older Android versions
    implementation(Libs.AndroidX.material) // AndroidX Material Design components library
    implementation(Libs.AndroidX.constraintLayout) // AndroidX ConstraintLayout library for flexible layouts

    implementation(Libs.AndroidX.Activity.ktx) // AndroidX Activity Kotlin extensions library

    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx) // AndroidX Lifecycle ViewModel Kotlin extensions library
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx) // AndroidX Lifecycle Runtime Kotlin extensions library

    implementation(Libs.AndroidX.Navigation.NavigationFragment) // AndroidX Navigation Fragment library
    implementation(Libs.AndroidX.Navigation.NavigationUI) // AndroidX Navigation UI library

    implementation(Libs.AndroidX.RecycleView.recycleview) // AndroidX RecyclerView library for creating lists and grids

    /* Glide */
    implementation(Libs.Glide.glide) // Glide library for image loading and display
    implementation(Libs.Glide.compiler) // Glide annotation processor for code generation


    /* OkHttp3 */
    implementation(Libs.OkHttp3.client) // OkHttp3 HTTP client library
    implementation(Libs.OkHttp3.loginInterceptor) // OkHttp3 login interceptor library
    implementation(Libs.OkHttp3.Profiler.profiler) // OkHttp3 Profiler library for network profiling

    /* Retrofit */
    implementation(Libs.Retrofit.retrofit) // Retrofit library for type-safe HTTP requests and REST API consumption
    implementation(Libs.Retrofit.converterMoshi) // Retrofit Moshi converter library for JSON parsing and serialization
    implementation(Libs.Retrofit.converterJackson) // Retrofit Jackson converter library for JSON parsing and serialization

    /* Jackson */
    implementation(Libs.Jackson.core) // Jackson core library for JSON parsing and serialization
    implementation(Libs.Jackson.annotations) // Jackson annotations library
    implementation(Libs.Jackson.databind) // Jackson databind library for JSON processing
    implementation(Libs.Jackson.kotlin) // Jackson Kotlin module for Kotlin support

    /* Arrow */
    implementation(Libs.Arrow.core) // Arrow library for functional programming in Kotlin

/* Hilt */
    implementation(Libs.Hilt.android) // Hilt Android library for dependency injection in Android
    kapt(Libs.Hilt.compiler) // Hilt annotation processor for code generation

    /* PowerSpinner */
    implementation(Libs.PowerSpinner.spinner) // PowerSpinner library for customizable spinner UI

    /* Shimmer */
    implementation(Libs.Shimmer.shimmer) // Shimmer library for creating shimmering effects

    /* Timber */
    implementation(Libs.Timber.timber) // Timber library for logging

    /* Test */
    testImplementation(Libs.JUnit.junit) // JUnit library for unit testing
    testImplementation(Libs.Mockito.kotlin) // Mockito Kotlin library for mocking dependencies
    testImplementation(Libs.Mockito.inline) // Mockito inline library for inline mocking syntax
    testImplementation(Libs.Kotlin.Coroutines.test) // Kotlin Coroutines test library for testing asynchronous code
    testImplementation(Libs.Turbine.turbine) // Turbine library for testing Kotlin Flows

    androidTestImplementation(Libs.AndroidX.Test.Ext.junit) // AndroidX Test extensions JUnit library for Android unit testing
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib) // AndroidX Test Espresso contributions library for UI testing
    androidTestImplementation(Libs.AndroidX.Test.runner) // AndroidX Test runner library for running Android tests
    androidTestImplementation(Libs.AndroidX.Test.rules) // AndroidX Test rules library for defining test rules
    androidTestImplementation(Libs.Hilt.test) // Hilt test library for testing Hilt dependencies
    androidTestImplementation(Libs.Kotlin.Coroutines.test) // Kotlin Coroutines test library for testing asynchronous code
    kaptAndroidTest(Libs.Hilt.compiler) // Hilt annotation processor for code generation

    androidTestImplementation(Libs.OkHttp3.mockWebServer) // OkHttp3 mock web server library for testing HTTP interactions


}