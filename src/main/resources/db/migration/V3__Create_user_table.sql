CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户账号，为QQ邮箱',
    `password` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户密码',
    `role` int DEFAULT NULL COMMENT '角色，0:超级管理员，1:管理员，2:普通用户',
    `token` char(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户登录令牌，使用UUID生成',
    `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户创建者(邀请者)',
    `create_time` datetime DEFAULT NULL COMMENT '用户创建时间',
    `modified_time` datetime DEFAULT NULL COMMENT '用户修改时间',
    `is_deleted` int DEFAULT NULL COMMENT '用户逻辑删除，0:账号可用，1:逻辑删除账号不可用',
    `deleted_time` datetime DEFAULT NULL COMMENT '用户账号逻辑删除时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci