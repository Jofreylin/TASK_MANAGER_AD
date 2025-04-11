# 📋 Task Manager AD

**Task Manager AD** es una aplicación móvil desarrollada en **Kotlin** para dispositivos Android. Su objetivo es ofrecer una forma simple y eficaz de gestionar tareas personales, permitiendo al usuario registrar, editar, completar o eliminar sus pendientes de manera intuitiva.

## 🚀 Funcionalidades principales

- ✅ Crear tareas con nombre y descripción.
- 📝 Editar contenido de una tarea.
- 🔄 Cambiar el estado de la tarea con un botón tipo *toggle*.
- 🗑️ Eliminar tareas.
- 💾 Guardado persistente: las tareas se mantienen aún después de cerrar la app, gracias a `SharedPreferences` y `Gson`.
- 🎨 Interfaz clara:  
  - Las tareas **pendientes** se muestran en color **anaranjado cálido**.  
  - Las tareas **completadas** aparecen en **verde claro**.

## ⚙️ Requisitos y configuración

- 📱 **Versión mínima de Android:** 10.0 (API 29)
- 🎯 **Versión objetivo:** Android 13 (API 33)
- 🧱 **Compile SDK:** 34
- 🛠️ **Android Studio:** *Hedgehog | 2023.1.1*
- 📦 **Dependencias clave:**
  - [`com.google.code.gson:gson:2.12.1`](https://github.com/google/gson): para la serialización y deserialización de objetos de tareas.
  - [`androidx.recyclerview:recyclerview:1.1.0`](https://developer.android.com/jetpack/androidx/releases/recyclerview): para mostrar y actualizar la lista de tareas en tiempo real.
  - [`androidx.core`, `lifecycle-runtime`, `activity-compose`, etc.`]: dependencias base incluidas por defecto en proyectos recientes.
- 💡 Se utiliza arquitectura sencilla con `MainActivity`, un `RecyclerView` para la lista y clases de modelo para estructurar los datos.
> 🔸 *Nota:* Aunque el proyecto fue generado con soporte para Jetpack Compose, el flujo principal de la aplicación se basa en `RecyclerView` clásico y vistas tradicionales de Android.



## 🏗️ Estructura del código (breve explicación)

- `Task.kt` → Clase de datos que representa una tarea con nombre, descripción y estado (pendiente o completada).
- `TaskAdapter.kt` → Adaptador del RecyclerView que maneja la lógica de mostrar tareas, colores, y responder a clics del usuario.
- `MainActivity.kt` → Actividad principal que orquesta el comportamiento de la app, incluyendo la persistencia de datos.



## 🧑‍💻 Desarrollado por

**Jofreylin Perez Valdez**  
Matrícula: `2021-30-3-0001`  
Materia: *Programación Móvil 1 - IDS-368*  
**Universidad Domínico Americana (UNICDA)**


