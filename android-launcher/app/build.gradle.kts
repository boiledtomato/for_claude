plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// バージョンは CI から渡す（-PherbariumVersionCode / -PherbariumVersionName）。
//
// ここに固定値を書くとリリースのたびに上げ忘れる。実際 v0.2.0 から v0.4.1 まで
// 4 回のリリースが全部 versionCode=3 / versionName=0.2.0 で出ていて、端末から
// 見るとどれも同じバージョンだった。同じ versionCode の APK は「更新」として
// 扱われないので、入れ直しても古いままに見える。
//
// versionCode は CI の実行番号から作る。単調に増え、旧リリースの 3 より必ず
// 大きい。手元ビルドは既定値のままで、端末へは CI の APK を入れる前提。
val herbariumVersionCode =
    (project.findProperty("herbariumVersionCode") as String?)?.toIntOrNull() ?: 1
val herbariumVersionName =
    (project.findProperty("herbariumVersionName") as String?) ?: "0.0.0-dev"

android {
    namespace = "com.botanical.launcher"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.botanical.launcher"
        minSdk = 26
        targetSdk = 35
        versionCode = herbariumVersionCode
        versionName = herbariumVersionName
    }

    signingConfigs {
        // 鍵をリポジトリに固定する。
        //
        // 既定のデバッグ鍵はビルドする機械ごとに自動生成されるため、手元で
        // 作った APK と CI が作った APK で署名が変わる。Android は署名の違う
        // アプリを上書きできないので、更新しようとすると「アプリがインストール
        // されていません」で弾かれる。
        //
        // パスワードは Android のデバッグ鍵の慣例値そのままで、秘匿する意味は
        // ない。配布ストアに出すときは別途 release 用の鍵を Secrets に置く。
        getByName("debug") {
            storeFile = rootProject.file("keystore/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.foundation)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
}
