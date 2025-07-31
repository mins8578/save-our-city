// gangwon-map.js - 카카오맵 기반 강원도 위험도 지도

document.addEventListener("DOMContentLoaded", function () {
    console.log('gangwon-map.js 시작');

    // ✅ 1️⃣ 위험등급 데이터 (업데이트 반영)
    var riskData = {
        "춘천시": "주의",
        "원주시": "주의",
        "강릉시": "안전",
        "속초시": "안전",
        "홍천군": "위험",
        "평창군": "고위험",
        "인제군": "고위험",
        "화천군": "고위험",
        "삼척시": "위험",
        "정선군": "고위험",
        "태백시": "고위험",
        "영월군": "고위험",
        "횡성군": "위험",
        "고성군": "위험",
        "양구군": "고위험",
        "양양군": "위험",
        "철원군": "위험",
        "동해시": "위험"
    };

    // ✅ 2️⃣ 소멸지수 데이터 (업데이트 반영)
    var extinctionData = {
        "춘천시": 0.84,
        "원주시": 0.88,
        "강릉시": 0.91,
        "속초시": 0.95,
        "홍천군": 0.62,
        "평창군": 0.48,
        "인제군": 0.43,
        "화천군": 0.39,
        "삼척시": 0.58,
        "정선군": 0.52,
        "태백시": 0.47,
        "영월군": 0.49,
        "횡성군": 0.65,
        "고성군": 0.72,
        "양구군": 0.41,
        "양양군": 0.78,
        "철원군": 0.56,
        "동해시": 0.69
    };

    // ✅ 3️⃣ 각 지역의 좌표 데이터
    var regionCoordinates = {
        "춘천시": [37.8813, 127.7298],
        "원주시": [37.3422, 127.9200],
        "강릉시": [37.7515, 128.8760],
        "속초시": [38.2070, 128.5920],
        "홍천군": [37.6970, 127.8890],
        "평창군": [37.3700, 128.3900],
        "인제군": [38.0690, 128.1700],
        "화천군": [38.1060, 127.7080],
        "삼척시": [37.4480, 129.1650],
        "정선군": [37.3800, 128.6600],
        "태백시": [37.1640, 128.9856],
        "영월군": [37.1836, 128.4611],
        "횡성군": [37.4900, 127.9820],
        "고성군": [38.3800, 128.4700],
        "양구군": [38.1100, 127.9900],
        "양양군": [38.0750, 128.6190],
        "철원군": [38.1470, 127.3130],
        "동해시": [37.5244, 129.1144]
    };

    // ✅ 2️⃣-1 인구감소율 데이터
    var populationDecline = {
      "춘천시": +0.24,
      "원주시": -0.18,
      "강릉시": -0.81,
      "속초시": -1.36,
      "홍천군": -0.33,
      "평창군": "추정 불가",
      "인제군": "추정 불가",
      "화천군": "추정 불가",
      "삼척시": -0.89,
      "정선군": -2.59,
      "태백시": -21.4,
      "영월군": -5.8,
      "횡성군": "추정 불가",
      "고성군": -12.2,
      "양구군": -2.29,
      "양양군": "추정 불가",
      "철원군": -1.61,
      "동해시": "추정 불가"
    };


    // ✅ 4️⃣ 위험등급별 마커 색상 함수
    function getMarkerColor(riskLevel) {
        switch(riskLevel) {
            case "고위험": return "#dc3545";
            case "위험": return "#fd7e14";
            case "주의": return "#ffc107";
            case "안전": return "#28a745";
            default: return "#6c757d";
        }
    }

    // ✅ 5️⃣ 카카오맵이 준비될 때까지 대기
    function waitForKakaoMap() {
        if (typeof window.kakaoMap !== 'undefined' && window.kakaoMap) {
            console.log('카카오맵 준비됨, 마커 추가 시작');
            addMarkersToMap();
        } else {
            console.log('카카오맵 대기 중...');
            setTimeout(waitForKakaoMap, 100);
        }
    }

    // ✅ 6️⃣ 지도에 마커 추가하는 함수
    function addMarkersToMap() {
        var map = window.kakaoMap;
        var markers = [];
        var infoWindows = [];

        // ✅ LatLngBounds 객체 생성
        var bounds = new kakao.maps.LatLngBounds();

        console.log('전체 마커 추가 시작 - 총', Object.keys(regionCoordinates).length, '개 지역');

        Object.keys(regionCoordinates).forEach(function (regionName) {
            var coords = regionCoordinates[regionName];
            var riskLevel = riskData[regionName];
            var extinctionIndex = extinctionData[regionName];
            var popDecline = populationDecline[regionName];

            console.log('마커 생성:', regionName, coords, riskLevel);

            var position = new kakao.maps.LatLng(coords[0], coords[1]);

            var marker = new kakao.maps.Marker({
                position: position,
                map: map
            });

            // ✅ bounds 에 추가
            bounds.extend(position);

            var infoWindowContent = `
                <div style="
                    padding: 12px;
                    background: white;
                    border-radius: 8px;
                    min-width: 160px;
                    font-family: 'Malgun Gothic', sans-serif;
                    border: 1px solid #ddd;
                ">
                    <div style="font-size: 16px; font-weight: bold; color: #2c3e50; margin-bottom: 8px;">
                        ${regionName}
                    </div>
                    <div style="line-height: 1.6;">
                        <div style="color: #555; margin-bottom: 4px;">
                            <span style="color: #888;">소멸지수:</span>
                            <strong style="color: #d84315;">${extinctionIndex}</strong>
                        </div>
                        <div style="color: #555;">
                            <span style="color: #888;">위험등급:</span>
                            <strong style="color: ${getMarkerColor(riskLevel)};">${riskLevel}</strong>
                        </div>
                        <div style="color: #555;">
                          <span style="color: #888;">인구감소율:</span>
                          <strong>${typeof popDecline === 'number' ? popDecline.toFixed(1) + '%' : popDecline}</strong>
                        </div>
                    </div>
                </div>
            `;

            var infoWindow = new kakao.maps.InfoWindow({
                content: infoWindowContent,
                removable: false
            });

            // 마커 호버(마우스 오버)
            kakao.maps.event.addListener(marker, 'mouseover', function () {
                infoWindow.open(map, marker);
            });

            // 마우스 아웃
            kakao.maps.event.addListener(marker, 'mouseout', function () {
                infoWindow.close();
            });

            markers.push(marker);
            infoWindows.push(infoWindow);
        });

        console.log('전체 마커 추가 완료 - 총', markers.length, '개');

        // ✅ 마지막에 지도 영역 조정!
        map.setBounds(bounds);

        // 통계 정보 추가
        addStatistics(map);
    }

    // ✅ 7️⃣ 통계 정보 표시
    function addStatistics(map) {
        var highRiskCount = Object.values(riskData).filter(risk => risk === "고위험").length;
        var riskCount = Object.values(riskData).filter(risk => risk === "위험").length;
        var cautionCount = Object.values(riskData).filter(risk => risk === "주의").length;
        var safeCount = Object.values(riskData).filter(risk => risk === "안전").length;

        console.log('통계:', {
            고위험: highRiskCount,
            위험: riskCount,
            주의: cautionCount,
            안전: safeCount
        });
    }

    // ✅ 8️⃣ 초기화 시작
    console.log('카카오맵 대기 시작');
    waitForKakaoMap();
});