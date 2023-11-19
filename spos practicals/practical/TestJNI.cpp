#include "C:/Program Files/Java/jdk-17.0.2/include/jni.h"
#include<stdio.h>
#include "TestJNI.h"

JNIEXPORT jint JNICALL Java_TestJNI_add(JNIEnv *env,jobject thisObj,jint n1,jint n2)
{
  jint res;
  res = n1+n2;
  return res;
}

// int main()
// {
//   printf("Hello from c!\n");
// }

