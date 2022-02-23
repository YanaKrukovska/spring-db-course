INSERT INTO author(id, full_name)
VALUES (1, 'Franz Kafka'),
       (2, 'Ernest Hemingway'),
       (3, 'Daniel Keyes'),
       (4, 'Erich Maria Remarque'),
       (5, 'F. Scott Fitzgerald'),
       (6, 'Bob Novella'),
       (7, 'Jay Novella'),
       (8, 'Gaston Leroux'),
       (9, 'Hugh Laurie');

INSERT INTO genre(id, naming)
VALUES (1, 'Drama'),
       (2, 'Science Fiction'),
       (3, 'War'),
       (4, 'Absurdist'),
       (5, 'Realism'),
       (6, 'Comedy');

INSERT INTO book(id, title, description, page_amount, cover_type, price, publisher)
VALUES (1, 'Amerika', 'His first novel', 336, 'PAPER', 85, 'Publisher 1'),
       (2, 'The Great Gatsby', 'Worlds most famous novel', 200, 'HARD', 250, 'Publisher 2'),
       (3, 'The Skeptics'' Guide to the Universe', 'An all-encompassing guide to skeptical thinking', 448, 'HARD', 348, 'Publisher 1'),
       (4, 'Flowers for Algernon', 'The saddest book ever', 216, 'PAPER', 110, 'Publisher 1'),
       (5, 'All Quiet on the Western Front', 'The most famous anti war novel', 296, 'HARD', 250, 'Publisher 4'),
       (6, 'The Minds of Billy Milligan', 'Re-released in honour of new netflix show', 374, 'PAPER', 100, 'Publisher 14'),
       (7, 'The Night in Lisbon', 'Authors last finished novel', 272, 'HARD', 125, 'Publisher 4'),
       (8, 'A Farewell to Arms', 'Another classic by Hemingway', 293, 'HARD', 250, 'Publisher 2'),
       (9, 'The Love of the Last Tycoon', 'Authors last novel', 169, 'PAPER', 95, 'Publisher 3'),
       (10, 'The Metamorphosis', 'Most famous novel by Kafka', 200, 'HARD', 125, 'Publisher 5');

INSERT INTO book_authors(book_id, author_id)
VALUES (1, 1),
       (2, 5),
       (3, 6),
       (3, 7),
       (4, 3),
       (5, 4),
       (6, 3),
       (7, 4),
       (8, 2),
       (9, 5),
       (10, 1);

INSERT INTO book_genres(book_id, genre_id)
VALUES (1, 4),
       (2, 1),
       (3, 2),
       (4, 1),
       (4, 2),
       (5, 3),
       (5, 5),
       (6, 2),
       (7, 1),
       (7, 3),
       (8, 3),
       (8, 5),
       (9, 1),
       (10, 4);
