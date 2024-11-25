SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE category;
TRUNCATE TABLE location;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO category (name, symbol, description)
VALUES
    ('Restaurant', '🍴', 'Places to dine, including cafes, bistros, and fine dining establishments'),
    ('Coffee Shop', '☕', 'Locations serving coffee, tea, and light snacks'),
    ('Park', '🌳', 'Public parks and recreation areas'),
    ('Museum', '🏛️', 'Cultural and historical landmarks, including museums and galleries'),
    ('Shopping Mall', '🛍️', 'Large shopping centers with multiple retail stores'),
    ('Supermarket', '🛒', 'Stores selling groceries and everyday items'),
    ('Gas Station', '⛽', 'Stations for refueling vehicles'),
    ('Hotel', '🏨', 'Places offering lodging and accommodations'),
    ('Hospital', '🏥', 'Healthcare facilities and emergency services'),
    ('School', '🏫', 'Educational institutions such as schools and universities'),
    ('Theater', '🎭', 'Venues for movies, plays, and other performances'),
    ('Stadium', '🏟️', 'Sports arenas and large event venues'),
    ('Airport', '✈️', 'Locations for air travel and logistics'),
    ('Train Station', '🚉', 'Facilities for train transport and commuting'),
    ('Beach', '🏖️', 'Coastal areas for leisure and activities'),
    ('Amusement Park', '🎢', 'Theme parks and entertainment centers'),
    ('Pharmacy', '💊', 'Stores selling medicines and healthcare products'),
    ('Library', '📚', 'Places to borrow and read books or access information'),
    ('Parking Lot', '🅿️', 'Spaces designated for vehicle parking'),
    ('Police Station', '🚔', 'Facilities for law enforcement and public safety');

INSERT INTO location (name, category_id, user_id, status, description, coordinate)
VALUES
    ('Central Park', 3, 1, 'public', 'A large public park in the city center', ST_GeomFromText('POINT(-73.968285 40.785091)', 4326)),
    ('Museum of Modern Art', 4, 1, 'public', 'A museum featuring modern and contemporary art', ST_GeomFromText('POINT(-73.977621 40.761433)', 4326)),
    ('Joe\'s Coffee', 2, 1, 'private', 'A cozy coffee shop known for its espresso', ST_GeomFromText('POINT(-73.985135 40.748817)', 4326)),
    ('Oceanview Beach', 15, 1, 'public', 'A relaxing beach with great ocean views', ST_GeomFromText('POINT(-74.005974 40.712776)', 4326)),
    ('Grandview Hotel', 8, 1, 'public', 'A luxurious hotel with a panoramic city view', ST_GeomFromText('POINT(-73.985656 40.748944)', 4326));
