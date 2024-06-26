CREATE DATABASE usersdb2;

USE usersdb2;

CREATE TABLE users(
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    project VARCHAR(50) NOT NULL,
    bounded_context VARCHAR(255) NOT NULL,
    namespace VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE service_tags (
    user_id BIGINT NOT NULL,
    tags VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
