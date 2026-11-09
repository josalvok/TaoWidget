# Tao Gadget

Widget de Android (Jetpack Glance) con frases del Tao Te Ching.

- Estética "glass": gradiente translúcido + borde luminoso, claro/oscuro automático.
- Redimensionable: mantén pulsado el widget en el escritorio y arrastra los tiradores;
  la tipografía se adapta al tamaño.
- Toca el widget para cambiar de frase. Cambia sola cada día.

## Compilar
1. Abre la carpeta en Android Studio (Ladybug o superior). Genera el wrapper si hace falta.
2. Run ▶ en un dispositivo/emulador API 26+ (recomendado 31+).
3. Escritorio → mantener pulsado → Widgets → "Tao Te Ching".

Por línea de comandos: `gradle assembleDebug` → `app/build/outputs/apk/debug/`.

## Notas
- Los widgets no pueden desenfocar el fondo (limitación de Android), así que el
  cristal es una simulación translúcida.
- Licencia: Apache-2.0. Las frases son paráfrasis propias.

## APK sin Android Studio
Sube el proyecto a un repositorio de GitHub: la acción `.github/workflows/build.yml`
compila el APK (Actions → Build APK → Artifacts → TaoGadget-debug-apk).
En el móvil activa "Instalar apps desconocidas" e instala `app-debug.apk`.
