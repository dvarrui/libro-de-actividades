################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../ud03_arrays/figuras_a_trazos.c \
../ud03_arrays/hanoi_iterativo.c \
../ud03_arrays/janoi.c 

OBJS += \
./ud03_arrays/figuras_a_trazos.o \
./ud03_arrays/hanoi_iterativo.o \
./ud03_arrays/janoi.o 

C_DEPS += \
./ud03_arrays/figuras_a_trazos.d \
./ud03_arrays/hanoi_iterativo.d \
./ud03_arrays/janoi.d 


# Each subdirectory must supply rules for building sources it contributes
ud03_arrays/%.o: ../ud03_arrays/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


