################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
C_SRCS += \
../ud05_malloc/ordenacion_basica.c \
../ud05_malloc/ordenacion_binaria.c \
../ud05_malloc/ordenacion_listadoble.c 

OBJS += \
./ud05_malloc/ordenacion_basica.o \
./ud05_malloc/ordenacion_binaria.o \
./ud05_malloc/ordenacion_listadoble.o 

C_DEPS += \
./ud05_malloc/ordenacion_basica.d \
./ud05_malloc/ordenacion_binaria.d \
./ud05_malloc/ordenacion_listadoble.d 


# Each subdirectory must supply rules for building sources it contributes
ud05_malloc/%.o: ../ud05_malloc/%.c
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C Compiler'
	gcc -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o"$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


