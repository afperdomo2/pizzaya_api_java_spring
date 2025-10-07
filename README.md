# PizzaYa API

Una API REST para la gestión de pizzas construida con **Spring Boot 3.5.6** y **Java 21**. Esta aplicación permite gestionar pizzas, clientes y órdenes de manera eficiente, incluyendo un procedimiento almacenado para generar órdenes aleatorias con descuento.

## 🚀 Características

- ✅ **CRUD completo de pizzas** (Crear, Leer, Actualizar, Eliminar)
- 👥 **Gestión de clientes** con información básica
- 📋 **Sistema de órdenes** con tipos (Delivery, Carryout, Onsite)
- 🎲 **Procedimiento almacenado** para órdenes aleatorias con 20% descuento
- 🔒 **Autenticación y Autorización** con Spring Security y JWT
- 👤 **Sistema de usuarios y roles** (ADMIN, CUSTOMER, EMPLOYEE)
- 🛡️ **Control de acceso basado en roles** con autoridades personalizadas
- 📚 **Documentación automática** con Swagger/OpenAPI 3
- 🗄️ **Base de datos MySQL** con Docker Compose
- 🔧 **Mapeo automático** con MapStruct
- ✅ **Validaciones** con Bean Validation
- 📝 **Auditoría automática** de entidades (createdBy, updatedBy, createdAt, updatedAt)
- 🏗️ **Arquitectura por capas** (Controller, Service, Persistence)
- 🐳 **Containerización** con Docker
- 🔄 **Hot reload** en desarrollo con DevTools

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 3.5.6**
- **Spring Web** - REST API
- **Spring Data JPA** - Persistencia de datos
- **Spring Security** - Autenticación y autorización
- **JWT (Auth0)** - Tokens de acceso
- **Spring Validation** - Validación de datos
- **MySQL** - Base de datos
- **MapStruct 1.6.3** - Mapeo de objetos
- **SpringDoc OpenAPI** - Documentación automática
- **Docker Compose** - Orchestración de contenedores
- **Gradle** - Gestión de dependencias

## 📋 Prerequisitos

- **Java 21** o superior
- **Docker** y **Docker Compose**
- **Gradle** (incluido wrapper)

## 🚀 Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/afperdomo2/pizzaya_api_java_spring.git
cd pizzaya
```

### 2. Configurar la base de datos

Ejecutar MySQL con Docker Compose:

```bash
docker-compose --env-file .env.production -f docker-compose.prod.yaml up -d
```

Esto creará una base de datos MySQL privada accesible solo por el contenedor de la API.

### 3. Limpiar y construir el proyecto

Si tienes problemas con dependencias en VS Code, ejecuta estos comandos:

```bash
# Limpiar proyecto
./gradlew clean

# Construir proyecto
./gradlew build
```

### 4. Ejecutar la aplicación

```bash
./gradlew bootRun
```

La aplicación estará disponible en: `http://localhost:8082/pizzaya/api`

## 📖 Documentación de la API

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de Swagger en:

```url
http://localhost:8082/pizzaya/api/swagger-ui/index.html
```

### 🔐 Autenticación en Swagger

Para probar los endpoints protegidos en Swagger:

1. Usar el endpoint `/auth/sign-in` con credenciales válidas
2. Copiar el JWT del header `Authorization` de la respuesta
3. Hacer clic en el botón **"Authorize"** en Swagger UI
4. Ingresar el token en el formato: `Bearer {token}`
5. Ahora puedes probar los endpoints protegidos

## 🎯 Endpoints Principales

### Autenticación

| Método | Endpoint | Descripción | Autenticación |
|--------|----------|-------------|---------------|
| `POST` | `/auth/sign-in` | Iniciar sesión y obtener JWT | ❌ No requerida |

### Pizzas

| Método | Endpoint | Descripción | Roles Permitidos |
|--------|----------|-------------|------------------|
| `GET` | `/pizzas` | Obtener todas las pizzas (paginado) | ADMIN, CUSTOMER, EMPLOYEE |
| `GET` | `/pizzas/{id}` | Obtener pizza por ID | ADMIN, CUSTOMER, EMPLOYEE |
| `GET` | `/pizzas/available` | Obtener pizzas disponibles (paginado y ordenado) | ADMIN, CUSTOMER, EMPLOYEE |
| `GET` | `/pizzas/cheaper-than/{price}` | Obtener top 3 pizzas más baratas | ADMIN, CUSTOMER, EMPLOYEE |
| `GET` | `/pizzas/vegan/count` | Contar pizzas veganas | ADMIN, CUSTOMER, EMPLOYEE |
| `POST` | `/pizzas` | Crear nueva pizza | ADMIN |
| `PUT` | `/pizzas/{id}` | Actualizar pizza | ADMIN |
| `DELETE` | `/pizzas/{id}` | Eliminar pizza | ADMIN |
| `PUT` | `/pizzas/price` | Actualizar precio de pizza | ADMIN |

### Órdenes de Clientes

| Método | Endpoint | Descripción | Roles/Permisos |
|--------|----------|-------------|----------------|
| `GET` | `/orders` | Obtener todas las órdenes | ADMIN |
| `GET` | `/orders/today` | Obtener órdenes del día | ADMIN |
| `GET` | `/orders/outside` | Obtener órdenes para llevar/delivery | ADMIN |
| `GET` | `/orders/customer/{customerId}` | Obtener órdenes de un cliente | ADMIN |
| `GET` | `/orders/{orderId}/summary` | Obtener resumen de una orden | ADMIN |
| `POST` | `/orders/random` | Crear orden aleatoria con descuento | RANDOM_ORDER (ADMIN, CUSTOMER) |

### Clientes

| Método | Endpoint | Descripción | Roles Permitidos |
|--------|----------|-------------|------------------|
| `GET` | `/customers/phone/{phone}` | Obtener cliente por teléfono | ADMIN, CUSTOMER |
| `GET` | `/customers/{customerId}/orders` | Obtener órdenes de un cliente | ADMIN, CUSTOMER |

## ⚙️ Configuración

### Perfiles de Aplicación

#### 🚀 Development

- Configuración de desarrollo con MySQL local

#### 💻 Production (Docker)

- Configuración de producción con contenedor MySQL. **Solo con docker**.
- Se requiere crear el archivo `.env.production` a partir del archivo `.env.production.example`.

### Variables de Entorno para Producción

```properties
# Aplicación
PORT=8083

# Base de datos
DB_USERNAME=pizzaya_user
DB_PASSWORD=secure_password_123
DB_ROOT_PASSWORD=secure_root_password
```

## 🧪 Testing

Para ejecutar las pruebas:

```bash
./gradlew test
```

## 🚀 Despliegue

### Construcción para Producción

```bash
./gradlew clean build -x test
```

### Generar JAR

```bash
./gradlew bootJar
```

El archivo JAR se generará en: `build/libs/pizzaya-0.0.1-SNAPSHOT.jar`

### 🐳 Despliegue con Docker

La aplicación incluye configuración completa de Docker para despliegue en producción:

#### Construcción de la Imagen Docker

```bash
# Construir la imagen Docker
docker build -f Dockerfile.prod -t pizzaya-api .

# Construir con un tag específico
docker build -f Dockerfile.prod -t pizzaya-api:1.0.0 .
```

#### Despliegue Completo con Docker Compose

Para un despliegue completo con base de datos incluida:

```bash
# Iniciar todos los servicios (base de datos + aplicación) en modo detached o segundo plano
docker-compose --env-file .env.production -f docker-compose.prod.yaml up -d

# ó

# Iniciar todos los servicios (base de datos + aplicación)
docker-compose --env-file .env.production -f docker-compose.prod.yaml up --build

# Ver logs de la aplicación
docker-compose -f docker-compose.prod.yaml logs -f app

# Detener todos los servicios
docker-compose -f docker-compose.prod.yaml down
```

#### Características del Dockerfile

- **Multi-stage build**: Optimiza el tamaño final de la imagen
- **Etapa 1 (Build)**: Usa Gradle para compilar
- **Etapa 2 (Runtime)**: Usa OpenJDK 21 para ejecución
- **Perfil de producción**: Configurado automáticamente
- **Puerto expuesto**: 8080
- **Base de datos privada**: Solo accesible por el contenedor de la API

## 🔧 Comandos Útiles de Gradle

```bash
# Limpiar proyecto (útil cuando hay problemas con dependencias en VS Code)
./gradlew clean

# Construir proyecto
./gradlew build

# Ejecutar aplicación
./gradlew bootRun

# Ejecutar tests
./gradlew test

# Generar JAR ejecutable
./gradlew bootJar

# Ver dependencias
./gradlew dependencies

# Verificar actualizaciones de dependencias
./gradlew dependencyUpdates
```

## 🐛 Solución de Problemas

### Problemas con Dependencias en VS Code

Si tienes problemas con las dependencias en VS Code, ejecuta:

```bash
./gradlew clean
./gradlew build
```

Luego reinicia VS Code o recarga la ventana (`Ctrl+Shift+P` > "Developer: Reload Window").

### Error de Conexión a Base de Datos

Asegúrate de que los servicios de Docker estén ejecutándose:

```bash
docker-compose -f docker-compose.prod.yaml ps
```

Si no están activos:

```bash
docker-compose --env-file .env.production -f docker-compose.prod.yaml up -d
```

### Problemas con el Procedimiento Almacenado

El procedimiento `take_random_pizza_order` se ejecuta automáticamente al inicializar la base de datos desde `db/store_procedure.sql`.

## 🏗️ Arquitectura del Proyecto

```text
src/main/java/com/afperdomo2/pizzaya/
├── 📁 persistence/                 # Capa de persistencia
│   ├── 📁 audit/                  # Auditoría de entidades
│   ├── 📁 entity/                 # Entidades JPA
│   ├── 📁 mapper/                 # Mappers MapStruct
│   ├── 📁 projection/             # Proyecciones de consultas
│   └── 📁 repository/             # Repositorios JPA
├── 📁 service/                    # Capa de servicio
│   ├── 📁 dto/                    # Data Transfer Objects
│   ├── 📁 exception/              # Excepciones personalizadas
│   ├── CustomerOrderService.java  # Servicio de órdenes
│   ├── CustomerService.java       # Servicio de clientes
│   ├── PizzaService.java          # Servicio de pizzas
│   └── UserSecurityService.java   # Servicio de seguridad
└── 📁 web/                        # Capa de presentación
    ├── 📁 config/                 # Configuraciones
    │   ├── CorsConfig.java        # Configuración CORS
    │   ├── JwtFilter.java         # Filtro JWT
    │   ├── JwtUtil.java           # Utilidades JWT
    │   ├── OpenApiConfig.java     # Configuración Swagger
    │   └── SecurityConfig.java    # Configuración Spring Security
    └── 📁 controller/             # Controladores REST
        ├── AuthController.java
        ├── CustomerController.java
        ├── CustomerOrderController.java
        └── PizzaController.java
```

## 🗄️ Modelo de Base de Datos

### Tabla: pizzas

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT | Identificador único (autoincremental) |
| `name` | VARCHAR(50) | Nombre de la pizza (único) |
| `description` | TEXT | Descripción de la pizza |
| `price` | DECIMAL(10,2) | Precio de la pizza |
| `is_vegetarian` | BOOLEAN | Indica si es vegetariana |
| `is_vegan` | BOOLEAN | Indica si es vegana |
| `is_available` | BOOLEAN | Indica si está disponible |

### Tabla: customers

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT | Identificador único |
| `name` | VARCHAR | Nombre del cliente |
| `address` | VARCHAR | Dirección del cliente |
| `email` | VARCHAR | Email del cliente (único) |
| `phone` | VARCHAR | Teléfono del cliente |

### Tabla: customer_orders

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT | Identificador único (autoincremental) |
| `customer_id` | BIGINT | ID del cliente |
| `total` | DECIMAL(10,2) | Total de la orden |
| `notes` | VARCHAR | Notas de la orden |
| `order_type` | CHAR(1) | Tipo de orden (D=Delivery, C=Carryout, S=Onsite) |
| `date` | DATETIME | Fecha de la orden |

### Tabla: order_items

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `customer_order_id` | BIGINT | ID de la orden (parte de clave compuesta) |
| `pizza_id` | BIGINT | ID de la pizza (parte de clave compuesta) |
| `quantity` | DECIMAL(10,2) | Cantidad |
| `subtotal` | DECIMAL(10,2) | Subtotal |

### Tabla: users

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id` | BIGINT | Identificador único (autoincremental) |
| `username` | VARCHAR | Nombre de usuario (único) |
| `email` | VARCHAR | Email del usuario (único) |
| `password` | VARCHAR | Contraseña encriptada (BCrypt) |
| `is_active` | BOOLEAN | Indica si el usuario está activo |
| `is_locked` | BOOLEAN | Indica si el usuario está bloqueado |

### Tabla: user_roles

| Campo | Tipo | Descripción |
|-------|------|-------------|
| `user_id` | BIGINT | ID del usuario (parte de clave compuesta) |
| `role` | VARCHAR | Rol del usuario: ADMIN, CUSTOMER, EMPLOYEE |
| `granted_date` | DATETIME | Fecha de asignación del rol |

## 🔐 Sistema de Seguridad

### Roles y Permisos

La aplicación implementa un sistema de control de acceso basado en roles:

- **ADMIN**: Acceso completo a todos los recursos
- **CUSTOMER**: Acceso a pizzas (lectura) y gestión de clientes y órdenes propias
- **EMPLOYEE**: Acceso de lectura a pizzas

### Autoridades Personalizadas

- **RANDOM_ORDER**: Permiso especial para crear órdenes aleatorias (asignado a ADMIN y CUSTOMER)

### Autenticación JWT

- Los tokens JWT tienen una validez de **15 días**
- Se generan mediante el algoritmo **HMAC256**
- Se envían en el header `Authorization` con el formato: `Bearer {token}`
- Incluyen información del usuario autenticado para auditoría automática

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor:

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -m 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👨‍💻 Autor

### Andrés Felipe Perdomo

- GitHub: [@afperdomo2](https://github.com/afperdomo2)
