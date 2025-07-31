<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="pageTitle" value="강원도 소멸 위기 알리미" />
<c:set var="currentPage" value="main" />

<jsp:include page="includes/header.jsp" />

<!-- 소개 섹션 -->
<section class="row mb-5">
    <div class="col-md-6">
        <h1 class="display-5 fw-bold mb-3">강원도 소멸 위기,<br>함께 살리는 프로젝트</h1>
        <p class="fs-5">강원도 내 인구감소와 저출산으로 인한 도시소멸 위기에 처한 지역을 알리고, 함께 해결책을 모색합니다.
        지역 활성화를 위한 아이디어를 공유하고 희망의 불씨를 지펴보세요.</p>
    </div>
    <div class="col-md-6 d-flex align-items-center justify-content-end">
        <div class="alert alert-danger w-100" style="max-width: 450px;">
            <h4 class="alert-heading"><i class="fas fa-exclamation-triangle"></i> 강원도 소멸 위험 지역 알림</h4>
            <p>인구 총량, 감소 속도, 청년층 비율 등 인구구조 전반에 걸친 지표를 고려하여 소멸위험도를 분석하고 해당 지역을 선정하였습니다.</p>
            <p>현재 강원도에서 소멸 위험이 높은 지역은 다음과 같습니다.</p>

            <ul class="mb-0">
                <c:forEach var="region" items="${highRiskRegions}" varStatus="status">
                    <c:if test="${status.index lt 3}">
                        <li>
                            <strong>${region.name}</strong> - 소멸지수: ${region.extinctionIndex}
                            (인구감소율: ${region.populationDecreaseRate}%)
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </div>
</section>

<!-- 지도 섹션 -->
<section class="row mb-5">
    <div class="col-12">
        <div class="card shadow">
            <div class="card-header bg-white d-flex justify-content-between align-items-center">
                <h3 class="h5 mb-0">강원도 지역별 소멸 위험도</h3>
                <!-- 🚀 총 고위험 지역 표시 -->

            </div>
            <div class="card-body">
                <!-- ⛔ map-legend 삭제 완료 -->
                <div id="map-container" class="position-relative" style="height: 600px; background-color: #f8f9fa;">
                    <div id="loading" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); color: #666; text-align: center; z-index: 1000;">
                        <div class="spinner-border text-primary" role="status" style="margin-bottom: 10px;">
                            <span class="visually-hidden">Loading...</span>
                        </div>
                        <div>지도를 불러오는 중...</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- 주요 기능 섹션 -->
<section class="row mb-5">
    <div class="col-12 text-center mb-4">
        <h2 class="h2">주요 기능</h2>
        <p class="text-muted">도시소멸 위기 알리미가 제공하는 주요 서비스입니다</p>
    </div>

    <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-map-marked-alt fa-3x text-primary mb-3"></i>
                <h3 class="h5">소멸 위기 지역 정보</h3>
                <p class="card-text">인구 감소율, 소멸지수, 위험 등급 등 지역별 통계 정보를 제공합니다.</p>
                <a href="#" class="btn btn-sm btn-outline-primary">자세히 보기</a>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-file-contract fa-3x text-primary mb-3"></i>
                <h3 class="h5">지자체 대응 정책 조회</h3>
                <p class="card-text">각 지역에서 시행 중인 이주 지원 정책, 관광/문화 요소 정보를 제공합니다.</p>
                <a href="#" class="btn btn-sm btn-outline-primary">정책 조회</a>
            </div>
        </div>
    </div>

    <div class="col-md-4 mb-4">
        <div class="card h-100 shadow-sm">
            <div class="card-body text-center">
                <i class="fas fa-lightbulb fa-3x text-primary mb-3"></i>
                <h3 class="h5">아이디어 공유 게시판</h3>
                <p class="card-text">지역 활성화 아이디어를 익명으로 공유하고 공감(좋아요)할 수 있습니다.</p>
                <a href="#" class="btn btn-sm btn-outline-primary">게시판 가기</a>
            </div>
        </div>
    </div>
</section>

<!-- 위험 지역 통계 섹션 -->
<section class="row">
    <div class="col-12">
        <div class="card shadow">
            <div class="card-header bg-white">
                <h3 class="h5 mb-0">강원도 소멸 위험 지역 통계 (Top 3)</h3>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead class="table-light text-center">
                            <tr>
                                <th style="width: 25%;">지역명</th>
                                <th style="width: 25%;">소멸지수</th>
                                <th style="width: 25%;">위험등급</th>
                                <th style="width: 25%;">인구감소율(%)</th>
                            </tr>
                        </thead>
                        <tbody class="text-center">
                            <c:forEach var="region" items="${highRiskRegions}" varStatus="status">
                                <c:if test="${status.index lt 3}">
                                    <tr>
                                        <td>${region.name}</td>
                                        <td>${region.extinctionIndex}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${region.riskLevel == '고위험'}">
                                                    <span class="badge bg-danger">${region.riskLevel}</span>
                                                </c:when>
                                                <c:when test="${region.riskLevel == '위험'}">
                                                    <span class="badge bg-warning text-dark">${region.riskLevel}</span>
                                                </c:when>
                                                <c:when test="${region.riskLevel == '주의'}">
                                                    <span class="badge bg-info">${region.riskLevel}</span>
                                                </c:when>
                                                <c:when test="${region.riskLevel == '안전'}">
                                                    <span class="badge bg-success">${region.riskLevel}</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-secondary">${region.riskLevel}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${region.populationDecreaseRate}%</td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer text-center">
                <a href="https://www.nabis.go.kr/dext5upload/handler/dext5handler.jsp?customValue=&d03=..." class="btn btn-primary" target="_blank">전체 강원도 통계 보기</a>
            </div>
        </div>
    </div>
</section>

<!-- 카카오맵 -->
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=262c3f48e455efd3752d1b3099700fe9"></script>

<script>
    console.log('카카오맵 스크립트 로드 후 - kakao 객체:', typeof kakao);

    document.addEventListener('DOMContentLoaded', function() {
        if (typeof kakao !== 'undefined' && kakao.maps) {
            console.log('카카오맵 API 정상 로드됨');

            var mapContainer = document.getElementById('map-container');
            var mapOption = {
                center: new kakao.maps.LatLng(37.8228, 128.1555),
                level: 9
            };

            try {
                var map = new kakao.maps.Map(mapContainer, mapOption);
                window.kakaoMap = map;  // ✅ 반드시 추가

                console.log('카카오맵 생성 성공');

                var loadingDiv = document.getElementById('loading');
                if (loadingDiv) {
                    loadingDiv.style.display = 'none';
                }
            } catch (error) {
                console.error('카카오맵 생성 실패:', error);
            }
        } else {
            console.error('카카오맵 API 로드 실패');
            var loadingDiv = document.getElementById('loading');
            if (loadingDiv) {
                loadingDiv.innerHTML = '카카오맵 로드 실패 - API 키 문제일 수 있습니다';
            }
        }
    });
</script>

<script src="${pageContext.request.contextPath}/static/js/gangwon-map.js"></script>
<script src="${pageContext.request.contextPath}/static/js/main.js"></script>

<jsp:include page="includes/footer.jsp" />