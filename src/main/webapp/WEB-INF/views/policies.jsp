<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- 페이지 설정 --%>
<c:set var="pageTitle" value="정책 조회" />
<c:set var="currentPage" value="policies" />

<%-- 헤더 포함 --%>
<jsp:include page="includes/header.jsp" />

<h1 class="mb-4">지자체 정책 조회</h1>

<!-- 정책 목록 섹션 -->
<section class="row">
    <div class="col-12">
        <div class="card shadow">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h3 class="h5 mb-0">정책 목록</h3>
                <span class="text-muted">총 <strong>${fn:length(policies)}</strong>개 정책</span>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light">
                            <tr>
                                <th>정책명</th>
                                <th>지역</th>
                                <th>정책 유형</th>
                                <th>지원 대상</th>
                                <th>상세보기</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="policy" items="${policies}">
                                <tr>
                                    <td>${policy.name}</td>
                                    <td>${policy.region}</td>
                                    <td>${policy.policyType}</td>
                                    <td>${policy.targetAudience}</td>
                                    <td>
                                        <a class="btn btn-sm btn-outline-primary" href="${policy.pdfUrl}" target="_blank">상세보기</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<%-- 푸터 포함 --%>
<jsp:include page="includes/footer.jsp" />