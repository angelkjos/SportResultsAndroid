# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/ivan/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# GSON
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# For using GSON @Expose annotation
-keepattributes *Annotation*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Retrofit 2.X
## https://square.github.io/retrofit/ ##

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

# OkHttp 3

-keep class okhttp3.**
-dontwarn okhttp3.**
-dontwarn okhttp3.**
-dontwarn okio.**

# Dagger 2
# http://stackoverflow.com/a/18177491/2643666
# https://github.com/idamobile/dagger-proguard-helper

-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keepclassmembers,allowobfuscation class * {
    @javax.inject.* *;
    @dagger.* *;
    <init>();
}

-keep class **$$ModuleAdapter
-keep class **$$InjectAdapter
-keep class **$$StaticInjection

-keepnames !abstract class coffee.*
-keepnames class dagger.Lazy

-dontwarn dagger.internal.codegen.**

-keep class dagger.* { *; }
-keep class javax.inject.* { *; }
-keep class * extends dagger.internal.Binding
-keep class * extends dagger.internal.ModuleAdapter
-keep class * extends dagger.internal.StaticInjection

-dontwarn dagger.**

# Support lib, brute force

-keep class android.support.v7.app.** { *; }
-keep interface android.support.v7.app.** { *; }

# Glide

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule