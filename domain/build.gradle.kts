plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }
}