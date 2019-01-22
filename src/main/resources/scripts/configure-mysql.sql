## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE recipeapp_dev;
CREATE DATABASE recipeapp_prod;

#Create database service accounts
CREATE USER 'dev_user'@'localhost' IDENTIFIED BY 'password_dev';
CREATE USER 'prod_user'@'localhost' IDENTIFIED BY 'password_prod';
CREATE USER 'dev_user'@'%' IDENTIFIED BY 'password_dev';
CREATE USER 'prod_user'@'%' IDENTIFIED BY 'password_prod';

#Database grants
GRANT SELECT ON recipeapp_dev.* to 'dev_user'@'localhost';
GRANT INSERT ON recipeapp_dev.* to 'dev_user'@'localhost';
GRANT DELETE ON recipeapp_dev.* to 'dev_user'@'localhost';
GRANT UPDATE ON recipeapp_dev.* to 'dev_user'@'localhost';

GRANT SELECT ON recipeapp_prod.* to 'prod_user'@'localhost';
GRANT INSERT ON recipeapp_prod.* to 'prod_user'@'localhost';
GRANT DELETE ON recipeapp_prod.* to 'prod_user'@'localhost';
GRANT UPDATE ON recipeapp_prod.* to 'prod_user'@'localhost';

GRANT SELECT ON recipeapp_dev.* to 'dev_user'@'%';
GRANT INSERT ON recipeapp_dev.* to 'dev_user'@'%';
GRANT DELETE ON recipeapp_dev.* to 'dev_user'@'%';
GRANT UPDATE ON recipeapp_dev.* to 'dev_user'@'%';

GRANT SELECT ON recipeapp_prod.* to 'prod_user'@'%';
GRANT INSERT ON recipeapp_prod.* to 'prod_user'@'%';
GRANT DELETE ON recipeapp_prod.* to 'prod_user'@'%';
GRANT UPDATE ON recipeapp_prod.* to 'prod_user'@'%';