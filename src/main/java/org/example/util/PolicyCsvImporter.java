package org.example.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.example.domain.Policy;
import org.example.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class PolicyCsvImporter {

    @Autowired
    private PolicyRepository policyRepository;

    @PostConstruct
    public void importCsv() {
        try {
            System.out.println("✅ 정책 CSV Import 시작!");

            // ✅ 기존 데이터 삭제
            policyRepository.deleteAll();
            System.out.println("✅ 기존 정책 데이터 삭제 완료!");

            // CSV 읽기
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("policy.csv");

            if (inputStream == null) {
                throw new RuntimeException("❌ policy.csv 파일을 찾을 수 없습니다! (src/main/resources 확인)");
            }

            CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .withSkipLines(1)
                    .build();

            List<String[]> rows = csvReader.readAll();

            for (String[] nextLine : rows) {
                if (nextLine.length < 6) {
                    System.out.println("⚠️ 잘못된 row 건너뜀: " + String.join(",", nextLine));
                    continue;
                }

                // ✅ 정확하게 매핑
                Policy policy = new Policy(
                        nextLine[0],  // name
                        nextLine[1],  // region
                        nextLine[2],  // policyType
                        nextLine[3],  // targetAudience
                        nextLine[4],  // description
                        nextLine[5]   // pdfUrl
                );

                policyRepository.save(policy);
                System.out.println("저장됨: " + policy.getName());
            }

            csvReader.close();
            System.out.println("✅ 정책 CSV Import 완료!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ CSV Import 중 오류 발생!");
        }
    }
}