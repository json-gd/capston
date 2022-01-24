DROP TABLE IF EXISTS  Products;
CREATE TABLE Products AS SELECT uniq_id as product_id, RAND() > 0.5 as is_available FROM CSVREAD('../catalog/jcpenney_com-ecommerce_sample.csv');
