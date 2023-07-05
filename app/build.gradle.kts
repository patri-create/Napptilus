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
        applicationId = "com.project.mytemplate"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "version"
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"

            buildConfigField("String", "BASE_URL", "\"https://example.com\"")
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    /* Kotlin */
    implementation(Libs.Kotlin.Coroutines.core)

    /* AndroidX */
    implementation(Libs.AndroidX.core)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.splash)

    implementation(Libs.AndroidX.Activity.ktx)

    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)

    implementation(Libs.AndroidX.Navigation.NavigationFragment)
    implementation(Libs.AndroidX.Navigation.NavigationUI)

    implementation(Libs.AndroidX.SwipeLayout.swipe)

    implementation(Libs.AndroidX.RecycleView.recycleview)
    implementation(Libs.AndroidX.RecycleView.recycleviewSelection)

    implementation(Libs.AndroidX.Drawer.drawer)

    /* Glide */
    implementation(Libs.Glide.glide)
    implementation(Libs.Glide.compiler)

    /* Dots Indicator */
    implementation(Libs.DotsIndicator.dotsIndicator)


    /* OkHttp3 */
    implementation(Libs.OkHttp3.loginInterceptor)

    /* Retrofit */
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterMoshi)
    implementation(Libs.Retrofit.converterJackson)

    /* Moshi */
    implementation(Libs.Moshi.moshi)
    kapt(Libs.Moshi.moshiCodegen)
    implementation(Libs.Moshi.moshiKotlin)

    /* Jackson */
    implementation(Libs.Jackson.core)
    implementation(Libs.Jackson.annotations)
    implementation(Libs.Jackson.databind)
    implementation(Libs.Jackson.kotlin)

    /* Arrow */
    implementation(Libs.Arrow.core)

    /* Hilt */
    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)

    /* Location Services */
    implementation(Libs.playServicesLocation)

    /* Test */
    testImplementation(Libs.JUnit.junit)
    testImplementation(Libs.Mockito.kotlin)
    testImplementation(Libs.Mockito.inline)
    testImplementation(Libs.Kotlin.Coroutines.test)
    testImplementation(Libs.Turbine.turbine)

    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.Espresso.contrib)
    androidTestImplementation(Libs.AndroidX.Test.runner)
    androidTestImplementation(Libs.AndroidX.Test.rules)
    androidTestImplementation(Libs.Hilt.test)
    androidTestImplementation(Libs.Kotlin.Coroutines.test)
    kaptAndroidTest(Libs.Hilt.compiler)

    androidTestImplementation(Libs.OkHttp3.mockWebServer)

}