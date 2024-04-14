INSERT INTO BOOK (AUTHOR , UPLOADER, NAME , DESCRIPTION, PRICE, STATE) VALUES ('Daniel Errico', 'mary', 'THE GUARDIANS OF LORE', 'The Guardians of Lore that is centers around two life-long friends, infusing humor and fantasy-based riddles into a modern fairytale.
This book is so funny and imbues curiosity in young readers to discover the unknown with a spirit of adventure.', 300, 'Yes');

INSERT INTO users VALUES ('keith', '{noop}keithpw', '', '', '');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_USER');
INSERT INTO user_roles(username, role) VALUES ('keith', 'ROLE_ADMIN');
INSERT INTO users VALUES ('may', '{noop}maypw', '', '', '');
INSERT INTO user_roles(username, role) VALUES ('may', 'ROLE_ADMIN');
INSERT INTO users VALUES ('chan', '{noop}chanpw', '', '', '');
INSERT INTO user_roles(username, role) VALUES ('chan', 'ROLE_USER');
INSERT INTO users VALUES ('anyone', '{noop}anyonepw', '', '', '');
INSERT INTO user_roles(username, role) VALUES ('anyone', 'ROLE_UNREG');
