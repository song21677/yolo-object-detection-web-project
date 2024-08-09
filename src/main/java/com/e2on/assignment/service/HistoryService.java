package com.e2on.assignment.service;

import com.e2on.assignment.dto.AnalysisResultDTO;
import com.e2on.assignment.dto.ImageDTO;
import com.e2on.assignment.entity.AnalysisResultEntity;
import com.e2on.assignment.entity.ImageEntity;
import com.e2on.assignment.entity.MemberEntity;
import com.e2on.assignment.repository.AnalysisResultRepository;
import com.e2on.assignment.repository.ImageRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HistoryService {

    private final ImageRepository imageRepository;
    private final AnalysisResultRepository analysisResultRepository;

    public HistoryService(ImageRepository imageRepository, AnalysisResultRepository analysisResultRepository) {
        this.imageRepository = imageRepository;
        this.analysisResultRepository = analysisResultRepository;
    }

    @Transactional
    public List<ImageDTO> showHistoryList(MemberEntity session) {
        List<ImageEntity> imageList = imageRepository.findAllByMemberId(session.getId());

        List<ImageDTO> historyList = imageList.stream()
                .map(image -> {
                    ImageDTO imageDTO = new ImageDTO();
                    imageDTO.setId(image.getId());
                    imageDTO.setUploadedName(image.getUploadedNameWithExt());
                    MemberEntity member = image.getMember();
                    imageDTO.setOwner(member.getLoginId());
                    imageDTO.setRequestAt(image.getUploadedAt().toLocalDate());
                    return imageDTO;
                })
                .collect(Collectors.toList());

        return historyList;
    }

    @Transactional
    public ImageDTO showHistory(String id) {
        //List<AnalysisResultEntity> byImageId = analysisResultRepository.findByImageId(UUID.fromString("3dee33b6-fba5-4563-a7ef-57ad302b6293"));
        //UUID imageId = UUID.fromString("5b2eea7c-d252-4b56-a015-7d7a1997868a");
        List<AnalysisResultEntity> analysisResult = analysisResultRepository.findAllByImageId(UUID.fromString(id));

        ImageEntity image = analysisResult.get(0).getImage();
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setStoredName(image.getUuidNameWithExt());
        imageDTO.setUploadedName(image.getUploadedNameWithExt());
        MemberEntity member = image.getMember();
        imageDTO.setOwner(member.getLoginId());
        imageDTO.setRequestAt(image.getUploadedAt().toLocalDate());
        imageDTO.setUploadedSize(image.getUploadedSize());
        imageDTO.setAnalyzedAt(image.getAnalyzedAt());
        imageDTO.setAnalyzedSize(image.getAnalyzedSize());
        imageDTO.setMimeType(image.getMimeType());

        List<AnalysisResultDTO> analysisResultDtos = analysisResult.stream()
                .map(rs -> {
                    AnalysisResultDTO analysisResultDto = new AnalysisResultDTO();
                    analysisResultDto.setX(rs.getX());
                    analysisResultDto.setY(rs.getY());
                    analysisResultDto.setW(rs.getW());
                    analysisResultDto.setH(rs.getH());
                    analysisResultDto.setCls(rs.getCls().getMean());
                    analysisResultDto.setConfidence(rs.getConfidence());

                    return analysisResultDto;
                })
                .collect(Collectors.toList());
        imageDTO.setAnalysisResultDTOList(analysisResultDtos);

        return imageDTO;
    }
}
