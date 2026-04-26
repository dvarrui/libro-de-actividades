
```
Estado      : En desarrollo!
Curso       :
Area        : Sistemas operativos, automatización, devops   
Descripción : Aprovisionamiento de infraestructura con Terraform
Requisitos  :
Tiempo      :
```

# 1. Terraform

> Enlaces de interés:
> * [Una introducción a Terraform para principiantes - Tutorial de Terraform](https://geekflare.com/es/terraform-for-beginners/)
> * https://geekflare.com/es/infrastructure-automation-software/

_Terraform es una herramienta para definir y aprovisionar la infraestructura completa utilizando un lenguaje declarativo (IaC)._

**Infraestructura como código (IaC)**
* Es el proceso de administrar y aprovisionar la infraestructura de TI utilizando archivos de definición legibles por máquina.
* Es un enfoque de ingeniería de software para el grupo de operaciones.
* Ayuda a automatizar el centro de datos completo mediante el uso de scripts de programación.

**Características de Terraform**
* Realiza orquestación
* Múltiples proveedores: AWS, Azure, GCP, Digital Ocean, Kubernetes, etc.
* Lenguaje de configuracion HCL.
* Admite arquitectura de solo cliente

# 2. Instalación y configuración inicial

> Enlace de interés:
> * [Página oficial para descargar Terraform](https://developer.hashicorp.com/terraform/downloads)

* `zypper install terraform`
* `mkdir terraformXX.d`, crear un directorio de trabajo.
* `cd terraformXX.d`
* Como vamos a hacer un ejemplo con el proveedor AWS, vamos a necesitar las claves de acceso.

Ejemplo `awsXX.tf` para AWS:

```
provider "aws" {
  access_key = "SECRETO"
  secret_key = "SECRETO"
  region = "us-west-2"
}

resource "aws_instance" "tfXXaws" {
  ami = "ami-0a634ae95e11c6f91"
  instance_type = "t2.micro"
}
```

# 3. Ejecutar las etapas del ciclo de vida

Desde el directorio de trabajo.
1. **Init**: inicializa el directorio de trabajo que consta de todos los archivos de configuración: `terraform init`
1. **Plan**: se crea un plan de ejecución para alcanzar el estado deseado de la infraestructura. `terraform plan`
> Note: You didn't specify an "-out" parameter to save this plan, so Terraform
can't guarantee that exactly these actions will be performed if
"terraform apply" is subsequently run.

3. **Apply**: se realizan los cambios como define el plan. `terraform apply`. Si vamos al panel de AWS EC2, veremos que se ha creado una nueva instancia.

![](https://geekflare.com/wp-content/uploads/2020/08/terraform-aws-ec2.png)

![](https://geekflare.com/wp-content/uploads/2020/08/terraform-aws-ec2.png)

4. **Destroy**: Se eliminan los recursos de infraestructura antiguos. `terraform destroy`. Comprobar el el panel que la instancia se eliminó.

--------

# A. ANEXO

## A.1 Conceptos de Terraform

* Variables: Es el par clave-valor para permitir la personalización.
* Provider: para interactuar con las API de servicio
* Módulo: Es una carpeta con plantillas donde se definen todas las configuraciones
* Estado: Información sobre la infraestructura
* Recursos: bloque de uno o más objetos de infraestructura (instancias de cómputo, redes virtuales, etc.), que se utilizan para configurar y administrar la infraestructura.
* Fuente de datos: Los proveedores lo implementan para devolver información sobre objetos.
* Valores de salida: valores de retorno de un módulo.

## A.2 Estado

**Terraform core** utiliza dos fuentes de entrada para trabajar.
* La configuración del usuario.
* El estado actual de la infraestructura.

![](https://geekflare.com/wp-content/uploads/2020/08/terraform-architecture.png)
