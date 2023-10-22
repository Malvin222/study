package org.zerock.dao;

import com.sun.tools.javac.comp.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.zerock.jdbcex.dao.TodoDAO;
import org.zerock.jdbcex.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;

public class TodoDAOTest {

    private TodoDAO todoDAO;

    //ready() -> 모든 테스트전 todoDAO 타입의 객체 생성
    @BeforeEach
    public  void  ready(){
        todoDAO = new TodoDAO();
    }

    //testTime() ->TodoDAO에 작성한 getTime()정상 동작 확인
    @Test
    public  void testTime() throws  Exception{

        System.out.println(todoDAO.getTime() );
    }

    @Test
    public void testInsert() throws Exception{
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .build();   //생성자와 달리 필요한 만큼만 데이터 세팅

        todoDAO.insert(todoVO);
    }

    @Test
    public void testList() throws Exception{

        List<TodoVO> list = todoDAO.selectAll();
        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public  void testSelectOne() throws Exception{

        Long tno = 1L; //반드시 존재하는 번호 이용

        TodoVO vo = todoDAO.selectOne(tno);

        System.out.println(vo);
    }

    @Test
    public void testDeleteOne() throws  Exception{

        Long tno = 5L;

        TodoVO todoVO = TodoVO.builder()
                .tno(5L)
                .build();

        todoDAO.deleteOne(tno);
    }

    @Test
    public void testUpdateOne() throws  Exception{
        TodoVO todoVO = TodoVO.builder()
                .tno(1L)
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        todoDAO.updateOne(todoVO);
    }
}
