

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_demo
-- ----------------------------
DROP TABLE IF EXISTS `t_demo`;
CREATE TABLE `t_demo` (
  `id` varchar(64) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_demo
-- ----------------------------
INSERT INTO `t_demo` VALUES ('1', 'name1', '1');
INSERT INTO `t_demo` VALUES ('2', 'name2', '2');
