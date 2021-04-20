DROP TRIGGER IF EXISTS `audit_name_add`; DELIMITER ;;

CREATE TRIGGER `audit_name_add` AFTER INSERT
ON `tbl_name` FOR EACH ROW -- 在tbl_name插入后在tbl_name_log记录日志的触发器 

BEGIN

INSERT INTO `tbl_name_log` (`action`,`name`,`time`) VALUES ('add',new.`name`,NOW()); 

END ;; DELIMITER ;

DROP TRIGGER IF EXISTS `audit_name_update`; DELIMITER ;;
CREATE TRIGGER `audit_name_update` AFTER UPDATE
ON `tbl_name` FOR EACH ROW 
#在tbl_name更新后在tbl_name_log记录日志的触发器 BEGIN

INSERT INTO `tbl_name_log` (`action`,`name`,`time`) VALUES ('update',new.`name`,NOW()); END ;; DELIMITER ;

DROP TRIGGER IF EXISTS `audit_name_del`; DELIMITER ;;
CREATE TRIGGER `audit_name_del` AFTER DELETE
ON `tbl_name` FOR EACH ROW #在tbl_name删除后在tbl_name_log记录日志的触发器 BEGIN

INSERT INTO `tbl_name_log` (`action`,`name`,`time`) VALUES ('del',old.`name`,NOW()); END ;; DELIMITER ;