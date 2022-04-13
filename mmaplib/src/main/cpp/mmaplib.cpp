#include <jni.h>
#include <string>
#include <filesystem>
#include <iostream>
#include <fstream>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/mman.h>
#include  <android/log.h>

#define  TAG    "MMKV"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG,__VA_ARGS__)

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_mmaplib_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_mmaplib_NativeLib_memoryMapping(JNIEnv *env, jobject thiz) {

    std::string file = "/sdcard/Download/mmkv_demo.txt";

    //O_RDWR 以可读写方式打开文件
    //O_CREAT 自动创建文件
    //S_IRWXU 代表该文件所有者具有可读、可写及可执行的权限.
    int fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);

    LOGI("fd = %d", fd);
    int32_t size = getpagesize();

    ftruncate(fd, size);

    auto ptr = (int8_t *) mmap(0, size, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    std::string data("baronqi size = ");

    strcpy((char *)ptr, data.data());
}