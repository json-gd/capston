DROP TABLE IF EXISTS  Products;
CREATE TABLE Products AS SELECT * FROM CSVREAD('/home/jakub/Dokumenty/capstone/catalog/jcpenney_com-ecommerce_sample.csv');
