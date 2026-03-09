-- Create databases
CREATE DATABASE IF NOT EXISTS video_db;
CREATE DATABASE IF NOT EXISTS user_db;

-- Create users for each service
CREATE USER IF NOT EXISTS 'video_user'@'%' IDENTIFIED BY 'video_password';
CREATE USER IF NOT EXISTS 'user_user'@'%' IDENTIFIED BY 'user_password';

-- Grant privileges
GRANT ALL PRIVILEGES ON video_db.* TO 'video_user'@'%';
GRANT ALL PRIVILEGES ON user_db.* TO 'user_user'@'%';

-- Apply changes
FLUSH PRIVILEGES;
