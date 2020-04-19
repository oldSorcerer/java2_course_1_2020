package org.levelup.application.dao;

import org.levelup.application.domain.PositionEntity;

public interface PositionDao {

    PositionEntity createPosition(String name);
}
