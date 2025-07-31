/**
 * main.js
 * 메인 페이지의 동작을 처리하는 스크립트
 */

document.addEventListener('DOMContentLoaded', function() {
    // 위험지역 확인하기 버튼 이벤트 리스너
    const dangerButton = document.querySelector('.btn-danger');
    if (dangerButton) {
        dangerButton.addEventListener('click', function() {
            // 지도 섹션으로 스크롤
            document.querySelector('#map-container').scrollIntoView({
                behavior: 'smooth'
            });

            // 강원도 고위험 지역 강조 토글 활성화
            document.getElementById('highlightGangwon').checked = true;

            // 1초 후 강조 효과 적용 (스크롤 후 적용)
            setTimeout(() => {
                const event = new Event('change');
                document.getElementById('highlightGangwon').dispatchEvent(event);
            }, 1000);
        });
    }

    // 아이디어 공유하기 버튼 이벤트 리스너
    const shareButton = document.querySelector('.btn-outline-secondary');
    if (shareButton) {
        shareButton.addEventListener('click', function() {
            // 아이디어 게시판으로 이동
            alert('아이디어 게시판 페이지로 이동합니다.');
            window.location.href = contextPath + '/ideas';
        });
    }

    // 네비게이션 버튼 이벤트 리스너 (클릭 핸들러는 header.jsp에서 처리)

    // 지역 상세보기 버튼 이벤트 리스너
    document.querySelectorAll('.table .btn-outline-primary').forEach(button => {
        button.addEventListener('click', function() {
            const row = this.closest('tr');
            const regionName = row.cells[0].textContent;

            // 지역 상세 페이지로 이동
            alert(`${regionName} 지역 상세 정보 페이지로 이동합니다.`);
            const regionId = this.dataset.regionId || regionName.replace(/\s+/g, '-').toLowerCase();
            window.location.href = contextPath + `/region/${regionId}`;
        });
    });

    // 전체 지역 통계 보기 버튼 이벤트 리스너
    const statsButton = document.querySelector('.card-footer .btn-primary');
    if (statsButton) {
        statsButton.addEventListener('click', function(event) {
            event.preventDefault(); // ⭐ a 태그 기본 이동 막기

            alert('전체 지역 통계 자료를 다운로드 합니다.');

            // 새 탭에서 열기
            window.open(this.href, '_blank');
        });
    }

    // 주요 기능 카드의 버튼 이벤트 리스너
    document.querySelectorAll('.card .btn-sm').forEach(button => {
        button.addEventListener('click', function() {
            const cardBody = this.closest('.card-body');
            if (!cardBody) return;

            const cardTitle = cardBody.querySelector('.h5');
            if (!cardTitle) return;

            const titleText = cardTitle.textContent;

            if (titleText.includes('소멸 위기 지역 정보')) {
                // 지도 섹션으로 스크롤
                const mapContainer = document.querySelector('#map-container');
                if (mapContainer) {
                    mapContainer.scrollIntoView({
                        behavior: 'smooth'
                    });
                }
            } else if (titleText.includes('지자체 대응 정책')) {
                // 정책 조회 페이지로 이동
                alert('지자체 정책 조회 페이지로 이동합니다.');
                window.location.href = contextPath + '/policies';
            } else if (titleText.includes('아이디어 공유')) {
                // 아이디어 게시판으로 이동
                alert('아이디어 게시판 페이지로 이동합니다.');
                window.location.href = contextPath + '/ideas';
            }
        });
    });
});