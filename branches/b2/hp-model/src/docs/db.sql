DROP TABLE IF EXISTS `hp`.`article`;
CREATE TABLE  `hp`.`article` (
  `ar_id` int(10) unsigned NOT NULL default '0',
  `us_id` int(10) unsigned NOT NULL default '0',
  `ar_title` varchar(255) NOT NULL default '',
  `ar_text` text NOT NULL,
  `ar_type` varchar(45) character set utf8 collate utf8_bin NOT NULL default '',
  PRIMARY KEY  (`ar_id`),
  KEY `FKFF2458D68B968077` (`us_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `hp`.`user`;
CREATE TABLE  `hp`.`user` (
  `us_id` int(11) NOT NULL,
  `us_login` varchar(30) NOT NULL default '',
  `us_name` varchar(30) NOT NULL default '',
  `us_pass` varchar(100) NOT NULL default '',
  `us_enabled` tinyint(1) default NULL,
  PRIMARY KEY  (`us_id`),
  UNIQUE KEY `us_login` (`us_login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `hp`.`authority`;
CREATE TABLE  `hp`.`authority` (
  `us_id` int(10) unsigned default NULL,
  `au_authority` text
) ENGINE=InnoDB DEFAULT CHARSET=cp1251;