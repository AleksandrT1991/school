SELECT student.name, student.age, f.name
FROM student
LEFT JOIN faculty f on student.id = f.student_id;

SELECT student.name, student.age
FROM student
         INNER JOIN avatar a on student.id = a.student_id;