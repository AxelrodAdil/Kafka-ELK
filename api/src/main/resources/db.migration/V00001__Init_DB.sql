CREATE TABLE students (
    student_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(75),
    last_name VARCHAR(75),
    age INT,
    email VARCHAR(100) UNIQUE,
    academic_performance INT,
    rating DECIMAL(10, 2),
    personal_discount DECIMAL(10, 2),
    registration_date DATE
);

CREATE TABLE courses (
    course_id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100),
    description TEXT,
    duration_minute INT,
    course_discount DECIMAL(10, 2),
    cost DECIMAL(10, 2)
);

CREATE TABLE instructors (
    instructor_id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(75),
    last_name VARCHAR(75),
    msisdn VARCHAR(16),
    contact_email VARCHAR(100),
    qualification TEXT
);

CREATE TABLE schedule (
    schedule_id BIGSERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES courses(course_id),
    instructor_id BIGINT REFERENCES instructors(instructor_id),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    location VARCHAR(100)
);

CREATE TABLE classes (
    class_id BIGSERIAL PRIMARY KEY,
    schedule_id BIGINT REFERENCES schedule(schedule_id),
    student_id BIGINT REFERENCES students(student_id),
    attendance BOOLEAN
);

CREATE TABLE resources (
    resource_id BIGSERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES courses(course_id),
    description TEXT,
    link VARCHAR(200)
);

CREATE TABLE enrollment_requests (
    request_id BIGSERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES courses(course_id),
    student_id BIGINT REFERENCES students(student_id),
    request_date DATE
);

CREATE TABLE payments (
    payment_id BIGSERIAL PRIMARY KEY,
    student_id BIGINT REFERENCES students(student_id),
    course_id BIGINT REFERENCES courses(course_id),
    payment_time TIMESTAMP,
    amount DECIMAL(10, 2)
);

CREATE TABLE discussions (
    discussion_id BIGSERIAL PRIMARY KEY,
    course_id BIGINT REFERENCES courses(course_id),
    title VARCHAR(100),
    message TEXT,
    post_date TIMESTAMP
);
