CREATE DATABASE IF NOT EXISTS nutritiondb;
USE nutritiondb;

CREATE TABLE IF NOT EXISTS nutrition (
    -- Unique identifier for each nutrition item
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    -- Name of the nutrition item, e.g., "Apple", "Whey Protein"
    name VARCHAR(255) NOT NULL,
    
    -- Type of the nutrition item, e.g., "Food", "Supplement"
    type VARCHAR(100),
    
    -- Nutritional values
    calories INT, -- Total calories
    protein_g FLOAT, -- Protein in grams
    fat_g FLOAT, -- Fat in grams
    carbohydrates_g FLOAT, -- Carbohydrates in grams
    
    -- Timestamps for creation and last update
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Indexes for performance improvement on large datasets
CREATE INDEX idx_name ON nutrition(name);
CREATE INDEX idx_type ON nutrition(type);
