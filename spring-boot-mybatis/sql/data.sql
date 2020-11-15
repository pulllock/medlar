use mybatis_test;
INSERT INTO mt_api(id, created_time, modified_time, version, code, name, method, alias, sys_id, timeout) VALUES (1, now(), now(), 1, '1TRW556GGH#TEST', 'me.cxis.mybatis.sample.service.OrderService', 'queryOrders', NULL, 1, 1000);
INSERT INTO mt_api(id, created_time, modified_time, version, code, name, method, alias, sys_id, timeout) VALUES (2, now(), now(), 1, 'SHHHFJFJJF#TEST', 'me.cxis.mybatis.sample.service.OrderService', 'createOrder', NULL, 1, 1000);
INSERT INTO mt_api(id, created_time, modified_time, version, code, name, method, alias, sys_id, timeout) VALUES (3, now(), now(), 1, 'JJUUHHHH#TEST', 'me.cxis.mybatis.sample.service.OrderService', 'confirm', NULL, 1, 1000);
INSERT INTO mt_api_param(id, created_time, modified_time, version, api_id, name, type, `sequence`) VALUES (1, now(), now(), 1, 1, 'query', 'me.cxis.mybatis.sample.query.OrderQuery', 1);
