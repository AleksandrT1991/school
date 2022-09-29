-- liquibase formatted sql

-- changeset a.tsygulev:1
CREATE INDEX student_name_index ON student (name);
-- changeset a.tsygulev:2
CREATE INDEX faculty_name_index ON faculty (name);
-- changeset a.tsygulev:3
CREATE INDEX faculty_color_index ON faculty (color);
