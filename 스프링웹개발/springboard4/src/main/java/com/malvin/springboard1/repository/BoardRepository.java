package com.malvin.springboard1.repository;

import com.malvin.springboard1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long>, JpaSpecificationExecutor<Board> {

    //등록 가능한 seq 값을 찾는 명령(JPQL)
    //- 동일 그룹, 하단 게시글 중 차수가 같거나 작은 글 중 가장 작은 seq를 구한다
    //- 없을 경우 null이므로 반드시 Long 형태로 처리(혹은 nvl 처리가 필요)
    @Query("select min(b.seq) from Board b where b.grp = :#{#origin.grp} and b.seq > :#{#origin.seq} and b.dep <= :#{#origin.dep}")
    Long getAvailableSeq(Board origin);

    //naming query이므로 JPQL 작성 불필요
    long countByGrp(Long grp);

    @Modifying
    @Transactional
    @Query("update Board b set b.seq = b.seq + 1 where b.grp = :#{#board.grp} and b.seq >= :#{#board.seq}")
    int increaseSequence(Board board);

    @Modifying
    @Transactional
    @Query("update Board b set b.seq = b.seq - 1 where b.grp = :#{#board.grp} and b.seq >= :#{#board.seq}")
    int decreaseSequence(Board board);
}
