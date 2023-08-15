INSERT INTO students (first_name, last_name, age, email, academic_performance,
                      rating, personal_discount, registration_date)
VALUES
    ('John', 'Doe', 20, 'john.doe@example.com', 85, 4.5, 10.00, '2023-01-15'),
    ('Jane', 'Smith', 22, 'jane.smith@example.com', 90, 4.8, 15.00, '2023-02-20'),
    ('Michael', 'Johnson', 19, 'michael.johnson@example.com', 78, 4.0, 5.00, '2023-03-10'),
    ('Alice', 'Johnson', 21, 'alice.johnson@example.com', 92, 4.7, 12.50, '2023-04-05'),
    ('Robert', 'Smith', 24, 'robert.smith@example.com', 80, 3.9, 8.00, '2023-03-12'),
    ('Olivia', 'Brown', 19, 'olivia.brown@example.com', 88, 4.2, 15.00, '2023-05-20');


INSERT INTO courses (title, description, duration_minute, course_discount, cost)
VALUES
    ('Introduction to Programming', 'Learn the basics of programming.', 180, 20.00, 99.99),
    ('Web Development Fundamentals', 'Build interactive websites.', 240, 15.00, 129.99),
    ('Data Science Essentials', 'Master data analysis techniques.', 300, 25.00, 149.99),
    ('Advanced Programming Concepts', 'Explore advanced programming topics.', 240, 15.00, 149.99),
    ('Full Stack Web Development', 'Build complete web applications.', 360, 20.00, 199.99),
    ('Machine Learning Fundamentals', 'Introduction to machine learning techniques.', 280, 10.00, 179.99);


INSERT INTO instructors (first_name, last_name, msisdn, contact_email, qualification)
VALUES
    ('Sarah', 'Williams', '123-456-7890', 'sarah.williams@example.com', 'MSc in Computer Science'),
    ('David', 'Brown', '987-654-3210', 'david.brown@example.com', 'PhD in Web Development'),
    ('Emily', 'Davis', '555-123-4567', 'emily.davis@example.com', 'BSc in Data Science'),
    ('Thomas', 'Miller', '777-888-9999', 'thomas.miller@example.com', 'PhD in Computer Science'),
    ('Emma', 'Wilson', '555-789-1234', 'emma.wilson@example.com', 'MSc in Web Development'),
    ('William', 'Davis', '888-333-4444', 'william.davis@example.com', 'BSc in Artificial Intelligence');


INSERT INTO schedule (course_id, instructor_id, start_time, end_time, location)
VALUES
    (1, 1, '2023-08-20 10:00:00', '2023-08-20 12:00:00', 'Room A'),
    (2, 2, '2023-08-22 14:00:00', '2023-08-22 16:00:00', 'Room B'),
    (3, 3, '2023-08-25 09:00:00', '2023-08-25 12:00:00', 'Room C'),
    (4, 4, '2023-08-28 13:00:00', '2023-08-28 15:00:00', 'Room D'),
    (5, 5, '2023-08-30 10:00:00', '2023-08-30 13:00:00', 'Room E'),
    (6, 6, '2023-09-02 09:00:00', '2023-09-02 11:30:00', 'Room F');


INSERT INTO classes (schedule_id, student_id, attendance)
VALUES
    (1, 1, true),
    (2, 2, true),
    (3, 3, false),
    (4, 4, true),
    (5, 5, false),
    (6, 6, true);


INSERT INTO resources (course_id, description, link)
VALUES
    (1, 'Introduction slides', 'https://example.com/intro-slides.pdf'),
    (2, 'CSS cheat sheet', 'https://example.com/css-cheat-sheet.pdf'),
    (3, 'Data analysis toolkit', 'https://example.com/data-analysis-toolkit.zip'),
    (4, 'Advanced programming guide', 'https://example.com/advanced-programming.pdf'),
    (5, 'Full stack project template', 'https://example.com/full-stack-template.zip'),
    (6, 'Machine learning dataset', 'https://example.com/machine-learning-data.csv');


INSERT INTO enrollment_requests (course_id, student_id, request_date)
VALUES
    (1, 2, '2023-08-10'),
    (2, 3, '2023-08-12'),
    (3, 1, '2023-08-15'),
    (4, 5, '2023-08-18'),
    (5, 6, '2023-08-21'),
    (6, 4, '2023-08-25');


INSERT INTO payments (student_id, course_id, payment_time, amount)
VALUES
    (1, 1, '2023-08-18 15:30:00', 89.99),
    (2, 2, '2023-08-20 09:45:00', 109.99),
    (3, 3, '2023-08-25 11:20:00', 124.99),
    (4, 4, '2023-08-25 14:15:00', 134.99),
    (5, 5, '2023-08-28 10:30:00', 184.99),
    (6, 6, '2023-09-01 12:00:00', 169.99);
