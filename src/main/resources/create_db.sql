CREATE TABLE university_class (
  id SERIAL PRIMARY KEY,
  name varchar(50) NOT NULL
);

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name varchar(50) NOT NULL,
  last_name varchar(50) NOT NULL
);

CREATE TABLE class_student (
  class_id    int REFERENCES university_class (id) ON UPDATE CASCADE ON DELETE CASCADE,
  student_id int REFERENCES student (id) ON UPDATE CASCADE ON DELETE CASCADE,
  grade numeric NOT NULL,
  CONSTRAINT class_student_pkey PRIMARY KEY (class_id, student_id)
);
