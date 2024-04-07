<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<jsp:include page="template/header.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row mt-4">
        <div class="col-md-10 offset-md-1">
            <h1 class="text-danger text-center">삭제오류</h1>
        </div>
    </div>

    <div class="row text-danger">
        <div class="col-md-10 offset-md-1">
            <p class="text-danger text-center">
                답글이 달린 글은 삭제 할 수 없습니다.
            </p>
        </div>
    </div>
    <div class="row text-center">
        <div class="col-md-10 offset-md-1 text-center">
            <a class="btn btn-link btn-lg" href="./">목록으로</a>
        </div>
    </div>

</div>

<jsp:include page="template/footer.jsp"></jsp:include>