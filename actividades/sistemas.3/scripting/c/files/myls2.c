#include<stdio.h>

int main(){
  int ret = system("ls -l");
  if (ret!=0) {
    printf("Error");
  }
  return 0;
}
