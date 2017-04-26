INSERT INTO CATEGORIES (NAME) VALUES ('Presentation');
INSERT INTO CATEGORIES (NAME) VALUES ('Email');
INSERT INTO CATEGORIES (NAME) VALUES ('Coffee');
INSERT INTO CATEGORIES (NAME) VALUES ('Briefing');
INSERT INTO CATEGORIES (NAME) VALUES ('Workshop');

INSERT INTO ENDORSEMENTS ("UUID", GRANTER_UUID, RECEIVER_UUID, SCORE, REASON, URL) VALUES ('a2a48938-0e5a-402e-a7ac-0c9bb35c37cc', RANDOM_UUID(), RANDOM_UUID(), 1, 'De presentatie was super duidelijk', 'https://www.ordina.be/');
INSERT INTO ENDORSEMENTS ("UUID", GRANTER_UUID, RECEIVER_UUID, SCORE, REASON, URL) VALUES ('5a97c460-f96d-4b7b-89a6-a824cbeedaea', RANDOM_UUID(), RANDOM_UUID(), 1, 'Bedankt voor de goede koffie', 'https://www.google.be/');
INSERT INTO ENDORSEMENTS ("UUID", GRANTER_UUID, RECEIVER_UUID, SCORE, REASON, URL) VALUES ('b630fb1c-3e47-4cee-bf40-af906ced41c4', RANDOM_UUID(), RANDOM_UUID(), 1, 'Alles rond de workshop was geweldig, veel bijgeleerd!', 'https://www.facebook.com/');
INSERT INTO ENDORSEMENTS ("UUID", GRANTER_UUID, RECEIVER_UUID, SCORE, REASON, URL) VALUES ('2405edf4-4393-4c03-acfb-8bd84d8f0b82', RANDOM_UUID(), RANDOM_UUID(), 1, 'Heel gestructureerde email', 'https://www.ordina.be/');

INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('a2a48938-0e5a-402e-a7ac-0c9bb35c37cc', 1);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('a2a48938-0e5a-402e-a7ac-0c9bb35c37cc', 4);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('5a97c460-f96d-4b7b-89a6-a824cbeedaea', 3);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('b630fb1c-3e47-4cee-bf40-af906ced41c4', 2);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('b630fb1c-3e47-4cee-bf40-af906ced41c4', 4);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('b630fb1c-3e47-4cee-bf40-af906ced41c4', 5);
INSERT INTO CATEGORY_ASSIGNMENTS (ENDORSEMENT_UUID, CATEGORY_ID) VALUES ('2405edf4-4393-4c03-acfb-8bd84d8f0b82', 2);

INSERT INTO ENDORSEMENT_LIKES ("UUID", GRANTER_UUID, ENDORSEMENT_UUID, REASON) VALUES (RANDOM_UUID(), RANDOM_UUID(), 'b630fb1c-3e47-4cee-bf40-af906ced41c4', 'Cuz i liek diz');
INSERT INTO ENDORSEMENT_LIKES ("UUID", GRANTER_UUID, ENDORSEMENT_UUID, REASON) VALUES (RANDOM_UUID(), RANDOM_UUID(), 'b630fb1c-3e47-4cee-bf40-af906ced41c4', 'Cuz i liek diz too');
INSERT INTO ENDORSEMENT_LIKES ("UUID", GRANTER_UUID, ENDORSEMENT_UUID, REASON) VALUES (RANDOM_UUID(), RANDOM_UUID(), 'b630fb1c-3e47-4cee-bf40-af906ced41c4', 'Cuz i liek diz three');
INSERT INTO ENDORSEMENT_LIKES ("UUID", GRANTER_UUID, ENDORSEMENT_UUID, REASON) VALUES (RANDOM_UUID(), RANDOM_UUID(), 'b630fb1c-3e47-4cee-bf40-af906ced41c4', 'Cuz i liek diz four');
INSERT INTO ENDORSEMENT_LIKES ("UUID", GRANTER_UUID, ENDORSEMENT_UUID, REASON) VALUES (RANDOM_UUID(), RANDOM_UUID(), 'a2a48938-0e5a-402e-a7ac-0c9bb35c37cc', 'Amuzing');