🎟️ RaveTix - Sistema de Venta de Entradas para Eventos
📅 Última actualización: {{Fecha Actualizada}}
📌 Estado del Proyecto: En Desarrollo 🚧


🎥 Demo en Acción
🔗 Ver Video de la Aplicación

📌 Características Principales
✅ Creación y gestión de eventos.
✅ Venta y asignación de tickets.
✅ Procesamiento de pagos con TARJETA, PAYPAL, YAPE.
✅ Soporte para bases de datos PostgreSQL.

📸 Capturas de Pantalla

📂 Estructura del Proyecto
📁 domain/ → Modelos de datos con JPA.
📁 enums/ → Estados y tipos de pago.
📁 mapper/ → Conversión de entidades.
📁 model/ → DTOs de servicio.
📁 repository/ → Interfaces JPA.
📁 service/ → Lógica de negocio.
📁 controller/ → Endpoints REST.

🛠️ Tecnologías Utilizadas
Tecnología	Descripción
☕ Java 17	Lenguaje de programación
🌱 Spring Boot	Framework principal
🔄 Spring Data JPA	Persistencia de datos
🐘 PostgreSQL	Base de datos
🎨 TailwindCSS	Estilos frontend
🛳️ Docker	Contenedores
📜 Documentación de la API
🔹 Obtener Todos los Eventos

http

GET /api/eventos
📌 Ejemplo de Respuesta:

json

[
   {
      "id": 1,
      "nombre": "Festival de Música",
      "fecha": "2025-08-10",
      "zona": "VIP",
      "precio": 150.00
   }
]

🚀 Roadmap del Proyecto
 Implementar autenticación con JWT 🔒

 Agregar notificaciones por correo 📩

 Integración con pagos en tiempo real 💳

 Mejorar UI con TailwindCSS 🎨

📥 Instalación y Uso
1️⃣ Clonar el repositorio

git clone https://github.com/tu-usuario/RaveTix.git
cd RaveTix
2️⃣ Configurar el entorno

properties

# Configuración de la base de datos
spring.datasource.url=jdbc:postgresql://localhost:5432/ravetix
spring.datasource.username=postgres
spring.datasource.password=admin
3️⃣ Ejecutar la aplicación


mvn spring-boot:run
4️⃣ API disponible en:
📍 http://localhost:8080
📍 http://localhost:8080/swagger-ui.html

🤝 Contribuciones
💡 ¡Se aceptan PRs y sugerencias! Abre un issue o haz un fork.

📧 Contáctame en: tu-email@example.com

📜 Licencia: MIT
