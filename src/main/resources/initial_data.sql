-- insert user
insert into user(id, full_name, email, password) values (1, "admin", "admin@email.com", "admin")
insert into user(id, full_name, email, password) values (2, "user", "user@email.com", "user")

-- insert topic
insert into topic(id, name, description, owner_id) values (1, "phep cong lop 1", "this topic is used to test", 1)
insert into topic(id, name, description, owner_id) values (2, "phep nhan lop 1", "this topic is used to test", 1)

-- insert exam
insert into exam(id, name, time, topic_id, owner_id) values (1, "kiem tra 15 phut - lop 1 - hoc ki 1 - 2022", 15, 1, 1)

-- insert question
insert into question(id, content, topic_id) values (1, "1 + 1 = ?", 1)
insert into question(id, content, topic_id) values (2, "3 + 1 = ?", 1)
insert into question(id, content, topic_id) values (3, "4 + 2 = ?", 1)

-- insert answer
insert into answer(id, content, right_answer, question_id) values (1, "2", true, 1)
insert into answer(id, content, right_answer, question_id) values (2, "3", false, 1)
insert into answer(id, content, right_answer, question_id) values (3, "4", false, 1)
insert into answer(id, content, right_answer, question_id) values (4, "5", false, 1)

insert into answer(id, content, right_answer, question_id) values (5, "2", false, 2)
insert into answer(id, content, right_answer, question_id) values (6, "3", false, 2)
insert into answer(id, content, right_answer, question_id) values (7, "4", true, 2)
insert into answer(id, content, right_answer, question_id) values (8, "5", false, 2)

insert into answer(id, content, right_answer, question_id) values (9, "5", false, 3)
insert into answer(id, content, right_answer, question_id) values (10, "6", true, 3)
insert into answer(id, content, right_answer, question_id) values (11, "7", false, 3)
insert into answer(id, content, right_answer, question_id) values (12, "8", false, 3)

-- insert exam_question
insert into exam_question(exam_id, question_id) values (1, 1)
insert into exam_question(exam_id, question_id) values (1, 2)
insert into exam_question(exam_id, question_id) values (1, 3)