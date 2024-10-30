package com.kidsworld.kidsping.domain.kid.service;

import com.kidsworld.kidsping.domain.kid.dto.request.CreateKidRequest;
import com.kidsworld.kidsping.domain.kid.dto.request.KidMbtiDiagnosisRequest;
import com.kidsworld.kidsping.domain.kid.dto.request.UpdateKidRequest;
import com.kidsworld.kidsping.domain.kid.dto.response.*;
import com.kidsworld.kidsping.domain.kid.entity.Kid;
import com.kidsworld.kidsping.domain.kid.entity.KidBadgeAwarded;
import com.kidsworld.kidsping.global.common.entity.CommonCode;

import java.util.List;

public interface KidService {

    CreateKidResponse createKid(CreateKidRequest request);

    GetKidResponse getKid(Long kidId);

    UpdateKidResponse updateKid(Long kidId, UpdateKidRequest request);

    DeleteKidResponse deleteKid(Long kidId);

    List<GetKidMbtiHistoryResponse> getKidMbtiHistory(Long kidId);


    void diagnoseKidMbti(KidMbtiDiagnosisRequest diagnosisRequest);

    List<KidBadgeAwarded> getAwardedBadges(Long kidId);
}