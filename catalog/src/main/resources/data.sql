DROP TABLE IF EXISTS  Products;
CREATE TABLE Products AS SELECT * FROM CSVREAD('jcpenney_com-ecommerce_sample.csv');
