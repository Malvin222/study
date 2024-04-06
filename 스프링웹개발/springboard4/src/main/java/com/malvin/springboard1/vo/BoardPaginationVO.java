package com.malvin.springboard1.vo;

import com.malvin.springboard1.entity.Board;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

//하단 pagination을 위한 vo
@Getter
@ToString
public class BoardPaginationVO {
    private int current; // 현재 페이지 번호
    private int begin; // 시작 페이지 번호
    private int end; //종료 페이지 번호
    private int total; // 전체 페이지 번호
    private int size = 10; //보여줄 페이지 번호

    public BoardPaginationVO(Page<Board> page) {
        this.total = page.getTotalPages();
        this.current = page.getNumber() +1; //jpa는 페이지가 0부터 시작
        this.begin = (current-1) /size * size +1;
        /*this.end = (current-1) /size * size +size;*/
        this.end = this.begin + size -1;
    }

    //처음(first),이전(previous),다음(next),(last)에 대한 정보
    public boolean hasFirstBlock(){
        return current > 1;
    }
    public boolean hasPreviousBlock(){
        return begin > 1;
    }
    public boolean hasNextBlock(){
        return end < total;
    }
    public boolean hasLastBlock(){
        return current < total;
    }
    public int getFirst(){
        return 1-1;
    }
    public int getLast(){
        return total -1;
    }
    public int getPrevious(){
        return begin - 1 -1;
    }
    public int getNext(){
        return end + 1 -1;
    }
    //JPA는 0부터 시작하므로 -1 해주어야함
}
