INSERT INTO USER (username, firstname, password, role, email) VALUES
('user', 'Mike the User', '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER', 'mike@user.com'),
('admin', 'Minnie the Admin', '$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C', 'ADMIN', 'minnie@admin.com');

INSERT INTO MESSAGE (encode, message, encrypted_msg, visibility, id) VALUES
('Caesar Cipher, shift: 1', 'monkey', 'npolfz', true, (SELECT id FROM user where id=1)),
('Caesar Cipher, shift: 1', 'pig', 'qjh', false, (SELECT id FROM user where id=1)),
('Caesar Cipher, shift: 1', 'bunny', 'cvooz', true, (SELECT id FROM user where id=2)),
('Morse Code', 'food', '..-. --- --- -..', true, (SELECT id FROM user where id=2)),
('Morse Code', 'bunny', '-... ..- -. -. -.--', true, (SELECT id FROM user where id=2));


