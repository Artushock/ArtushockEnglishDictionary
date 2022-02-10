import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.artushock.artushockenglishdictionary"
    const val compile_sdk = 31
    const val min_sdk = 23
    const val target_sdk = 31
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val models = ":models"
    const val repository = ":repository"

    //Features
    const val historyScreen = ":historyScreen"
}

object Versions {
    //Design
    const val appcompatVersion = "1.4.1"
    const val materialVersion = "1.5.0"
    const val fragmentKtxVersion = "1.4.1"
    const val constraintLayoutVersion = "2.1.3"

    //Kotlin
    const val coreKtxVersion = "1.7.0"

    //Coroutines
    const val coroutinesCoreVersion = "1.5.2"
    const val coroutinesAndroidVersion = "1.5.2"

    //Test
    const val jUnitVersion = "4.13.2"
    const val jUnitExtVersion = "1.1.3"
    const val espressoCoreVersion = "3.4.0"

    // Rx-Java
    const val rxAndroidVersion = "2.1.0"
    const val rxJavaVersion = "2.2.8"

    // Retrofit 2
    const val retrofitVersion = "2.9.0"
    const val retrofitGsonVersion = "2.9.0"
    const val loggingInterceptorVersion = "5.0.0-alpha.2"
    const val rxjava2AdapterVersion = "1.0.0"
    const val retrofit2KotlinCoroutinesAdapterVersion = "0.9.2"

    //Glide
    const val glideCompilerVersion = "4.12.0"
    const val glideVersion = "4.12.0"
    const val glideOkhttp3Version = "4.12.0@aar"

    //Koin
    const val koinCoreVersion = "3.1.2"
    const val koinAndroidVersion = "3.1.2"
    const val koinAndroidCompatVersion = "3.1.2"

    //Room
    const val roomRuntimeVersion = "2.4.1"
    const val roomCompilerVersion = "2.4.1"
    const val roomKtxVersion = "2.4.1"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtxVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
}

object Kotlin {
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
}

object Coroutines {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCoreVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroidVersion}"
}

object TestImpl {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExtVersion}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
}

object RxJava {
    const val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroidVersion}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJavaVersion}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonVersion}"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptorVersion}"
    const val rxjava2Adapter = "com.jakewharton.retrofit:retrofit2-rxjava2-adapter:${Versions.rxjava2AdapterVersion}"
    const val retrofit2KotlinCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit2KotlinCoroutinesAdapterVersion}"
}

object Glide {
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideCompilerVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideOkhttp3 = "com.github.bumptech.glide:okhttp3-integration:${Versions.glideOkhttp3Version}"
}

object Koin {
    const val koinCore = "io.insert-koin:koin-core:${Versions.koinCoreVersion}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koinAndroidVersion}"
    const val koinAndroidCompat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompatVersion}"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomRuntimeVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomCompilerVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomKtxVersion}"
}