<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} - 도시소멸 위기 알리미</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">

    <!-- Leaflet.js 라이브러리 (항상 추가) -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>

    <!-- 컨텍스트 경로 추가 -->
    <script>
        var contextPath = '${pageContext.request.contextPath}';
    </script>

    <!-- 추가 스크립트나 스타일시트 -->
    <c:if test="${not empty additionalStyles}">
        ${additionalStyles}
    </c:if>
</head>
<body>
    <div class="container-fluid">
        <!-- 헤더 시작 -->
        <header class="py-3 mb-4 border-bottom">
            <div class="container d-flex justify-content-between align-items-center">
                <a href="${pageContext.request.contextPath}/" class="d-flex align-items-center text-dark text-decoration-none">
                    <i class="fas fa-city me-2"></i>
                    <span class="fs-4 fw-bold">도시소멸 위기 알리미</span>
                </a>
                <div>
                    <button class="btn ${currentPage == 'main' ? 'btn-primary' : 'btn-outline-primary'} me-2"
                            onclick="location.href='${pageContext.request.contextPath}/'">지역정보</button>
                    <button class="btn ${currentPage == 'policies' ? 'btn-primary' : 'btn-outline-primary'} me-2"
                            onclick="location.href='${pageContext.request.contextPath}/policies'">정책조회</button>
                    <button class="btn ${currentPage == 'ideas' ? 'btn-primary' : 'btn-outline-primary'}"
                            onclick="location.href='${pageContext.request.contextPath}/ideas'">아이디어 게시판</button>
                </div>
            </div>
        </header>
        <!-- 헤더 끝 -->

        <!-- 메인 콘텐츠 시작 -->
        <main class="container my-4">