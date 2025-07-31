<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

</main>
<!-- 메인 콘텐츠 끝 -->

<!-- 푸터 -->
<footer class="py-3 my-4 border-top">
    <div class="container">
        <p class="text-center text-muted">
            &copy; 2025 도시소멸 위기 알리미 | 1인 프로젝트
            <br>도시소멸 문제에 대한 인식 제고와 해결책 모색을 위한 웹사이트입니다.
        </p>
    </div>
</footer>
</div>

<!-- 공통 스크립트 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<!-- 추가 스크립트 -->
<c:if test="${not empty additionalScripts}">
    ${additionalScripts}
</c:if>
</body>
</html>