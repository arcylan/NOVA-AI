🌐 Live Demo

👉 https://nova-ai-1dzt.onrender.com

✨ Features
🔐 JWT Authentication
Secure login & signup
Token-based session handling
📧 Email Verification
Sends confirmation email on registration
Uses SMTP (Gmail)
🤖 AI Chat System
Integrated with Google Gemini (via Spring AI)
Context-based conversation per user
💬 Chat Persistence
Stores chat history in MySQL
Maintains user-specific conversations
🔒 Spring Security
Protected routes
Custom JWT filter
☁️ Cloud Deployment
Backend deployed on Render
Database hosted on Railway
🛠️ Tech Stack

Backend

Java 21
Spring Boot
Spring Security
Spring Data JPA
Spring AI

Frontend

HTML, CSS, JavaScript (Vanilla)

Database

MySQL (Railway)

Deployment

Docker
Render (Backend Hosting)
📁 Project Structure
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── config/
 └── resources/
⚙️ Environment Variables

⚠️ Do NOT hardcode secrets. Use environment variables:

SPRING_DATASOURCE_URL=your_db_url
SPRING_DATASOURCE_USERNAME=your_db_username
SPRING_DATASOURCE_PASSWORD=your_db_password

JWT_SECRET=your_secret_key

SPRING_MAIL_USERNAME=your_email
SPRING_MAIL_PASSWORD=your_app_password

SPRING_AI_GOOGLE_GENAI_API_KEY=your_api_key
>>> Docker Setup
Build JAR
mvn clean package
Run with Docker
docker build -t nova-ai .
docker run -p 8080:8080 nova-ai
>>> How to Run Locally
Clone repo
git clone https://github.com/arcylan/NOVA-AI.git
Configure application.properties
Run project
mvn spring-boot:run
 >>>API Endpoints
Auth
POST /auth/register
POST /auth/login
Chat
POST /chat/create
GET /api/{chatId}/{message}
>>> Learning Highlights
Implemented stateless authentication using JWT
Integrated AI APIs using Spring AI
Designed secure backend architecture
Deployed full-stack system with Docker + Cloud DB
Debugged real-world issues like:
Token validation
CORS
Database inconsistencies
Cloud deployment errors
>>> Future Improvements
Chat history UI
Streaming AI responses
Redis caching
Rate limiting
OAuth login (Google/GitHub)
 >>>Author

Arcylan Reyaz

GitHub: https://github.com/arcylan
>>> If you like this project

Give it a ⭐ on GitHub — it helps a lot!
