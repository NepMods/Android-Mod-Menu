#ifndef UTILS
#define UTILS

#include <jni.h>
#include <unistd.h>
#include <cstdio>
#include <cstring>
#include <string>
#include <cstdlib>
#include "Logger.h"

typedef unsigned long DWORD;
static uintptr_t libBase;

bool libLoaded = false;

DWORD findLibrary(const char *library) {
    char filename[0xFF] = {0},
            buffer[1024] = {0};
    FILE *fp = NULL;
    DWORD address = 0;

    sprintf(filename, OBFUSCATE("/proc/self/maps"));

    fp = fopen(filename, OBFUSCATE("rt"));
    if (fp == NULL) {
        perror(OBFUSCATE("fopen"));
        goto done;
    }

    while (fgets(buffer, sizeof(buffer), fp)) {
        if (strstr(buffer, library)) {
            address = (DWORD) strtoul(buffer, NULL, 16);
            goto done;
        }
    }

    done:

    if (fp) {
        fclose(fp);
    }

    return address;
}

DWORD getAbsoluteAddress(const char *libraryName, DWORD relativeAddr) {
    libBase = findLibrary(libraryName);
    if (libBase == 0)
        return 0;
    return (reinterpret_cast<DWORD>(libBase + relativeAddr));
}


jboolean isGameLibLoaded(JNIEnv *env, jobject thiz) {
    return libLoaded;
}

bool isLibraryLoaded(const char *libraryName) {
    //libLoaded = true;
    char line[512] = {0};
    FILE *fp = fopen(OBFUSCATE("/proc/self/maps"), OBFUSCATE("rt"));
    if (fp != NULL) {
        while (fgets(line, sizeof(line), fp)) {
            std::string a = line;
            if (strstr(line, libraryName)) {
                libLoaded = true;
                return true;
            }
        }
        fclose(fp);
    }
    return false;
}

uintptr_t string2Offset(const char *c) {
    int base = 16;
    // See if this function catches all possibilities.
    // If it doesn't, the function would have to be amended
    // whenever you add a combination of architecture and
    // compiler that is not yet addressed.
    static_assert(sizeof(uintptr_t) == sizeof(unsigned long)
                  || sizeof(uintptr_t) == sizeof(unsigned long long),
                  "Please add string to handle conversion for this architecture.");

    // Now choose the correct function ...
    if (sizeof(uintptr_t) == sizeof(unsigned long)) {
        return strtoul(c, nullptr, base);
    }

    // All other options exhausted, sizeof(uintptr_t) == sizeof(unsigned long long))
    return strtoull(c, nullptr, base);
}

void T(JNIEnv *env, jobject thiz, const char *text, int length) {
    jstring jstr = env->NewStringUTF(text);
    jclass toast = env->FindClass("android/widget/Toast");
    jmethodID methodMakeText = env->GetStaticMethodID( toast, "makeText", "(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;");
    if (methodMakeText == NULL) { return;  }
    jobject toastobj = env->CallStaticObjectMethod(toast, methodMakeText, thiz, jstr, length);
     jmethodID methodShow = env->GetMethodID(toast, "show", "()V");
    if (methodShow == NULL) {  return; }
    env->CallVoidMethod(toastobj, methodShow);
}
struct {
     int L = 1;
      int S = 0;
} Length;
void D(jobject ctx, JNIEnv *env, const char *title, const char *msg){
    jclass Alert = env->FindClass("android/app/AlertDialog$Builder");
    jmethodID AlertCons = env->GetMethodID(Alert, "<init>", "(Landroid/content/Context;)V");

    jobject MainAlert = env->NewObject(Alert, AlertCons, ctx);

    jmethodID setTitle = env->GetMethodID(Alert, "setTitle", "(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;");
    env->CallObjectMethod(MainAlert, setTitle, env->NewStringUTF(title));

    jmethodID setMsg = env->GetMethodID(Alert, ("setMessage"), ("(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setMsg, env->NewStringUTF(msg));

    jmethodID setCa = env->GetMethodID(Alert, ("setCancelable"), ("(Z)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setCa, false);

    jmethodID setPB = env->GetMethodID(Alert, ("setPositiveButton"), ("(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setPB, env->NewStringUTF("Ok"), static_cast<jobject>(NULL));

    jmethodID create = env->GetMethodID(Alert, ("create"), ("()Landroid/app/AlertDialog;"));
    jobject creaetob = env->CallObjectMethod(MainAlert, create);

    jclass AlertN = env->FindClass(("android/app/AlertDialog"));

    jmethodID show = env->GetMethodID(AlertN, ("show"), ("()V"));
    env->CallVoidMethod(creaetob, show);
}
void DFull(jobject ctx, JNIEnv *env, const char *title, const char *msg, const char *Ok,const char *Cancel, bool usCloseable ){
    jclass Alert = env->FindClass("android/app/AlertDialog$Builder");
    jmethodID AlertCons = env->GetMethodID(Alert, "<init>", "(Landroid/content/Context;)V");

    jobject MainAlert = env->NewObject(Alert, AlertCons, ctx);

    jmethodID setTitle = env->GetMethodID(Alert, "setTitle", "(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;");
    env->CallObjectMethod(MainAlert, setTitle, env->NewStringUTF(title));

    jmethodID setMsg = env->GetMethodID(Alert, ("setMessage"), ("(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setMsg, env->NewStringUTF(msg));

    jmethodID setCa = env->GetMethodID(Alert, ("setCancelable"), ("(Z)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setCa, usCloseable);

    jmethodID setPB = env->GetMethodID(Alert, ("setPositiveButton"), ("(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setPB, env->NewStringUTF(Ok), static_cast<jobject>(NULL));

    jmethodID setNB = env->GetMethodID(Alert, ("setNegativeButton"), ("(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;"));
    env->CallObjectMethod(MainAlert, setNB, env->NewStringUTF(Cancel), static_cast<jobject>(NULL));

    
    jmethodID create = env->GetMethodID(Alert, ("create"), ("()Landroid/app/AlertDialog;"));
    jobject creaetob = env->CallObjectMethod(MainAlert, create);

    jclass AlertN = env->FindClass(("android/app/AlertDialog"));

    jmethodID show = env->GetMethodID(AlertN, ("show"), ("()V"));
    env->CallVoidMethod(creaetob, show);
}

void CP(JNIEnv *env, jobject ctx, const char *perm){
    jclass native_context = env->GetObjectClass(ctx);
    jmethodID startActivity = env->GetMethodID(native_context, ("startActivity"),("(Landroid/content/Intent;)V"));

    jmethodID pack = env->GetMethodID(native_context, ("getPackageName"),("()Ljava/lang/String;"));
    jstring packageName = static_cast<jstring>(env->CallObjectMethod(ctx, pack));

    const char *pkg = env->GetStringUTFChars(packageName, 0);

    std::stringstream pkgg;
    pkgg << ("package:");
    pkgg << pkg;
    std::string pakg = pkgg.str();

    jclass Uri = env->FindClass(("android/net/Uri"));
    jmethodID Parce = env->GetStaticMethodID(Uri, ("parse"), ("(Ljava/lang/String;)Landroid/net/Uri;"));
    jobject UriMethod = env->CallStaticObjectMethod(Uri, Parce, env->NewStringUTF(pakg.c_str()));

    jclass intentclass = env->FindClass(("android/content/Intent"));
    jmethodID newIntent = env->GetMethodID(intentclass, ("<init>"), ("(Ljava/lang/String;Landroid/net/Uri;)V"));
    jobject intent = env->NewObject(intentclass,newIntent,env->NewStringUTF((perm)), UriMethod);

    env->CallVoidMethod(ctx, startActivity, intent);
}

namespace ToastLength {
    inline const int LENGTH_LONG = 1;
    inline const int LENGTH_SHORT = 0;
}

#endif
