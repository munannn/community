CREATE TABLE `invitation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inviter` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邀请人',
  `invitee` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '被邀请人',
  `role` int DEFAULT NULL COMMENT '被邀请人角色',
  `invite_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '邀请码，随机生成',
  `is_use` int DEFAULT NULL COMMENT '邀请码是否使用，0:未使用，1:已使用；一个邀请码只能邀请一名用户，已使用的邀请码失效',
  `invite_time` datetime DEFAULT NULL COMMENT '邀请时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci