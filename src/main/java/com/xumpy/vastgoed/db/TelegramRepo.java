package com.xumpy.vastgoed.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramRepo extends CrudRepository<TelegramDBPojo, Integer> {
}
