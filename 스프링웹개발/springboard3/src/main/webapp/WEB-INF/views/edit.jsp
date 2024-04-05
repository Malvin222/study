<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<jsp:include page="template/header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <h1>${board.no}번 게시글 수정</h1>
            <p class="text-secondary">글은 자신의 인격입니다</p>
        </div>
    </div>

    <form method="post">
    <input type="hidden" name="no" value="${board.no}">

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <div class="form-floating">
                <input name="writer" type="text" class="form-control" placeholder="작성자" value="${board.writer}" required>
                <label class="form-label text-secondary">작성자</label>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <div class="form-floating">
                <input name="title" type="text" class="form-control" placeholder="제목" value="${board.title}" required>
                <label class="form-label text-secondary">제목</label>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <textarea class="form-control" rows="15" name="content" required>${board.content}</textarea>
        </div>
    </div>

    <%--비밀번호를 재설정하도록 입력창 추가--%>
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <div class="form-floating">
                <input type="password" name="password" class="form-control" placeholder="비밀번호" required>
                <label class="form-label text-secondary">비밀번호</label>
            </div>
        </div>
    </div>

    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <a href="./" type="button" class="btn btn-secondary btn-lg">목록</a>
            <button type="submit" class="btn btn-danger btn-lg">수정</button>
        </div>
    </div>
    </form>
</div>

<jsp:include page="template/footer.jsp"></jsp:include>
