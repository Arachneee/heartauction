INSERT INTO member (name)
VALUES ('회원1'),
       ('회원2'),
       ('회원3'),
       ('회원4'),
       ('회원5')
;
INSERT INTO donation (title, description, member_id)
VALUES ( '기부 물품1', '기부 물품 설명1', 1 ),
       ( '기부 물품2', '기부 물품 설명2', 2 ),
       ( '기부 물품3', '기부 물품 설명3', 1 ),
       ( '기부 물품4', '기부 물품 설명4', 1 ),
       ( '기부 물품5', '기부 물품 설명5', 1 )
;
INSERT INTO auction (donation_id, start_price, start_date_time, status)
VALUES ( 1, 1000, '2025-03-01', 'NOT_STARTED'),
       ( 2, 2000, '2025-03-02', 'NOT_STARTED'),
       ( 3, 3000, '2025-03-03', 'NOT_STARTED'),
       ( 4, 4000, '2025-03-04', 'NOT_STARTED'),
       ( 5, 5000, '2025-03-05', 'NOT_STARTED')
;
