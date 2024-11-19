CREATE TABLE IF NOT EXISTS category
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    symbol      VARCHAR(10)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    created     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated     TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

TRUNCATE TABLE category;

INSERT INTO category (name, symbol, description) VALUES ('Restaurant', '🍴', 'Places to dine, including cafes, bistros, and fine dining establishments');
INSERT INTO category (name, symbol, description) VALUES ('Coffee Shop', '☕', 'Locations serving coffee, tea, and light snacks');
INSERT INTO category (name, symbol, description) VALUES ('Park', '🌳', 'Public parks and recreation areas');
INSERT INTO category (name, symbol, description) VALUES ('Museum', '🏛️', 'Cultural and historical landmarks, including museums and galleries');
INSERT INTO category (name, symbol, description) VALUES ('Shopping Mall', '🛍️', 'Large shopping centers with multiple retail stores');
INSERT INTO category (name, symbol, description) VALUES ('Theater', '🎭', 'Venues for performances such as plays, musicals, and concerts');
INSERT INTO category (name, symbol, description) VALUES ('Stadium', '🏟️', 'Large venues for sports, concerts, and events');
INSERT INTO category (name, symbol, description) VALUES ('Hotel', '🏨', 'Places to stay overnight, including motels and inns');
INSERT INTO category (name, symbol, description) VALUES ('Library', '📚', 'Public or private libraries for reading and studying');
INSERT INTO category (name, symbol, description) VALUES ('Airport', '✈️', 'Air transportation hubs for domestic and international travel');
INSERT INTO category (name, symbol, description) VALUES ('Train Station', '🚉', 'Railway stations for local and long-distance trains');
INSERT INTO category (name, symbol, description) VALUES ('Bus Stop', '🚏', 'Designated stops for public bus transportation');
INSERT INTO category (name, symbol, description) VALUES ('Beach', '🏖️', 'Coastal areas for relaxation, swimming, and sunbathing');
INSERT INTO category (name, symbol, description) VALUES ('Hospital', '🏥', 'Medical facilities for treatment and emergencies');
INSERT INTO category (name, symbol, description) VALUES ('Pharmacy', '💊', 'Places to purchase medicine and health-related items');
INSERT INTO category (name, symbol, description) VALUES ('Gym', '🏋️', 'Fitness centers for exercise and training');
INSERT INTO category (name, symbol, description) VALUES ('Cinema', '🎬', 'Movie theaters for watching films');
INSERT INTO category (name, symbol, description) VALUES ('Zoo', '🐾', 'Animal parks for family visits and education');
INSERT INTO category (name, symbol, description) VALUES ('Aquarium', '🐠', 'Facilities for viewing aquatic life');
INSERT INTO category (name, symbol, description) VALUES ('Amusement Park', '🎢', 'Parks with rides, games, and attractions');