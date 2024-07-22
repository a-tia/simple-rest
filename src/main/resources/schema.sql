-- test database setup
CREATE TABLE app_user (
                          id LONG AUTO_INCREMENT PRIMARY KEY, -- auto-increments the id by default
                          username VARCHAR(50) NOT NULL UNIQUE,
                          gender VARCHAR(20) NOT NULL CHECK (gender IN ('male', 'female')),
                          age INT NOT NULL,
                          account_creation_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);