package com.malvin.springboard1.vo;


import com.malvin.springboard1.entity.Board;
import com.malvin.springboard1.util.EmptyChecker;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

//검색 처리를 위한 VO
@Data
public class BoardVO {
    private Long no;
    private String writer, title,content, begin, end;

    //검색 여부 판정
    public boolean isSearch(){
/*
        return (no !=null && no > 0L) || (writer !=null && !writer.isEmpty()) ||
                (title !=null && !title.isEmpty()) || (content !=null && !content.isEmpty()) ||
                (begin !=null && !begin.isEmpty()) || (end !=null && !end.isEmpty());
*/
        return !EmptyChecker.isEmpty(no, writer,title,content,begin,end);
    }
    // 시간 객체 반환
    public Timestamp beginTime(){
        return Timestamp.valueOf(begin+" "+"00:00:00.0");
    }
    public Timestamp endTime(){
        return Timestamp.valueOf(end+" "+"23:59:59.99");
    }


    //JPA Specification을 위한 specc 생성
    public Specification<Board> specification(){
        return (root, query, builder) -> {
            List<Predicate> predicateList =new ArrayList<>();

            //번호 조건 추가
            if(!EmptyChecker.isEmpty(this.no)){
                predicateList.add(builder.equal(root.get("no"), this.no));
            }

            //작성자 조건 추가
            if(!EmptyChecker.isEmpty(this.writer)){
                predicateList.add(builder.equal(root.get("writer"), this.writer));
            }

            //제목 조건 추가
            if(!EmptyChecker.isEmpty(this.title)){
                predicateList.add(builder.like(root.get("title"),"%"+ this.title + "%"));
            }

            //내용 조건 추가
            if(!EmptyChecker.isEmpty(this.content)){
                predicateList.add(builder.like(root.get("content"), "%"+this.content+ "%"));
            }

            //기간 조건 추가
            if(!EmptyChecker.isEmpty(this.begin)&&!EmptyChecker.isEmpty(this.end)){
                predicateList.add(builder.between(root.get("writeTime"),this.beginTime(),this.endTime()));
            }

            //end로 합성한 뒤 반환
            return builder.and(predicateList.stream().toArray(Predicate[]::new));
        };
    }
}
