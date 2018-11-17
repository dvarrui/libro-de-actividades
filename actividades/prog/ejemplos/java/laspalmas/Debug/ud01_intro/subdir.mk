################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../ud01_intro/bucles.c \
../ud01_intro/holamundo.c 

OBJS += \
./ud01_intro/bucles.o \
./ud01_intro/holamundo.o 

C_DEPS += \
./ud01_intro/bucles.d \
./ud01_intro/holamundo.d 


# Each subdirectory must supply rules for building sources it contributes
ud01_intro/%.o: ../ud01_intro/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


