# 🎬 StreamFlix - Full Stack Video Streaming Platform

A complete video streaming platform with microservices backend and React frontend, featuring JWT authentication, video catalog, and modern UI.

## 🏗️ Architecture Overview

```
┌─────────────────┐
│   Frontend    │  React.js + Vite + Tailwind CSS
│   (Port 3000)   │
├─────────────────┤
│   API Gateway   │  Spring Cloud Gateway
│   (Port 8080)   │
├─────────────────┤
│  Discovery     │  Eureka Service Registry
│  (Port 8761)   │
├─────────────────┤
│   Config       │  Spring Cloud Config
│  (Port 8888)   │
├─────────────────┤
│   User Service │  Spring Boot + JWT Auth
│  (Port 8082)   │
├─────────────────┤
│  Video Service│  Spring Boot + JWT Auth
│  (Port 8081)   │
├─────────────────┤
│   Database     │  MySQL 8.0
│  (Port 3306)   │
└─────────────────┘
```

## 🚀 Quick Start

### Prerequisites
- Docker & Docker Compose
- Node.js 16+ (for local frontend development)
- Java 17+ (for backend development)

### Option 1: Complete Stack (Recommended)
```bash
# Start all services
docker-compose -f docker-compose.full-stack.yml up -d

# Access services
# Frontend: http://localhost:3000
# API Gateway: http://localhost:8080
# User Service: http://localhost:8082
# Video Service: http://localhost:8081
# Config Service: http://localhost:8888
# Discovery: http://localhost:8761
```

### Option 2: Backend Only
```bash
# Start backend services
docker-compose -f docker-compose.mysql.yml up -d

# Frontend development
cd frontend
npm install
npm run dev
```

## 📁 Project Structure

```
streaming-platform/
├── backend/
│   ├── config-service/          # Configuration management
│   ├── discovery-service/       # Service registry (Eureka)
│   ├── user-service/           # User management & authentication
│   ├── video-service/          # Video catalog management
│   ├── gateway-service/         # API Gateway
│   ├── common-seeder/          # Database seeding utilities
│   ├── mysql-init/             # Database initialization
│   └── docker-compose.mysql.yml  # Backend services
├── frontend/                   # React.js application
│   ├── src/
│   │   ├── components/        # Reusable UI components
│   │   ├── pages/            # Page components
│   │   ├── services/          # API service layers
│   │   ├── hooks/             # Custom React hooks
│   │   └── config/            # Configuration
│   ├── public/                 # Static assets
│   ├── package.json
│   ├── vite.config.js
│   ├── tailwind.config.js
│   ├── Dockerfile
│   └── nginx.conf
└── docker-compose.full-stack.yml  # Complete stack
```

## 🔐 Authentication System

### JWT-Based Authentication
- **Login**: `/api/auth/login` - Returns JWT token with user info
- **Token Validation**: `/api/auth/validate` - Validates JWT tokens
- **Protected Routes**: All video endpoints require valid JWT
- **Token Storage**: localStorage with automatic header injection
- **Session Management**: Stateless authentication

### User Roles & Permissions
- **ROLE_USER**: Standard authenticated user
- **Public Access**: Search, browse, and video details
- **Protected Access**: Full video catalog, user management

## 🎬 Video Management

### Features
- **Video Catalog**: Complete CRUD operations
- **Search**: Full-text search across titles and descriptions
- **Filtering**: By category (Action, Comedy, Drama, Sci-Fi, Horror)
- **Type Filtering**: Movies vs TV Series
- **Ratings**: 5-star rating system
- **Metadata**: Title, description, thumbnail, release year

### Database Schema
```sql
-- Videos Table
CREATE TABLE videos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    thumbnail_url VARCHAR(500),
    category VARCHAR(50),
    type ENUM('MOVIE', 'TV_SERIES'),
    rating DECIMAL(2,1),
    release_year INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Users Table
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## 🎨 Frontend Features

### Modern UI/UX
- **Dark Theme**: Professional streaming platform design
- **Responsive**: Mobile-first approach with Tailwind CSS
- **Loading States**: Skeleton screens and spinners
- **Error Handling**: User-friendly error messages
- **Smooth Transitions**: Hover effects and animations
- **Accessibility**: ARIA labels and keyboard navigation

### Components
- **Layout**: Header, footer, navigation
- **VideoCard**: Thumbnail, metadata, watch button
- **LoadingSpinner**: Multiple sizes for different contexts
- **Auth Forms**: Login and registration with validation

### Routing
- **Public Routes**: Home, browse, search
- **Protected Routes**: Video catalog (requires auth)
- **Authentication Guards**: Redirect unauthenticated users
- **404 Handling**: Fallback for unknown routes

## 🔧 Development

### Backend Development
```bash
# Build specific service
cd user-service
mvn clean package -DskipTests
docker-compose -f docker-compose.mysql.yml build user-service

# Run tests
mvn test

# View logs
docker logs user-service
```

### Frontend Development
```bash
# Install dependencies
cd frontend
npm install

# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview
```

## 🐳 Docker Configuration

### Multi-Stage Frontend Build
- **Stage 1**: Node.js build environment
- **Stage 2**: Nginx production server
- **Optimization**: Gzip compression, static asset caching
- **Security**: Custom headers, CORS handling

### Service Networking
- **Bridge Network**: All services communicate via Docker bridge
- **Service Discovery**: Eureka for service registration
- **API Gateway**: Single entry point for all client requests
- **Database**: Persistent MySQL data volume

## 📊 Monitoring & Logging

### Application Logs
- **Structured Logging**: JSON format with correlation IDs
- **Health Checks**: Docker health endpoints
- **Service Discovery**: Eureka dashboard at http://localhost:8761
- **API Documentation**: Swagger UI available for all services

### Performance Metrics
- **Response Time**: API gateway performance tracking
- **Database Connections**: HikariCP connection pool monitoring
- **Error Rates**: Authentication and authorization failures

## 🔒 Security Features

### Backend Security
- **JWT Authentication**: Stateless token-based auth
- **Password Encryption**: BCrypt hashing
- **CORS Configuration**: Cross-origin resource sharing
- **Input Validation**: Bean validation on all endpoints
- **SQL Injection Prevention**: JPA parameterized queries

### Frontend Security
- **XSS Protection**: React's built-in sanitization
- **CSRF Protection**: SameSite cookies and secure headers
- **Secure Headers**: Content Security Policy, X-Frame-Options
- **Token Storage**: HttpOnly, Secure cookies in production

## 🚀 Deployment

### Production Deployment
```bash
# Build and deploy complete stack
docker-compose -f docker-compose.full-stack.yml up -d --build

# Scale services
docker-compose -f docker-compose.full-stack.yml up -d --scale user-service=3 --scale video-service=2

# View running containers
docker-compose -f docker-compose.full-stack.yml ps
```

### Environment Variables
```bash
# Backend
SPRING_PROFILES_ACTIVE=production
JWT_SECRET=your-production-secret-here
DATABASE_URL=your-production-database-url

# Frontend
VITE_API_BASE_URL=https://api.yourdomain.com
```

## 🧪 Testing

### Backend Testing
- **Unit Tests**: JUnit 5 for service logic
- **Integration Tests**: @SpringBootTest for API endpoints
- **Security Tests**: Authentication and authorization flows
- **Database Tests**: @DataJpaTest for repository layer

### Frontend Testing
- **Component Tests**: React Testing Library
- **E2E Tests**: Cypress for user flows
- **API Integration**: Mock Service Worker for testing

## 📈 Performance Optimization

### Backend Optimizations
- **Database Indexing**: Optimized queries for video search
- **Connection Pooling**: HikariCP configuration
- **Caching**: Redis for session and frequently accessed data
- **Lazy Loading**: JPA entity relationships optimization

### Frontend Optimizations
- **Code Splitting**: React.lazy for route-based chunks
- **Tree Shaking**: Unused code elimination
- **Asset Optimization**: Image compression and WebP support
- **Bundle Analysis**: Webpack Bundle Analyzer integration

## 🔧 Configuration Management

### Spring Cloud Config
- **Environment-Specific**: Separate configs for dev/staging/prod
- **Dynamic Updates**: Live configuration changes without restart
- **Encryption**: Sensitive configuration encryption
- **Version Control**: Git-backed configuration repository

### Frontend Configuration
- **Environment Variables**: .env files for different environments
- **API Endpoints**: Centralized configuration management
- **Build Configuration**: Vite optimization settings
- **Feature Flags**: Runtime feature toggles

## 🔄 CI/CD Pipeline

### GitHub Actions
```yaml
name: StreamFlix CI/CD
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Run backend tests
        run: ./backend/test.sh
      - name: Run frontend tests
        run: ./frontend/test.sh
  
  build:
    needs: test
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Build Docker images
        run: docker-compose -f docker-compose.full-stack.yml build
      - name: Push to registry
        run: |
          echo ${{ secrets.DOCKER_PASSWORD }} | docker login -u ${{ secrets.DOCKER_USERNAME }} --password-stdin
          docker-compose -f docker-compose.full-stack.yml push
```

## 📚 API Documentation

### Service Endpoints
```yaml
# Authentication Service
POST /api/auth/login          # User login
POST /api/auth/validate       # Token validation
POST /api/users/register       # User registration

# Video Service
GET  /api/videos              # Get all videos (protected)
GET  /api/videos/search?q=query  # Search videos (public)
GET  /api/videos/category/{cat} # Get by category (public)
GET  /api/videos/type/{type}   # Get by type (public)
GET  /api/videos/{id}          # Get video details (protected)

# User Service
GET  /api/users               # Get all users (protected)
GET  /api/users/{id}          # Get user details (protected)
```

### Swagger Documentation
- **User Service**: http://localhost:8082/swagger-ui.html
- **Video Service**: http://localhost:8081/swagger-ui.html
- **Gateway Service**: http://localhost:8080/swagger-ui.html

## 🌍 Browser Support

### Desktop Browsers
- Chrome 90+ ✅
- Firefox 88+ ✅
- Safari 14+ ✅
- Edge 90+ ✅

### Mobile Browsers
- iOS Safari 14+ ✅
- Chrome Mobile 90+ ✅
- Samsung Internet 12+ ✅

## 🤝 Contributing Guidelines

1. **Code Style**: Follow existing patterns and conventions
2. **Testing**: Add tests for new features
3. **Documentation**: Update API docs for new endpoints
4. **Security**: Report vulnerabilities responsibly
5. **Performance**: Consider impact of changes on system performance

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support and questions:
- Create an issue in the GitHub repository
- Check the [Wiki](https://github.com/your-repo/wiki) for documentation
- Review existing [Issues](https://github.com/your-repo/issues) before creating new ones

---

**Built with ❤️ using modern web technologies**
