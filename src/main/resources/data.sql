INSERT INTO role VALUES (1, 'ROLE_USER_BUYER'), (2, 'ROLE_USER_RENT'), (3, 'ROLE_ADMIN');
INSERT INTO t_user
    VALUES (0, '$2y$12$14TkBuwcTvrgaFiQRag51.23sdBK386EhWPeqAZJV6FCEr9JhwzKu', 'first_admin'),
       (1, '$2y$12$J8cRlY4GP24SG96F5icQOemuP1LJ2JYOEsTNa.FX6hr0QQG578TX6', 'first_UB'),
       (2, '$2y$12$LPyOCEl4QbFE/pphnjEw4.goVJv3mYqExsNP10Lz95RUhsMw900jy', 'first_UR');
INSERT INTO t_user_roles (user_id_user, roles_id)
    VALUES (0, 3),
           (1, 1),
           (2, 2);
INSERT INTO model (id_model, model_name, year_of_issue)
VALUES (1, 's', 1);