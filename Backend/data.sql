-- Create the k_leung database
CREATE DATABASE IF NOT EXISTS k_leung;

-- Switch to the k_leung database
USE k_leung;

-- Create the hotel table
CREATE TABLE IF NOT EXISTS hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hotelName VARCHAR(255) NOT NULL,
    ranking INT NOT NULL,
    location VARCHAR(255) NOT NULL
);

INSERT INTO hotel (hotelName, ranking, location) VALUES 
('The Westin Nova Scotian', 4, 'Halifax'),
('Delta Hotels by Marriott Barrington', 4, 'Halifax'),
('Cambridge Suites Hotel Halifax', 4, 'Halifax'),
('The Hollis Halifax - a DoubleTree Suites by Hilton Hotel', 4, 'Halifax'),
('Prince George Hotel', 4, 'Halifax'),
('Halifax Marriott Harbourfront Hotel', 4, 'Halifax'),
('Hampton Inn by Hilton Halifax Downtown', 3, 'Halifax'),
('Four Points by Sheraton Halifax', 3, 'Halifax'),
('Lord Nelson Hotel & Suites', 3, 'Halifax'),
('Comfort Hotel Bayer\'s Lake', 3, 'Halifax'),
('Digby Pines Golf Resort & Spa', 4, 'Digby'),
('Annapolis Royal Inn', 3, 'Annapolis Royal'),
('Trout Point Lodge of Nova Scotia', 5, 'Kemptville'),
('Liscombe Lodge Resort and Conference Centre', 4, 'Liscomb'),
('Inverary Resort', 3, 'Baddeck'),
('Pictou Lodge Beach Resort', 4, 'Pictou'),
('The Keltic Lodge at the Highlands', 4, 'Ingonish Beach'),
('Fox Harb\'r Resort', 5, 'Wallace'),
('White Point Beach Resort', 4, 'White Point'),
('Oceanstone Seaside Resort', 4, 'Indian Harbour');
