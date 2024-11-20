CREATE TABLE IF NOT EXISTS category (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    symbol      VARCHAR(10)  NOT NULL,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    );

CREATE TABLE IF NOT EXISTS place (
    id            INT AUTO_INCREMENT PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    category_id   INT NOT NULL,
    user_id       INT NOT NULL,
    status        ENUM('public', 'private') DEFAULT 'private',
    description   TEXT,
    coordinates   GEOMETRY NOT NULL SRID 4326,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    deleted_at    TIMESTAMP DEFAULT NULL,
    FOREIGN KEY (category_id) REFERENCES category (id)
    );