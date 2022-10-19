## WebRTC
-keep class com.cloudwebrtc.webrtc.* { *; }
-keep class com.maple.love.chat.meet.Webrtc.* { *; }
-keep class org.webrtc.* { *; }

-dontwarn org.webrtc.**
-keep class com.twilio.video.* { *; }
-keep class com.twilio.common.* { *; }
-keepattributes InnerClasses

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

-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn retrofit2.**