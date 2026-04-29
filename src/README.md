# ⛳ Tu tiempo Golf

Dashboard web local que muestra las condiciones meteorológicas de Oviedo con una puntuación diaria basada en marcas de golf: **Birdie**, **Par** o **Bogey**.

## 🚀 Tecnologías

- **Java 21** + **Maven**
- **Javalin** — servidor web ligero
- **Gson** — parseo de JSON
- **Open-Meteo API** — datos meteorológicos en tiempo real (gratuita, sin registro)
- **JUnit 5** — tests unitarios

## ⚙️ Cómo ejecutar

1. Clona el repositorio
```bash
git clone https://github.com/tuUsuario/golf-weather-dashboard.git
```

2. Abre el proyecto en IntelliJ y **Maven → Reload Project**

3. Ejecuta `Main.java`

4. Abre el navegador en `http://localhost:7000`

## 🏗️ Arquitectura

src/main/java/com/ignacio/

├── api/          → Clases para deserializar el JSON de Open-Meteo

├── logica/       → AnalizadorGolf (Birdie/Par/Bogey) y GeneradorNota

├── modelo/       → DatosMeteoHoy y PrediccionDia

├── servicio/     → Llamada HTTP a Open-Meteo

└── web/          → Generación dinámica del HTML