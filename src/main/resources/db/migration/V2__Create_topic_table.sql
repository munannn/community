CREATE TABLE `topic` (
     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
     `title` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帖子标题',
     `content` text COLLATE utf8mb4_general_ci COMMENT '帖子内容',
     `publish_by` int DEFAULT NULL COMMENT '发帖人id',
     `create_time` datetime DEFAULT NULL COMMENT '发帖时间',
     `modified_time` datetime DEFAULT NULL COMMENT '上一次修改时间',
     `view_count` int NOT NULL DEFAULT '0' COMMENT '帖子浏览数',
     `comment_count` int NOT NULL DEFAULT '0' COMMENT '帖子评论数',
     `like_count` int NOT NULL DEFAULT '0' COMMENT '帖子点赞数',
     `tag` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '帖子标签',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci