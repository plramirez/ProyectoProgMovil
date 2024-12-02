# Proyecto de Programación Móvil

Este es un proyecto de aplicación móvil con el objetivo de centralizar todos los eventos culturales y académicos de UNICDA. Desarrollado en Kotlin utilizando Firebase para la autenticación y mensajería en la nube.

## Requisitos

- Android Studio Koala | 2024.1.1 Patch 1
- Kotlin 1.8
- Gradle 7.2.1
- Firebase

## Configuración del Proyecto

1. Clona el repositorio:
    ```bash
    git clone https://github.com/tu-usuario/proyecto-prog-movil.git
    ```
2. Abre el proyecto en Android Studio.

3. Configura Firebase:
    - Ve a [Firebase Console](https://console.firebase.google.com/).
    - Crea un nuevo proyecto o selecciona uno existente.
    - Agrega tu aplicación Android al proyecto de Firebase.
    - Descarga el archivo `google-services.json` y colócalo en el directorio `app` de tu proyecto.

4. Asegúrate de tener las siguientes dependencias en tu archivo `build.gradle.kts`:
    ```kotlin
    dependencies {
        implementation("com.google.android.gms:play-services-base:18.0.1")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
        implementation("androidx.databinding:databinding-runtime:7.2.1")
        implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
        implementation("com.google.firebase:firebase-analytics")
        implementation("com.google.firebase:firebase-database")
        implementation("com.google.firebase:firebase-firestore")
        implementation("com.google.firebase:firebase-auth-ktx")
        implementation("com.google.firebase:firebase-messaging")
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.appcompat)
        implementation(libs.material)
        implementation(libs.androidx.activity)
        implementation(libs.androidx.constraintlayout)
        implementation(libs.androidx.ui.android)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.junit)
        androidTestImplementation(libs.androidx.espresso.core)
    }
    ```

## Estructura del Proyecto

- `data`: Contiene las clases de repositorio para la autenticación.
- `presentation`: Contiene las actividades y vistas de la aplicación.
- `domain`: Contiene las clases de modelo y lógica de negocio.
- `MyFirebaseMessagingService.kt`: Servicio para manejar mensajes de Firebase Cloud Messaging.

## Uso

1. Ejecuta la aplicación en un dispositivo o emulador Android.
2. Regístrate o inicia sesión utilizando la autenticación de Firebase.
3. Envía notificaciones desde la consola de Firebase y verifica que se reciban en la aplicación.

## Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o un pull request para discutir cualquier cambio que desees realizar.
