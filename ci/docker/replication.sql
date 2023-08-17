-- on node 1
SET @@GLOBAL.group_replication_bootstrap_group=1;
create user 'repl'@'%';
GRANT REPLICATION SLAVE ON *.* TO repl@'%';
flush privileges;
change master to master_user='root' for channel 'group_replication_recovery';
START GROUP_REPLICATION;
SET @@GLOBAL.group_replication_bootstrap_group=0;
SELECT * FROM performance_schema.replication_group_members;

-- on secondary
change master to master_user='repl' for channel 'group_replication_recovery';
START GROUP_REPLICATION;

USE mysql;
CREATE USER 'app-user'@'%' IDENTIFIED BY 'Password$4';
GRANT ALL ON *.* TO 'app-user'@'%';
FLUSH PRIVILEGES;

CREATE SCHEMA `sns_test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

mysql -uroot -p