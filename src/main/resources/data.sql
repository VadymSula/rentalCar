INSERT INTO role VALUES (1, 'ROLE_USER_BUYER'), (2, 'ROLE_USER_RENT'), (3, 'ROLE_ADMIN');
INSERT INTO t_user
VALUES (0, '$2y$12$14TkBuwcTvrgaFiQRag51.23sdBK386EhWPeqAZJV6FCEr9JhwzKu', 'first_admin');
INSERT INTO t_user_roles (user_id_user, roles_id) VALUES (0, 2);