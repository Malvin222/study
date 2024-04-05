package com.malvin.springboard1.repository;

import com.malvin.springboard1.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {

    //목록 최신순(번호 내림차순)
    List<Board> findAllByOrderByNoDesc();
}
