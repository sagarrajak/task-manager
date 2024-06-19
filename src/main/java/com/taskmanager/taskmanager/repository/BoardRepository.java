package com.taskmanager.taskmanager.repository;

import com.taskmanager.taskmanager.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    @Query("""
            select case when (count(be) > 0) then true
                   else false
                end
            from  BoardEntity be
            where be.boardUniqueId ilike :uniqueBoardName
            """)
    boolean isThereBoardWithSameName(String uniqueBoardName);
}
