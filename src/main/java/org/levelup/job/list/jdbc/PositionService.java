package org.levelup.job.list.jdbc;

import org.levelup.job.list.domain.Position;

public interface PositionService {

    public Position createPosition (String name);
    public Position deletePositionById (int id);
    public Position deletePositionByName(String name);
    public Position findAllPositionWhichNameLike(String name);
    public Position findPositionById(int id);
    public Position findAllPositions();
    public Position findPositionByName(String name);

}
