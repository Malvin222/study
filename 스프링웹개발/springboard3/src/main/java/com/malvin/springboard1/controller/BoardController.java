package com.malvin.springboard1.controller;

import com.malvin.springboard1.entity.Board;
import com.malvin.springboard1.repository.BoardRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Map;

@Log4j2
@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String list(Model model){
//        model.addAttribute("list",boardRepository.findAll());
        model.addAttribute("list", boardRepository.findAllByOrderByNoDesc());
        log.info(boardRepository.findAll());

        return "list";
    }

    @GetMapping("/write")
    public String write(){
        return "write";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Board board){
        Board result = boardRepository.save(board);
/*        return "redirect:/";*/
        return "redirect:detail?no="+result.getNo();
    }

    @GetMapping("/detail")
    public String detail(@RequestParam long no, Model model){
        Board board = boardRepository.findById(no).orElseThrow();

        //조회수 증가
        board.setReadcount(board.getReadcount()+1);
        Board result = boardRepository.save(board);

        model.addAttribute("board", result);
        return "detail";
    }

    //비밀번호 로직이 추가되었으므로 단순하게 번호를 받는 것이 아닌 FlashMap을 수신하는 코드로 변경
    @GetMapping("/edit")
    public String edit(HttpServletRequest requset, Model model){
        //FlashMap 수신 코드
        Map<String,?> map = RequestContextUtils.getInputFlashMap(requset);
        if(map == null) throw new RuntimeException("권한 없음");
        Long no = (Long)map.get("no");

        Board board = boardRepository.findById(no).orElseThrow();
        model.addAttribute("board",board);
        return "edit";
    }

/*    @GetMapping("/edit")
    public String edit(@RequestParam long no,Model model){
        Board board = boardRepository.findById(no).orElseThrow();
        model.addAttribute("board",board);
        return "edit";
    }*/

    @PostMapping("/edit")
    public String edit(@ModelAttribute Board board, RedirectAttributes attr){
        Board origin = boardRepository.findById(board.getNo()).orElseThrow();
        origin.setTitle(board.getTitle());
        origin.setWriter(board.getWriter());
        origin.setContent(board.getContent());

        //비밀번호 추가
        origin.setPassword(board.getPassword());
        Board result = boardRepository.save(origin);
        attr.addAttribute("no",result.getNo());
        return "redirect:detail";
    }
    //비밀번호 로직이 추가되었으므로 단순하게 번호를 받는 것이 아닌 FlashMap을 수신하는 코드로 변경
    @GetMapping("delete")
    public String delete(HttpServletRequest requset){
        //FlashMap 수신 코드
        Map<String,?> map = RequestContextUtils.getInputFlashMap(requset);
        if(map == null) throw new RuntimeException("권한 없음");
        Long no = (Long)map.get("no");
        boardRepository.deleteById(no);
        return "redirect:/";
    }

/*    @GetMapping("delete")
    public String delete(@RequestParam long no){
        boardRepository.deleteById(no);
        return "redirect:/";
    }*/

    //비밀번호 검사 맵핑 추가
    // - / paasword/모드/번호
    // -  모드는 반드시 edit 또는 delete 중 하나가 오도록 정규식 검사 처리
    @GetMapping("/password/{mode:edit|delete}/{no}")
    public String password(@PathVariable String mode, @PathVariable long no, Model model){
        model.addAttribute("mode",mode);
        model.addAttribute("no",no);
        return "password";

    }

    @PostMapping("/password/{mode:edit|delete}/{no}")
    public String password(@PathVariable String mode, @PathVariable long no, @RequestParam String password,
                           RedirectAttributes attr){
        //글번호가 없으면 오류 처리
        Board board = boardRepository.findById(no).orElseThrow();

        if(board.getPassword().equals(password)){ //비밀번호 일치 - 다음 단계로 이동
            //이동은 하지만 번호를 알 수가 없으며, 이 페이지를 통과했다는 보증이 필요하므로 FlashAttribute를 사용
            //FlashAttribute는 세션을 사용하며 요청후  소멸하므로 현재 상태에서는 유용하게 사용할 수 있는 값
            //-> 하지만 edit와 delete의 로직을 변경해야함
            attr.addFlashAttribute("no",no);
            return"redirect:/"+mode;
        }else {//비밀번호 불일치 - 이전 단계로 복귀(+error 표식)
            return "redirect:/password/"+mode+"/"+no+"?error";

        }
    }
}
