#include <jni.h>
#include <android/log.h>
#include <pthread.h>
#include <unistd.h>

#define LOG_TAG "AmonixMod"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

// Это точка входа, которую игра вызывает при загрузке библиотеки
extern "C" JNIEXPORT jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    LOGI("======================================");
    LOGI("AmonixMod: Библиотека успешно внедрена!");
    LOGI("======================================");
    
    // Здесь позже будем добавлять хуки (адреса функций игры)
    
    return JNI_VERSION_1_6;
}

