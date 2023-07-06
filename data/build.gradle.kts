plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    /* Modules */
    implementation(project(":domain"))

    /* Retrofit */
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterMoshi)
    implementation(Libs.Retrofit.converterJackson)

    /* OkHttp3 */
    implementation(Libs.OkHttp3.client)
    implementation(Libs.OkHttp3.loginInterceptor)

    /* Hilt */
    implementation(Libs.Hilt.android)
    implementation(Libs.Hilt.compiler)

    /* Arrow */
    implementation(Libs.Arrow.core)
}