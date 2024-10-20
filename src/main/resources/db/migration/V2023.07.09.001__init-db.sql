-- Create notes table
CREATE TABLE IF NOT EXISTS notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    content VARCHAR(500)
);

DELETE FROM notes;


