import androidx.room.gradle.RoomGradlePlugin

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.retrofitecommerceapp"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.example.retrofitecommerceapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // below dependency for using retrofit.
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    //    TODO: ImageSlider
    implementation("com.github.smarteist:autoimageslider:1.4.0")
    //VIEWPAGER 2
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    //Fragment
    implementation("androidx.fragment:fragment:1.6.2")
    //    //PIN VIEW
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    //COUNTRY CODE PICKER
    implementation ("com.hbb20:ccp:2.5.0")
    //LOTTIEFILES
    implementation("com.airbnb.android:lottie:6.0.1")

    //Room Dependency
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    //Razorpay
    implementation ("com.razorpay:checkout:1.6.26")

}


