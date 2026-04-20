// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false        // ✅ Must exist in libs.versions.toml
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.kotlin.kapt) apply false           // ✅ Must exist in libs.versions.toml
    alias(libs.plugins.kotlin.serialization) apply false  // ✅ Must exist in libs.versions.toml
    alias(libs.plugins.hilt.android) apply false          // ✅ Must exist in libs.versions.toml
    alias(libs.plugins.google.gms.google.services) apply false
}
