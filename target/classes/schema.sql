CREATE TABLE IF NOT EXISTS questions (
    ques_id INT PRIMARY KEY,
    title VARCHAR(255),
    optionA VARCHAR(255),
    optionB VARCHAR(255),
    optionC VARCHAR(255),
    ans INT,
    chose INT
);
