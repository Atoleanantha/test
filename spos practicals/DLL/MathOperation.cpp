#include <jni.h>
#include <stdio.h>
#include "MathOperation.h"

JNIEXPORT jint JNICALL Java_MathOperation_add(JNIEnv *env,jobject obj,jint a, jint b){
    return a+b;
}

JNIEXPORT jint JNICALL Java_MathOperation_sub(JNIEnv *env,jobject obj,jint a, jint b){
    return a-b;
}

JNIEXPORT jint JNICALL Java_MathOperation_mult(JNIEnv *env,jobject obj, jint a, jint b){
    return a*b;
}

JNIEXPORT jdouble JNICALL Java_MathOperation_div(JNIEnv *env,jobject obj,jint a, jint b){
    if(b==0){
        return 0;
    }
    return a/b;
}