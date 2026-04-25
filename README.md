# 🛍️ Drakon Store

**Plataforma de Ventas de Productos - Arquitectura Hexagonal**

---

## 📋 Información del Proyecto

| Dato | Valor |
|------|-------|
| **Materia** | Ingeniería de Software III |
| **Profesor/Asesor** | Carlos Arturo Barrientos |
| **Estudiante** | Jefferson David Mejia Torres |
| **Universidad** | Universidad del Cauca |
| **Año** | 2026 |
| **Descripción** | Sistema web de gestión de ventas de productos con autenticación y control de usuario |

---

## 🎯 Objetivo

Desarrollar una aplicación de comercio electrónico aplicando principios de **arquitectura hexagonal** y **clean code** para garantizar:

- ✅ Código mantenible y escalable
- ✅ Separación clara de responsabilidades
- ✅ Independencia de frameworks (dominio puro)
- ✅ Fácil testing unitario
- ✅ Flexibilidad para cambiar tecnologías

---

## 📦 Módulos Principales

### 1️⃣ **Autenticación de Usuarios**
- Registro de nuevos usuarios
- Inicio de sesión
- Generación de tokens JWT
- Validación de credenciales

### 2️⃣ **Gestión de Productos**
- CRUD completo de productos
- Validaciones de negocio
- Control de stock
- Consultas y listados

---

## 🏗️ Arquitectura Hexagonal

```
┌─────────────────────────────────────────┐
│         CAPA DE ENTRADA (REST)          │
│        AuthController                    │
│        ProductoController                │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│       CAPA DE APLICACIÓN                │
│  DTOs | Mappers | Transformación        │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│    CAPA DE DOMINIO (NÚCLEO)             │
│  Modelos | Casos de Uso | Puertos       │
│  Lógica de Negocio Pura                 │
└──────────────┬──────────────────────────┘
               │
┌──────────────▼──────────────────────────┐
│      CAPA DE INFRAESTRUCTURA            │
│  JPA | PostgreSQL | Adaptadores         │
└─────────────────────────────────────────┘
```

---

## 🛠️ Stack Tecnológico

- **Framework**: Spring Boot 4.0.4
- **Lenguaje**: Java 25
- **Base de Datos**: PostgreSQL
- **ORM**: JPA/Hibernate
- **Seguridad**: Spring Security + JWT
- **Build**: Gradle
- **Testing**: JUnit 5 + Mockito

---

## 📂 Estructura de Directorios

```
src/main/java/com/drakon/store/
├── domain/                          # Núcleo del negocio
│   ├── api/                        # Puertos de entrada
│   ├── spi/                        # Puertos de salida
│   ├── model/                      # Modelos de dominio
│   ├── usecase/                    # Casos de uso
│   └── exception/                  # Excepciones
├── application/                     # Capa de transformación
│   ├── dto/                        # Data Transfer Objects
│   │   ├── request/
│   │   └── response/
│   └── mapper/                     # Transformadores
└── infrastructure/                  # Detalles técnicos
    ├── configuration/              # Configuración Spring
    ├── input/                      # Entrada (Controllers)
    └── output/                     # Salida (JPA)
```

---

## 🚀 Cómo Ejecutar

### Requisitos
- Java 25 o superior
- PostgreSQL 12+
- Gradle (incluido en el proyecto)

### Pasos

1. **Crear base de datos** (en PostgreSQL):
```sql
CREATE DATABASE tienda_db;
```

2. **Configurar credenciales** en `application.yaml`:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tienda_db
    username: postgres
    password: tu_contraseña
```

3. **Compilar y ejecutar**:
```bash
./gradlew build
./gradlew bootRun
```

4. **Acceder a la aplicación**:
```
http://localhost:8080/api
```

---

## 📡 Endpoints Principales

### Autenticación
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/autenticacion/registro` | Registrar nuevo usuario |
| POST | `/api/autenticacion/iniciar-sesion` | Iniciar sesión |

### Productos
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/productos` | Obtener todos los productos |
| GET | `/api/productos/{id}` | Obtener producto por ID |
| POST | `/api/productos` | Crear nuevo producto |
| PUT | `/api/productos/{id}` | Actualizar producto |
| DELETE | `/api/productos/{id}` | Eliminar producto |

---

## 🔐 Seguridad

- Contraseñas encriptadas con BCrypt
- Tokens JWT para autenticación sin estado
- Validación de datos en la capa de dominio
- Manejo centralizado de excepciones

---

## 📝 Notas de Desarrollo

- El proyecto sigue **arquitectura hexagonal** estricta
- La lógica de negocio está **completamente desacoplada** de frameworks
- Los casos de uso contienen **todas las validaciones** de negocio
- Las excepciones se lanzan desde el dominio y se capturan en infraestructura

---

## 👨‍💻 Autor

**Jefferson David Mejia Torres**  
Estudiante de Ingeniería de Software  
Universidad del Cauca

**Asesor**: Carlos Arturo Barrientos

---

## 📄 Licencia

Este proyecto es de uso académico.

---

## 📞 Contacto

Para preguntas o sugerencias sobre este proyecto, contactar al profesor/asesor o al estudiante responsable.

---

**Última actualización**: Abril 2026