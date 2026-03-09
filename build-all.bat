@echo off
echo Building all microservices...

cd config-service
call mvn clean package -DskipTests
cd ..

cd discovery-service
call mvn clean package -DskipTests
cd ..

cd video-service
call mvn clean package -DskipTests
cd ..

cd user-service
call mvn clean package -DskipTests
cd ..

cd gateway-service
call mvn clean package -DskipTests
cd ..

echo Building frontend application...
cd video-streaming-frontend
call npm install
call npm run build
cd ..

echo All services built successfully!
pause
