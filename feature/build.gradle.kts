plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":share"))

    with(Libs.Kotlin){
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }

    with(Libs.Android) {
        implementation(core)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
        implementation(lifecycle)
    }

    with(Libs.Android.JetPack) {
        implementation(viewModel)
        implementation(liveData)
        implementation(activityKtx)
        implementation(hilt_android)
        kapt(hilt_compiler)
        kapt(viewModel_hilt)
        implementation(pagingLibrary3)
    }

    with(Libs.Glide) {
        implementation(glide)
        kapt(glide_compiler)
    }

}