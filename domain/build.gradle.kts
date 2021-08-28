plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {

    implementation(project(":data"))
    implementation(project(":share"))

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }
}