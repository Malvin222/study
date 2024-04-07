package com.malvin.springboard1.service;

import com.malvin.springboard1.entity.Board;
import com.malvin.springboard1.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board write(Board board) {
        //새글일 경우와 답글일 경우를 구분하여 처리(board.no가 존재할 경우 답글)
        //- 새글일 경우 - 등록 후 grp를 번호와 동일하게 갱신
        //- 답글일 경우 - 원본글 정보를 이용하여 grp, seq, dep를 계산
        boolean isReply = board.getNo() != null;

        if(isReply) {//답글일 경우 - grp, seq, dep 계산
            Board origin = boardRepository.findById(board.getNo()).orElseThrow();

            Long seq = boardRepository.getAvailableSeq(origin);
            if(seq == null) {//위치를 못찾은 경우 해당 그룹의 마지막에 추가해야 하므로 카운트를 구한다.
                seq = boardRepository.countByGrp(origin.getGrp());
            }
            else {//찾은 경우 해당 위치 이상의 값들을 증가 처리(grp, seq 필요)
                boardRepository.increaseSequence(Board.builder().grp(origin.getGrp()).seq(seq).build());
            }

            //no, grp, seq, dep 변경
            board.setNo(null);//번호 초기화(시퀀스)
            board.setGrp(origin.getGrp());//그룹 유지
            board.setSeq(seq);//계산된 seq
            board.setDep(origin.getDep()+1);//차수 증가
        }

        Board result = boardRepository.save(board);

        if(!isReply) {//새글일 경우 - grp 갱신
            result.setGrp(result.getNo());//no와 grp를 동일하게 처리
            boardRepository.save(result);
        }

        return result;
    }

}