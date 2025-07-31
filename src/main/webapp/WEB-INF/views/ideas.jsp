<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 페이지 설정 --%>
<c:set var="pageTitle" value="아이디어 게시판" />
<c:set var="currentPage" value="ideas" />

<%-- 헤더 포함 --%>
<jsp:include page="includes/header.jsp" />

<div class="d-flex justify-content-between align-items-center mb-4">
    <h1>아이디어 게시판</h1>
    <button class="btn btn-success" data-bs-toggle="modal" data-bs-target="#ideaFormModal">
        <i class="fas fa-plus-circle me-1"></i> 아이디어 등록
    </button>
</div>

<!-- 아이디어 검색 섹션 -->
<section class="row mb-4">
    <div class="col-12">
        <div class="card shadow-sm">
            <div class="card-body">
                <form class="row g-3" id="searchForm">
                    <div class="col-md-6">
                        <select class="form-select" id="ideaCategory">
                            <option selected value="">전체 카테고리</option>
                            <option value="주거">주거</option>
                            <option value="일자리">일자리</option>
                            <option value="관광">관광</option>
                            <option value="문화">문화</option>
                            <option value="교육">교육</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div class="col-md-6">
                        <div class="input-group">
                            <input type="text" class="form-control" id="searchKeyword" placeholder="검색어를 입력하세요">
                            <button class="btn btn-outline-primary" type="submit">검색</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- 아이디어 목록 섹션 -->
<section id="ideaList" class="row row-cols-1 row-cols-md-2 g-4 mb-4">
    <c:forEach var="idea" items="${ideas}" varStatus="status">
        <div class="col idea-item" data-page="${(status.index / 6) + 1}">
            <div class="card h-100 shadow-sm">
                <div class="card-header bg-white">
                    <span class="badge bg-info me-2">${idea.category}</span>
                    <small class="text-muted">
                        ${idea.createdAt.toString().substring(0, 10)}
                    </small>
                    <div class="float-end">
                        <span title="공감수"><i class="far fa-heart"></i> ${idea.likeCount}</span>
                    </div>
                </div>
                <div class="card-body">
                    <h5 class="card-title">${idea.title}</h5>
                    <p class="card-text">${idea.content}</p>
                </div>
                <div class="card-footer bg-white">
                    <div class="d-flex justify-content-between align-items-center">
                        <div>
                            <button class="btn btn-sm btn-outline-primary me-1" data-idea-id="${idea.id}">자세히 보기</button>
                            <button class="btn btn-sm btn-outline-danger like-button" data-idea-id="${idea.id}">
                                <i class="far fa-heart"></i> 공감하기
                            </button>
                        </div>
                        <div>
                            <button class="btn btn-sm btn-outline-warning me-1 edit-button" data-idea-id="${idea.id}">
                                <i class="fas fa-edit"></i> 수정
                            </button>
                            <button class="btn btn-sm btn-outline-danger delete-button" data-idea-id="${idea.id}">
                                <i class="fas fa-trash"></i> 삭제
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</section>

<!-- 페이지네이션 -->
<nav aria-label="아이디어 목록 페이지네이션">
    <ul id="pagination" class="pagination justify-content-center">
        <!-- JavaScript로 동적 생성 -->
    </ul>
</nav>

<!-- 아이디어 등록 모달 -->
<div class="modal fade" id="ideaFormModal" tabindex="-1" aria-labelledby="ideaFormModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ideaFormModalLabel">아이디어 등록</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="ideaForm">
                    <div class="mb-3">
                        <label for="ideaTitle" class="form-label">제목</label>
                        <input type="text" class="form-control" id="ideaTitle" required>
                    </div>
                    <div class="mb-3">
                        <label for="ideaCategory" class="form-label">카테고리</label>
                        <select class="form-select" id="ideaCategoryInput" required>
                            <option value="" selected disabled>카테고리 선택</option>
                            <option value="주거">주거</option>
                            <option value="일자리">일자리</option>
                            <option value="관광">관광</option>
                            <option value="문화">문화</option>
                            <option value="교육">교육</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="ideaContent" class="form-label">내용</label>
                        <textarea class="form-control" id="ideaContent" rows="5" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="targetRegion" class="form-label">대상 지역</label>
                        <select class="form-select" id="targetRegion">
                            <option value="" selected>전체 지역</option>
                            <option value="강원도">강원도</option>
                            <option value="태백시">- 태백시</option>
                            <option value="삼척시">- 삼척시</option>
                            <option value="횡성군">- 횡성군</option>
                            <!-- 다른 지역 옵션들 -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="ideaPassword" class="form-label">비밀번호 <span class="text-danger">*</span></label>
                        <input type="password" class="form-control" id="ideaPassword" placeholder="게시글 수정/삭제 시 필요합니다" required>
                        <div class="form-text">게시글을 수정하거나 삭제할 때 필요한 비밀번호를 입력하세요.</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="submitIdea">등록하기</button>
            </div>
        </div>
    </div>
</div>

<!-- 아이디어 수정 모달 -->
<div class="modal fade" id="editIdeaModal" tabindex="-1" aria-labelledby="editIdeaModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editIdeaModalLabel">아이디어 수정</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="editIdeaForm">
                    <input type="hidden" id="editIdeaId" />
                    <div class="mb-3">
                        <label for="editIdeaTitle" class="form-label">제목</label>
                        <input type="text" class="form-control" id="editIdeaTitle" required>
                    </div>
                    <div class="mb-3">
                        <label for="editIdeaCategory" class="form-label">카테고리</label>
                        <select class="form-select" id="editIdeaCategory" required>
                            <option value="" disabled>카테고리 선택</option>
                            <option value="주거">주거</option>
                            <option value="일자리">일자리</option>
                            <option value="관광">관광</option>
                            <option value="문화">문화</option>
                            <option value="교육">교육</option>
                            <option value="기타">기타</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="editIdeaContent" class="form-label">내용</label>
                        <textarea class="form-control" id="editIdeaContent" rows="5" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="editTargetRegion" class="form-label">대상 지역</label>
                        <select class="form-select" id="editTargetRegion">
                            <option value="">전체 지역</option>
                            <option value="강원도">강원도</option>
                            <option value="태백시">- 태백시</option>
                            <option value="삼척시">- 삼척시</option>
                            <option value="횡성군">- 횡성군</option>
                            <!-- 다른 지역 옵션들 -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="editIdeaPassword" class="form-label">비밀번호 확인 <span class="text-danger">*</span></label>
                        <input type="password" class="form-control" id="editIdeaPassword" placeholder="게시글 작성 시 입력한 비밀번호" required>
                        <div class="form-text">게시글 작성 시 설정한 비밀번호를 입력하세요.</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-warning" id="updateIdea">수정하기</button>
            </div>
        </div>
    </div>
</div>

<!-- 아이디어 상세보기 모달 -->
<div class="modal fade" id="ideaDetailModal" tabindex="-1" aria-labelledby="ideaDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="ideaDetailModalLabel">아이디어 상세보기</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="row mb-3">
                    <div class="col-md-8">
                        <h4 id="detailTitle">제목</h4>
                    </div>
                    <div class="col-md-4 text-end">
                        <span class="badge bg-info me-2" id="detailCategory">카테고리</span>
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-md-6">
                        <small class="text-muted">작성일: <span id="detailCreatedAt"></span></small>
                    </div>
                    <div class="col-md-6 text-end">
                        <small class="text-muted">
                            <i class="far fa-heart"></i> <span id="detailLikeCount">0</span>
                        </small>
                    </div>
                </div>

                <div class="mb-3">
                    <strong>대상 지역:</strong> <span id="detailRegion">전체 지역</span>
                </div>

                <div class="mb-3">
                    <strong>내용:</strong>
                    <div class="mt-2 p-3 bg-light rounded">
                        <p id="detailContent">내용이 여기에 표시됩니다.</p>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="passwordConfirmModal" tabindex="-1" aria-labelledby="passwordConfirmModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="passwordConfirmModalLabel">비밀번호 확인</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form id="passwordConfirmForm">
                    <input type="hidden" id="deleteIdeaId" />
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">비밀번호 <span class="text-danger">*</span></label>
                        <input type="password" class="form-control" id="confirmPassword" placeholder="게시글 작성 시 입력한 비밀번호" required>
                        <div class="form-text">게시글을 삭제하시려면 작성 시 설정한 비밀번호를 입력하세요.</div>
                    </div>
                    <div class="alert alert-warning" role="alert">
                        <i class="fas fa-exclamation-triangle me-2"></i>
                        삭제된 게시글은 복구할 수 없습니다. 정말 삭제하시겠습니까?
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-danger" id="confirmDelete">삭제하기</button>
            </div>
        </div>
    </div>
</div>

<%-- 추가 스크립트 설정 --%>

<%-- 푸터 포함 --%>
<jsp:include page="includes/footer.jsp" />

<script>
document.addEventListener('DOMContentLoaded', function() {
    // 페이지네이션 변수
    let currentPage = 1;
    const itemsPerPage = 6;

    // 페이지네이션 초기화
    initializePagination();

    function initializePagination() {
        const ideaItems = document.querySelectorAll('.idea-item');
        const totalItems = ideaItems.length;
        const totalPages = Math.ceil(totalItems / itemsPerPage);

        console.log('총 게시글:', totalItems, '총 페이지:', totalPages); // 디버깅용

        // 첫 페이지 표시
        showPage(1);

        // 페이지네이션 버튼 생성
        createPaginationButtons(totalPages);
    }

    function showPage(page) {
        const ideaItems = document.querySelectorAll('.idea-item');
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        // 모든 아이템 숨기기
        ideaItems.forEach((item, index) => {
            if (index >= startIndex && index < endIndex) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });

        currentPage = page;
        updatePaginationButtons();
    }

    function createPaginationButtons(totalPages) {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = '';

        if (totalPages <= 1) {
            pagination.style.display = 'none';
            return;
        }

        pagination.style.display = 'flex';

        // 이전 버튼
        const prevLi = document.createElement('li');
        prevLi.className = 'page-item';
        prevLi.innerHTML = '<a class="page-link" href="#" tabindex="-1">이전</a>';
        prevLi.addEventListener('click', function(e) {
            e.preventDefault();
            if (currentPage > 1) {
                showPage(currentPage - 1);
            }
        });
        pagination.appendChild(prevLi);

        // 페이지 번호 버튼들
        for (let i = 1; i <= totalPages; i++) {
            const li = document.createElement('li');
            li.className = 'page-item';
            li.innerHTML = '<a class="page-link" href="#">' + i + '</a>';
            li.addEventListener('click', function(e) {
                e.preventDefault();
                showPage(i);
            });
            pagination.appendChild(li);
        }

        // 다음 버튼
        const nextLi = document.createElement('li');
        nextLi.className = 'page-item';
        nextLi.innerHTML = '<a class="page-link" href="#">다음</a>';
        nextLi.addEventListener('click', function(e) {
            e.preventDefault();
            const totalPages = Math.ceil(document.querySelectorAll('.idea-item').length / itemsPerPage);
            if (currentPage < totalPages) {
                showPage(currentPage + 1);
            }
        });
        pagination.appendChild(nextLi);
    }

    function updatePaginationButtons() {
        const pagination = document.getElementById('pagination');
        const pageItems = pagination.querySelectorAll('.page-item');
        const totalPages = Math.ceil(document.querySelectorAll('.idea-item').length / itemsPerPage);

        pageItems.forEach((item, index) => {
            item.classList.remove('active', 'disabled');

            if (index === 0) { // 이전 버튼
                if (currentPage === 1) {
                    item.classList.add('disabled');
                }
            } else if (index === pageItems.length - 1) { // 다음 버튼
                if (currentPage === totalPages) {
                    item.classList.add('disabled');
                }
            } else { // 페이지 번호 버튼
                const pageNum = index;
                if (pageNum === currentPage) {
                    item.classList.add('active');
                }
            }
        });
    }

    // 검색 기능 구현
    const searchForm = document.getElementById('searchForm');
    const categorySelect = document.getElementById('ideaCategory');
    const searchInput = document.getElementById('searchKeyword');

    // 검색 폼 제출 이벤트
    searchForm.addEventListener('submit', function(e) {
        e.preventDefault();
        performSearch();
    });

    // 카테고리 변경 시 즉시 검색
    categorySelect.addEventListener('change', function() {
        performSearch();
    });

    // 검색 실행 함수
    function performSearch() {
        const selectedCategory = categorySelect.value;
        const searchKeyword = searchInput.value.trim().toLowerCase();
        const allIdeaItems = document.querySelectorAll('.idea-item');

        console.log('검색 실행 - 카테고리:', selectedCategory, '키워드:', searchKeyword);

        let visibleCount = 0;

        allIdeaItems.forEach(item => {
            const card = item.querySelector('.card');
            const categoryBadge = card.querySelector('.badge');
            const title = card.querySelector('.card-title');
            const content = card.querySelector('.card-text');

            const itemCategory = categoryBadge ? categoryBadge.textContent.trim() : '';
            const itemTitle = title ? title.textContent.trim().toLowerCase() : '';
            const itemContent = content ? content.textContent.trim().toLowerCase() : '';

            // 카테고리 필터링
            const categoryMatch = !selectedCategory || itemCategory === selectedCategory;

            // 키워드 필터링 (제목 + 내용에서 검색)
            const keywordMatch = !searchKeyword ||
                itemTitle.includes(searchKeyword) ||
                itemContent.includes(searchKeyword);

            if (categoryMatch && keywordMatch) {
                item.style.display = 'block';
                item.setAttribute('data-visible', 'true');
                visibleCount++;
            } else {
                item.style.display = 'none';
                item.setAttribute('data-visible', 'false');
            }
        });

        console.log('검색 결과:', visibleCount + '개 게시글');

        // 검색 후 페이지네이션 재설정
        resetPaginationForSearch();
    }

    // 검색 결과에 맞춰 페이지네이션 재설정
    function resetPaginationForSearch() {
        const visibleItems = document.querySelectorAll('.idea-item[data-visible="true"]');
        const totalPages = Math.ceil(visibleItems.length / itemsPerPage);

        // 페이지네이션 버튼 재생성
        createPaginationButtons(totalPages);

        // 첫 페이지로 이동
        showSearchPage(1);
    }

    // 검색 결과용 페이지 표시 함수
    function showSearchPage(page) {
        const visibleItems = document.querySelectorAll('.idea-item[data-visible="true"]');
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        // 모든 아이템 숨기기
        document.querySelectorAll('.idea-item').forEach(item => {
            item.style.display = 'none';
        });

        // 해당 페이지의 검색 결과만 표시
        visibleItems.forEach((item, index) => {
            if (index >= startIndex && index < endIndex) {
                item.style.display = 'block';
            }
        });

        currentPage = page;
        updatePaginationButtons();
    }

    // 기존 showPage 함수 수정 (검색 상태 고려)
    function showPage(page) {
        const visibleItems = document.querySelectorAll('.idea-item[data-visible="true"]');

        if (visibleItems.length > 0) {
            // 검색 상태인 경우
            showSearchPage(page);
        } else {
            // 전체 보기 상태인 경우
            const ideaItems = document.querySelectorAll('.idea-item');
            const startIndex = (page - 1) * itemsPerPage;
            const endIndex = startIndex + itemsPerPage;

            ideaItems.forEach((item, index) => {
                if (index >= startIndex && index < endIndex) {
                    item.style.display = 'block';
                } else {
                    item.style.display = 'none';
                }
            });

            currentPage = page;
            updatePaginationButtons();
        }
    }

    // 전체 보기로 돌아가는 함수 (검색 초기화)
    function resetSearch() {
        categorySelect.value = '';
        searchInput.value = '';

        // 모든 아이템 표시 상태로 복원
        document.querySelectorAll('.idea-item').forEach(item => {
            item.removeAttribute('data-visible');
        });

        // 페이지네이션 재초기화
        initializePagination();
    }
    const detailButtonsByText = Array.from(document.querySelectorAll('button')).filter(btn =>
        btn.textContent.includes('자세히 보기')
    );

    detailButtonsByText.forEach((btn, index) => {
        btn.addEventListener('click', function(e) {
            e.preventDefault();
            const ideaId = this.dataset.ideaId;

            if (ideaId) {
                openDetailModal(ideaId);
            } else {
                alert('아이디어 ID를 찾을 수 없습니다.');
            }
        });
    });

    // 상세보기 모달 열기 함수
    function openDetailModal(ideaId) {
        const getUrl = '/api/idea/' + ideaId;

        fetch(getUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('HTTP ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // 모달 요소들이 존재하는지 확인
            const modal = document.getElementById('ideaDetailModal');
            if (!modal) {
                alert('모달을 찾을 수 없습니다!');
                return;
            }

            // 모달 내용 업데이트
            const titleEl = document.getElementById('detailTitle');
            const categoryEl = document.getElementById('detailCategory');
            const contentEl = document.getElementById('detailContent');
            const regionEl = document.getElementById('detailRegion');
            const createdAtEl = document.getElementById('detailCreatedAt');
            const likeCountEl = document.getElementById('detailLikeCount');

            if (titleEl) titleEl.textContent = data.title || '제목 없음';
            if (categoryEl) categoryEl.textContent = data.category || '카테고리 없음';
            if (contentEl) contentEl.textContent = data.content || '내용 없음';
            if (regionEl) regionEl.textContent = data.targetRegion || '전체 지역';
            if (createdAtEl) {
                // 목록과 동일한 날짜 로직 사용
                let dateStr = '';
                if (data.createdAt) {
                    if (typeof data.createdAt === 'string') {
                        dateStr = data.createdAt.substring(0, 10);
                    } else if (data.createdAt.toString) {
                        dateStr = data.createdAt.toString().substring(0, 10);
                    }
                }
                createdAtEl.textContent = dateStr;
            }
            if (likeCountEl) likeCountEl.textContent = data.likeCount || 0;

            // Bootstrap 모달 열기
            const detailModal = new bootstrap.Modal(modal);
            detailModal.show();
        })
        .catch(error => {
            console.error('상세 정보 로드 오류:', error);
            alert('상세 정보를 불러오는 데 실패했습니다: ' + error.message);
        });
    }

    // 페이지네이션 초기화
    initializePagination();

    function initializePagination() {
        const ideaItems = document.querySelectorAll('.idea-item');
        const totalItems = ideaItems.length;
        const totalPages = Math.ceil(totalItems / itemsPerPage);

        console.log('총 게시글:', totalItems, '총 페이지:', totalPages); // 디버깅용

        // 첫 페이지 표시
        showPage(1);

        // 페이지네이션 버튼 생성
        createPaginationButtons(totalPages);
    }

    function showPage(page) {
        const ideaItems = document.querySelectorAll('.idea-item');
        const startIndex = (page - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;

        // 모든 아이템 숨기기
        ideaItems.forEach((item, index) => {
            if (index >= startIndex && index < endIndex) {
                item.style.display = 'block';
            } else {
                item.style.display = 'none';
            }
        });

        currentPage = page;
        updatePaginationButtons();
    }

    function createPaginationButtons(totalPages) {
        const pagination = document.getElementById('pagination');
        pagination.innerHTML = '';

        if (totalPages <= 1) {
            pagination.style.display = 'none';
            return;
        }

        pagination.style.display = 'flex';

        // 이전 버튼
        const prevLi = document.createElement('li');
        prevLi.className = 'page-item';
        prevLi.innerHTML = '<a class="page-link" href="#" tabindex="-1">이전</a>';
        prevLi.addEventListener('click', function(e) {
            e.preventDefault();
            if (currentPage > 1) {
                showPage(currentPage - 1);
            }
        });
        pagination.appendChild(prevLi);

        // 페이지 번호 버튼들
        for (let i = 1; i <= totalPages; i++) {
            const li = document.createElement('li');
            li.className = 'page-item';
            li.innerHTML = '<a class="page-link" href="#">' + i + '</a>';
            li.addEventListener('click', function(e) {
                e.preventDefault();
                showPage(i);
            });
            pagination.appendChild(li);
        }

        // 다음 버튼
        const nextLi = document.createElement('li');
        nextLi.className = 'page-item';
        nextLi.innerHTML = '<a class="page-link" href="#">다음</a>';
        nextLi.addEventListener('click', function(e) {
            e.preventDefault();
            const totalPages = Math.ceil(document.querySelectorAll('.idea-item').length / itemsPerPage);
            if (currentPage < totalPages) {
                showPage(currentPage + 1);
            }
        });
        pagination.appendChild(nextLi);
    }

    function updatePaginationButtons() {
        const pagination = document.getElementById('pagination');
        const pageItems = pagination.querySelectorAll('.page-item');
        const totalPages = Math.ceil(document.querySelectorAll('.idea-item').length / itemsPerPage);

        pageItems.forEach((item, index) => {
            item.classList.remove('active', 'disabled');

            if (index === 0) { // 이전 버튼
                if (currentPage === 1) {
                    item.classList.add('disabled');
                }
            } else if (index === pageItems.length - 1) { // 다음 버튼
                if (currentPage === totalPages) {
                    item.classList.add('disabled');
                }
            } else { // 페이지 번호 버튼
                const pageNum = index;
                if (pageNum === currentPage) {
                    item.classList.add('active');
                }
            }
        });
    }

    // 등록
    document.getElementById('submitIdea').addEventListener('click', function() {
        const form = document.getElementById('ideaForm');
        if (form.checkValidity()) {
            const formData = {
                title: document.getElementById('ideaTitle').value,
                category: document.getElementById('ideaCategoryInput').value,
                content: document.getElementById('ideaContent').value,
                targetRegion: document.getElementById('targetRegion').value,
                anonPassword: document.getElementById('ideaPassword').value
            };

            fetch('/api/ideas', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(formData)
            })
            .then(response => response.json())
            .then(data => {
                alert('아이디어가 등록되었습니다!');
                const modal = bootstrap.Modal.getInstance(document.getElementById('ideaFormModal'));
                modal.hide();
                form.reset();
                location.reload();
            })
            .catch(error => {
                console.error('등록 오류:', error);
                alert('등록 중 오류 발생');
            });
        } else {
            form.reportValidity();
        }
    });

    // 삭제 버튼
    document.querySelectorAll('.delete-button').forEach(button => {
        button.addEventListener('click', function() {
            const ideaId = this.dataset.ideaId;
            openDeleteModal(ideaId);
        });
    });

    // 수정 버튼
    document.querySelectorAll('.edit-button').forEach(button => {
        button.addEventListener('click', function() {
            const ideaId = this.dataset.ideaId;
            console.log('수정 버튼 클릭 - dataset.ideaId:', ideaId); // 디버깅용
            console.log('this.getAttribute data-idea-id:', this.getAttribute('data-idea-id')); // 대안 방법
            openEditModal(ideaId);
        });
    });

    // 공감 버튼
    document.querySelectorAll('.like-button').forEach(button => {
        button.addEventListener('click', function() {
            const ideaId = this.dataset.ideaId;
            const likeUrl = '/api/idea/' + ideaId + '/like';
            console.log('공감 URL:', likeUrl); // 디버깅용

            fetch(likeUrl, {
                method: 'POST'
            })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('공감 실패');
                }
            })
            .then(data => {
                console.log('공감 응답:', data); // 디버깅용

                // 버튼 상태 변경
                this.innerHTML = '<i class="fas fa-heart"></i> 공감됨';
                this.classList.remove('btn-outline-danger');
                this.classList.add('btn-danger');
                this.disabled = true;

                // 공감수 실시간 업데이트
                const ideaCard = this.closest('.card');
                const likeCountSpan = ideaCard.querySelector('[title="공감수"]');
                if (likeCountSpan && data.likeCount) {
                    likeCountSpan.innerHTML = '<i class="far fa-heart"></i> ' + data.likeCount;
                }

                alert('아이디어에 공감했습니다!');
            })
            .catch(error => {
                console.error('공감 오류:', error);
                alert('공감 중 오류 발생');
            });
        });
    });

    // 수정 모달 열기 함수
    window.openEditModal = function(ideaId) {
        console.log('수정 모달 - 받은 ideaId:', ideaId); // 디버깅용

        document.getElementById('editIdeaId').value = ideaId;

        // AJAX로 아이디어 정보 불러오기
        const getUrl = '/api/idea/' + ideaId;
        console.log('데이터 가져오기 URL:', getUrl); // 디버깅용

        fetch(getUrl)
        .then(response => response.json())
        .then(data => {
            console.log('가져온 데이터:', data); // 디버깅용
            document.getElementById('editIdeaTitle').value = data.title || '';
            document.getElementById('editIdeaCategory').value = data.category || '';
            document.getElementById('editIdeaContent').value = data.content || '';
            document.getElementById('editTargetRegion').value = data.targetRegion || '';
        })
        .catch(error => {
            console.error('수정 모달 데이터 로드 오류:', error);
            alert('아이디어 정보를 불러오는 데 실패했습니다.');
        });

        const editModal = new bootstrap.Modal(document.getElementById('editIdeaModal'));
        editModal.show();
    };

    // 수정
    document.getElementById('updateIdea').addEventListener('click', function() {
        const form = document.getElementById('editIdeaForm');
        const ideaId = document.getElementById('editIdeaId').value;
        const password = document.getElementById('editIdeaPassword').value;

        if (form.checkValidity() && password) {
            const updateData = {
                title: document.getElementById('editIdeaTitle').value,
                category: document.getElementById('editIdeaCategory').value,
                content: document.getElementById('editIdeaContent').value,
                targetRegion: document.getElementById('editTargetRegion').value,
                anonPassword: password
            };

            const updateUrl = '/api/idea/' + ideaId;
            console.log('수정 URL:', updateUrl); // 디버깅용
            console.log('전송할 데이터:', updateData); // 디버깅용

            fetch(updateUrl, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(updateData)
            })
            .then(response => {
                console.log('응답 상태:', response.status); // 디버깅용
                if (response.ok) {
                    alert('아이디어가 수정되었습니다!');
                    const modal = bootstrap.Modal.getInstance(document.getElementById('editIdeaModal'));
                    modal.hide();
                    location.reload();
                } else {
                    return response.text().then(text => {
                        console.log('서버 에러 응답:', text); // 디버깅용
                        throw new Error('수정 실패: ' + text);
                    });
                }
            })
            .catch(error => {
                console.error('수정 오류:', error);
                alert('수정 중 오류 발생');
            });
        } else {
            form.reportValidity();
        }
    });

    // 삭제 모달 열기 함수
    window.openDeleteModal = function(ideaId) {
        document.getElementById('deleteIdeaId').value = ideaId;
        const deleteModal = new bootstrap.Modal(document.getElementById('passwordConfirmModal'));
        deleteModal.show();
    };

    // 삭제
    document.getElementById('confirmDelete').addEventListener('click', function() {
        const ideaId = document.getElementById('deleteIdeaId').value;
        const password = document.getElementById('confirmPassword').value;

        console.log('삭제 시도 - ideaId:', ideaId, 'password:', password); // 디버깅용

        if (password && ideaId) {
            const deleteUrl = '/api/idea/' + ideaId + '?anonPassword=' + password;
            console.log('삭제 URL:', deleteUrl); // 디버깅용

            fetch(deleteUrl, {
                method: 'DELETE'
            })
            .then(response => {
                if (response.ok) {
                    alert('아이디어가 삭제되었습니다!');
                    const modal = bootstrap.Modal.getInstance(document.getElementById('passwordConfirmModal'));
                    modal.hide();
                    location.reload();
                } else {
                    throw new Error('삭제 실패: 비밀번호가 일치하지 않거나 게시물이 존재하지 않습니다.');
                }
            })
            .catch(error => {
                console.error('삭제 오류:', error);
                alert(error.message);
            });
        } else {
            if (!ideaId) {
                alert('게시글 ID를 찾을 수 없습니다.');
            } else {
                alert('비밀번호를 입력해주세요.');
                document.getElementById('confirmPassword').focus();
            }
        }
    });

    // 모달 닫힐 때 리셋
    document.getElementById('editIdeaModal').addEventListener('hidden.bs.modal', function() {
        document.getElementById('editIdeaForm').reset();
    });

    document.getElementById('passwordConfirmModal').addEventListener('hidden.bs.modal', function() {
        document.getElementById('passwordConfirmForm').reset();
    });

});
</script>