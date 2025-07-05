package com.inner_medicine.infra.gpt.service;

import java.util.List;

public interface OpenAIService {
    double getSkillMatchScore(String applicantSkills, String jobDescription);
    double getTextSimilarity(String text1, String text2);
    List<String> extractSkillsFromText(String text);

}
