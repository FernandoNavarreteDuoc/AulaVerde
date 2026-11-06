# 🌿 AulaVerde

### Aplicación Android para la gestión de herramientas docentes

**AulaVerde** es una aplicación móvil desarrollada en Android Studio, orientada a facilitar la organización y gestión de información de los profesores de la Escuela de Carabineros.

La aplicación permite a los docentes administrar su perfil personal y gestionar documentos académicos mediante una interfaz sencilla, intuitiva y adaptable a dispositivos Android.

## 📱 Funcionalidades

### 🔐 Autenticación de usuarios
- Registro de profesores con validación de datos.
- Inicio y cierre de sesión.
- Almacenamiento local de información de usuarios.

### 🏠 Pantalla de inicio
- Bienvenida personalizada con el nombre del profesor.
- Tutorial básico para utilizar la aplicación.
- Navegación mediante una barra inferior.

### 👤 Gestión de perfil
- Visualización de nombre y correo electrónico.
- Edición de información personal.
- Selección de fotografía de perfil desde el dispositivo.

### 📁 Gestión de documentos
- Selección de archivos desde el dispositivo.
- Registro de documentos con título, tipo y asignatura.
- Búsqueda de documentos por nombre.
- Visualización de información de documentos.
- Eliminación de elementos de la lista.

## 🛠️ Tecnologías utilizadas

| Tecnología | Uso |
|---|---|
| Kotlin | Lenguaje de programación |
| Android Studio | Entorno de desarrollo |
| Jetpack Compose | Diseño de interfaces |
| Room | Base de datos local |
| MVVM | Arquitectura del proyecto |
| Git y GitHub | Control de versiones y colaboración |

## 🏗️ Arquitectura

El proyecto utiliza el patrón de arquitectura **MVVM (Model-View-ViewModel)**, que permite separar la lógica de negocio de la interfaz de usuario.

- **Model:** representa los datos de la aplicación y su persistencia mediante Room.
- **View:** contiene las interfaces desarrolladas con Jetpack Compose.
- **ViewModel:** administra los estados y coordina la interacción entre la interfaz y los datos.
- **Repository:** conecta los ViewModels con las operaciones de acceso a datos mediante los DAO.

Esta organización facilita el mantenimiento, la reutilización del código y la incorporación de nuevas funcionalidades.

## 🚀 Instalación y ejecución

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/FernandoNavarreteDuoc/AulaVerde.git
   ```

2. Abrir el proyecto en Android Studio.
3. Esperar la sincronización de Gradle.
4. Seleccionar un emulador o conectar un dispositivo Android.
5. Ejecutar la aplicación.

## 📂 Organización del proyecto

```text
com.example.aulaverde
│
├── data/
├── notifications/
├── repository/
├── security/
├── ui/
├── validation/
├── viewmodel/
├── DocumentManagementScreen.kt
└── MainActivity.kt
```

## 👨‍💻 Desarrolladores

Proyecto académico desarrollado por:

- **Fernando Navarrete**
- **Krishna Binimelis**

**Carrera:** Ingeniería en Informática

## 📌 Estado del proyecto

AulaVerde se encuentra en desarrollo académico, con funcionalidades de autenticación, administración de perfil y gestión básica de documentos.

Se contempla continuar mejorando la persistencia de documentos, la experiencia de usuario y las funcionalidades de la aplicación.

---

**AulaVerde 🌿 — Herramientas digitales para docentes.**
