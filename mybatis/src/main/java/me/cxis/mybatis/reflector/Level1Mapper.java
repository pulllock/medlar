package me.cxis.mybatis.reflector;

import java.util.List;

public interface Level1Mapper<L, M, N> {

    List<User<L>> pageQueryUser();
}
