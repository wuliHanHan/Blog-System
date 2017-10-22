insert into t_category
(categoryName, iconClass, aliasName, sort)
values
('假日生活', 1, 'Life', 1),
('两性话题', 2, 'Sex', 2);

insert into t_tag
(tagName)
values
('dota'),
('Java');

insert into t_friend
(siteName, siteUrl, siteDesc, sort)
values
('百度', 'www.baidu.com', 'heixin', 1);

insert into t_article
(categoryId, title, content, description, statue, author, createTime, showCount)
values
(12, '测试', '测试', '测试', 0, 'ziqiang', '2009-9-9 23:22:11', 10),
(13, '测试2', '测试2', '测试2', 0, 'ziqiang', '2010-9-9 23:22:11', 10);

insert into t_article_tag
(articleId, tagId)
values
(87, 23),
(88, 23);

insert into t_article_image
(imageUrl, articleId)
values
('https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png', 87);

insert into t_manager
(userName, password)
values
('zhao','650');