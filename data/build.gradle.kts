plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {

    implementation(project(":share"))

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }

    implementation(Libs.Android.annotation)
    implementation(Libs.inject)
    implementation(Libs.Android.JetPack.pagingLibrary3)

    with(Libs.Network) {
        implementation(retrofit)
        implementation(gson)
        implementation(okhttp)
        implementation(interceptor)
    }
}