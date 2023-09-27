//formatter:off
INSERT INTO category (color, is_default, name) VALUES ('BLACK_01', 'DEFAULT', '기타'), ('YELLOW_01', 'DEFAULT', '식사'), ('BROWN_01', 'DEFAULT', '카페'), ('ORANGE_01', 'DEFAULT', '군것질'), ('RED_01', 'DEFAULT', '패션/뷰티'), ('GREEN_01', 'DEFAULT', '생활'), ('BLUE_01', 'DEFAULT', '건강'), ('PINK_01', 'DEFAULT', '유흥'), ('BLACK_02', 'DEFAULT', '교통'), ('PURPLE_01', 'DEFAULT', '여가');
INSERT INTO member (member_id, email, member_name, oauth_server_type, password) VALUES (X'24ce4681c966419fb739811ebb6f1872', 'superuser@haruman.site', '하루만', 'DEFAULT', 'haruman607');
INSERT INTO profile (nickname, member_id) VALUES ('남고니', X'24ce4681c966419fb739811ebb6f1872');
