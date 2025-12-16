CREATE TABLE tb_movie_category
(
    movie_id    INTEGER,
    category_id INTEGER,
    CONSTRAINT fk_tb_movie_category_tb_movie FOREIGN KEY (movie_id) REFERENCES tb_movie (id),
    CONSTRAINT fk_tb_movie_category_tb_category FOREIGN KEY (category_id) REFERENCES tb_category (id)
);