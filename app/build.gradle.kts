plugins {
    id("com.android.application")
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

    implementation(project(":feature"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":share"))

    with(Libs.Android) {
        implementation(core)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
        implementation(lifecycle)
    }

    with(Libs.Android.JetPack) {
        implementation(hilt_android)
        kapt(hilt_compiler)
        kapt(viewModel_hilt)
        implementation(activityKtx)
        implementation(room)
        implementation(roomKtx)
        kapt(room_compiler)
    }
}