ALTER TABLE student
  ADD CHECK (age > 16),
  ALTER COLUMN name SET NOT NULL,
  ALTER COLUMN age SET DEFAULT 20;

ALTER TABLE faculty
    ADD CONSTRAINT unique_color_name UNIQUE (color,name);
