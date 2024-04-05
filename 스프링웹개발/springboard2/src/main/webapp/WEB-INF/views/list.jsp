
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<jsp:include page="template/header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.css">

        <%--container --%>
        <div class="container-fluid">
            <%-- 제목 --%>
            <div class="row mt-4">
                <div class="col-md-10 offset-md-1"></div>
                    <h1>자유 게시판</h1>
                    <p>
                        타인에 대한 비방 또는 모욕은 사전 예고 없이 삭제됩니다.
                    </p>
            </div>
            <%-- 게시글 개수 및 글쓰기 버튼 --%>
                <div class="row mt-4">
                    <div class="col-md-10 offset-md-1">
                        <div class="row">
                            <div class="col text-start">
                                (1/275)
                            </div>
                            <div class="col text-end">
                                <a href="write" class="btn btn-primary">글쓰기</a>
                            </div>
                        </div>
                    </div>
                </div>
            <%-- 게시글 --%>
            <div class="row mt-4">
                <div class="col-md-10 offset-md-1">
                    <table class="table table-hover">
                        <thead class="bg-dark text-light text-center">
                            <tr>
                                <th>번호</th>
                                <th width="40%">제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody class="text-center">
                        <c:forEach var="board" items="${list}">
                            <tr>
                                <td>${board.no}</td>
                                <td class="text-start">
                                    <a href="detail?no=${board.no}">
                                            ${board.title}
                                    </a>
                                </td>
                                <td>${board.writer}</td>
                                <td>${board.writeTime}</td>
                                <td>${board.readcount}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <%-- 페이지 네비게이터--%>
            <div class="row mt-4">
                <div class="col-md-10 offset-md-1">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                        <li class="page-item"><a class="page-link" href="#">&lt;</a></li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">4</a></li>
                        <li class="page-item"><a class="page-link" href="#">5</a></li>
                        <li class="page-item"><a class="page-link" href="#">6</a></li>
                        <li class="page-item"><a class="page-link" href="#">7</a></li>
                        <li class="page-item"><a class="page-link" href="#">8</a></li>
                        <li class="page-item"><a class="page-link" href="#">9</a></li>
                        <li class="page-item"><a class="page-link" href="#">10</a></li>
                        <li class="page-item"><a class="page-link" href="#">&gt;</a></li>
                        <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>

            <%-- 검색창 --%>
            <div class="row mt-4">
                <div class="col-md-10 offset-md-1"></div>
                    <form class="form" method="get">
                        <div class="row">
                            <div class="col-2 offset-2">
                                <select class="form-select">
                                    <option>제목</option>
                                    <option>작성자</option>
                                </select>
                            </div>

                            <div class="col-4">
                                <input type="search" class="form-control" placeholder="검색어"required>
                            </div>

                            <div class="col-2">
                                <button class="btn btn-success w-100" type="submit">검색</button>

                            </div>
                        </div>
                    </form>
            </div>
        </div>

<jsp:include page="template/footer.jsp"></jsp:include>

