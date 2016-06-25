# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AndroidSDK/tools/proguard/proguard-android.txt
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

-optimizationpasses 5  #设置压缩比率 0 - 7
#-dontusemixedcaseclassnames
#-dontskipnonpubliclibraryclasses
-dontpreverify  #不进行预校验
#-verbose
-dontoptimize #不优化内部代码，如果项目中无用代码太多，没有清除，可以注释掉此属性来优化清除无用的代码
-keepattributes Signature #不优化泛型和反射
-keepattributes *Annotation* #保留注解

#okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.** #忽略混淆okio的警告

#retrofit2
-dontwarn retrofit2.** #忽略混淆retrofit2的警告
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions #保留异常