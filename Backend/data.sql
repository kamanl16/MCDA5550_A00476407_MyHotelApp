-- Create the k_leung database
CREATE DATABASE IF NOT EXISTS k_leung;

-- Switch to the k_leung database
USE k_leung;

-- Sample data for RoomTypes table
INSERT INTO RoomTypes (type_name, occupancy, bed_type, view, size, type_name_tc, bed_type_tc, view_tc, description, description_tc)
VALUES
    ('Standard Room', 2, 'Double Size', 'City View', '300 sq ft', '標準房間', '雙人床', '城市景', 'A cozy room with a beautiful city view, perfect for two.', '兩人適宜，享有美麗的城市景觀的舒適房間'),
    ('Deluxe Room', 2, 'King Size', 'Harbour View', '400 sq ft', '豪華房間', '雙人床', '海港景', 'Spacious room with a king-sized bed and stunning harbour views.', '寬敞的房間，配有特大號床和絕佳的海港景觀'),
    ('Suite', 4, 'King Size', 'Harbour View', '600 sq ft', '家庭套房', '特大床', '海港景', 'Luxurious suite with ample space for a family or group, featuring panoramic harbour views.', '豪華套房，為家庭或團體提供充足空間，享有全景海港景觀');
    
-- Sample data for Rooms table
INSERT INTO Rooms (type_id, price_per_night) VALUES
(1, 100.00),  -- Standard Room 1
(1, 120.00),  -- Standard Room 3
(2, 150.00),  -- Deluxe Room 1
(2, 150.00),  -- Deluxe Room 2
(3, 200.00),  -- Suite 1
(3, 200.00);  -- Suite 2

-- Sample data for Rooms table
INSERT INTO k_leung.ImageData (cat_type, cat_id, path, title) VALUES
(2, 1, 'https://dev.cs.smu.ca/~k_leung/HotelImages/Swimmingpool.png', 'Swimming Pool'),
(2, 2, 'https://dev.cs.smu.ca/~k_leung/HotelImages/gym.png', 'Gym'),
(2, 3, 'https://dev.cs.smu.ca/~k_leung/HotelImages/Reception.png', 'Reception'),
(1, 1, 'https://dev.cs.smu.ca/~k_leung/HotelImages/StandardRmCityViewDoubleBed.png', 'Standard Room'),
(1, 1, 'https://dev.cs.smu.ca/~k_leung/HotelImages/StandardRmCityViewDoubleBedBath.png	', 'Standard Room Bathroom'),
(1, 2, 'https://dev.cs.smu.ca/~k_leung/HotelImages/DeluxeRmHarbourViewKingBed.png', 'Deluxe Room'),
(1, 2, 'https://dev.cs.smu.ca/~k_leung/HotelImages/DeluxeRmHarbourViewKingBedBath.png', 'Deluxe Room Bathroom'),
(1, 3, 'https://dev.cs.smu.ca/~k_leung/HotelImages/SuiteHarbourViewKingBed.png', 'Suite'),
(1, 3, 'https://dev.cs.smu.ca/~k_leung/HotelImages/SuiteHarbourViewKingBedBath.png', 'Suite Bathroom'),
(1, 3, 'https://dev.cs.smu.ca/~k_leung/HotelImages/SuiteHarbourViewKingBedLiving.png', 'Suite');
